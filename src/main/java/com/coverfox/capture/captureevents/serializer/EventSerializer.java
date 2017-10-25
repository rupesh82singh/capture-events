package com.coverfox.capture.captureevents.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.coverfox.capture.captureevents.data.Event;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EventSerializer implements Serializer<Event> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] serialize(String topic, Event data) {
		byte[] retVal = null;
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	      retVal = objectMapper.writeValueAsString(data).getBytes();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return retVal;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
