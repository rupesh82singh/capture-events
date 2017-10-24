package com.coverfox.capture.captureevents.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.coverfox.capture.captureevents.data.Event;
import com.coverfox.capture.captureevents.db.EventRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Deprecated
@Service
public class CaptureActivitiesHandler {
	
	@Autowired
	private EventRepository eventRepository;
	
	public Mono<ServerResponse> get(ServerRequest request){
		return ServerResponse.ok().body(Flux.fromArray(new String[] {"One","Two"}), String.class);
	}
	
	public Mono<ServerResponse> saveEvent(ServerRequest request){
		Mono<Event> userE = request.bodyToMono(Event.class);
		eventRepository.saveAll(userE);
		return ServerResponse.ok().body(Mono.just("Sucess"), String.class);
	}

}
