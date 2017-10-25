package com.coverfox.capture.captureevents.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coverfox.capture.captureevents.data.Event;

import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Repository
public class EventProducer  {

	private final Logger LOGGER = LoggerFactory.getLogger(EventProducer.class);
	
	@Autowired
	private KafkaSender<String, Event> eventSender;

	public void produce(Event event) {
		eventSender.send(Flux.just(event).map(e ->
		SenderRecord.create(new ProducerRecord<>("user-event", event.getId().toString(), event), event.getId())))
		.doOnError(e -> LOGGER.error("Error in sending message", e)) // Proper error message
		.subscribe(r -> {
			RecordMetadata metadata = r.recordMetadata();
			LOGGER.info("Message {} sent successfully, topic-partition={}-{} offset={}",
					r.correlationMetadata(),
					metadata.topic(),
					metadata.partition(),
					metadata.offset());

		});
	}

}
