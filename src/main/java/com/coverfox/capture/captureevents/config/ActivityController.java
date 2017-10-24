package com.coverfox.capture.captureevents.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coverfox.capture.captureevents.data.Event;
import com.coverfox.capture.captureevents.db.EventRepository;
import com.coverfox.capture.captureevents.service.TicketService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class ActivityController {
	
	private static final Log log = LogFactory.getLog(ActivityController.class);
	
	@Autowired
	private EventRepository repo;
	@Autowired
	private TicketService ticketService;

    @GetMapping(path = "/")
    public Flux<Event> get() {
        return repo.findAll();
    }

    @PostMapping
    public Mono<ResponseEntity<Event>> save(@RequestBody Event event) {
        return this.repo.save(event)
                .doOnSuccess(ticketService::create).doOnError(e->log.error("Error:",e))
                .map(savedEvent -> new ResponseEntity<>(savedEvent, HttpStatus.CREATED));
    }


}
