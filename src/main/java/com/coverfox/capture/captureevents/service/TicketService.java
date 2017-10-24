package com.coverfox.capture.captureevents.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.coverfox.capture.captureevents.data.Event;
import com.coverfox.capture.captureevents.data.Ticket;

import reactor.core.publisher.Mono;

@Component
public class TicketService {
	
	private static final Log log= LogFactory.getLog(TicketService.class);
	
	public Mono<Ticket> create(Event event) {
		Ticket ticket = new Ticket();
		ticket.setAdvisor("Test Advisor");
		log.info("Created new Ticket. Sending to Kafaka Topic.");
		return Mono.just(ticket);
	}

}
