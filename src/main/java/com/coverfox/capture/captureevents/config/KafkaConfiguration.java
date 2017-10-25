package com.coverfox.capture.captureevents.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coverfox.capture.captureevents.data.Event;
import com.coverfox.capture.captureevents.serializer.EventSerializer;

import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

@Configuration
public class KafkaConfiguration {

	@Bean(name="eventSender", autowire=Autowire.BY_NAME)
	public KafkaSender<String, Event> createEventSender() {
		Map<String, Object> props = createCommonConfig();
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EventSerializer.class);
		SenderOptions<String, Event> senderOptions = SenderOptions.create(props);
		return KafkaSender.create(senderOptions);
	}

	private Map<String, Object> createCommonConfig() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.CLIENT_ID_CONFIG, "event-producer");
		props.put(ProducerConfig.ACKS_CONFIG, "all");
		return props;
	}

}
