package com.coverfox.capture.captureevents.db;

import java.util.UUID;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.coverfox.capture.captureevents.CaptureActivitiesConstant;
import com.coverfox.capture.captureevents.data.Event;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class EventRepository implements ReactiveCrudRepository<Event, UUID>{
	
	@Autowired
	private ReactiveCassandraTemplate cassandraTemplate;
	
	@Override
	public <S extends Event> Mono<S> save(S entity) {
		return cassandraTemplate.insert(entity);
	}

	@Override
	public <S extends Event> Flux<S> saveAll(Iterable<S> entities) {
		return Flux.fromIterable(entities).flatMap(entity->cassandraTemplate.insert(entity));
	}

	@Override
	public <S extends Event> Flux<S> saveAll(Publisher<S> entityStream) {
		return Flux.first(entityStream).flatMap(entity->cassandraTemplate.insert(entity));
	}

	@Override
	public Mono<Event> findById(UUID id) {
		return cassandraTemplate.selectOneById(id, Event.class);
	}

	@Override
	public Mono<Event> findById(Publisher<UUID> id) {
		return null;
	}

	@Override
	public Mono<Boolean> existsById(UUID id) {
		return cassandraTemplate.exists(id, Event.class);
	}

	@Override
	public Mono<Boolean> existsById(Publisher<UUID> id) {
		return null;
	}

	@Override
	public Flux<Event> findAll() {
		return cassandraTemplate.select(CaptureActivitiesConstant.GET_ALLEVENT_QUERY, Event.class);
	}

	@Override
	public Flux<Event> findAllById(Iterable<UUID> ids) {
		return Flux.fromIterable(ids).flatMap(id->cassandraTemplate.selectOneById(id, Event.class));
	}

	@Override
	public Flux<Event> findAllById(Publisher<UUID> idStream) {
		return null;
	}

	@Override
	public Mono<Long> count() {
		return cassandraTemplate.count(Event.class);
	}

	@Override
	public Mono<Void> deleteById(UUID id) {
		cassandraTemplate.deleteById(id, Event.class);
		return Mono.empty();
	}

	@Override
	public Mono<Void> deleteById(Publisher<UUID> id) {
		return null;
	}

	@Override
	public Mono<Void> delete(Event entity) {
		cassandraTemplate.delete(entity);
		return Mono.empty();
	}

	@Override
	public Mono<Void> deleteAll(Iterable<? extends Event> entities) {
		Flux.fromIterable(entities).flatMap(entity->cassandraTemplate.delete(entity));
		return Mono.empty();
	}

	@Override
	public Mono<Void> deleteAll(Publisher<? extends Event> entityStream) {
		return null;
	}

	@Override
	public Mono<Void> deleteAll() {
		//cassandraTemplate.delete(new Query(CaptureActivitiesConstant.DELETE_ALLEvent_QUERY), Event.class);
		return null;
	}
	

}
