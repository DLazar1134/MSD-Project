package com.msd.service.event;

import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.msd.model.Event;

@Repository
public class EventServiceImpl implements EventService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Event> getEvent() {
		return entityManager.createQuery("from Event", Event.class).getResultList();
	}

	@Override
	public Event getEvent(Long id) {
		return entityManager.find(Event.class, id);
	}

	private void updateOrAdd(Long id, Event event) throws URISyntaxException {
		Event eventToUpdate = entityManager.find(Event.class, id);
		if (eventToUpdate != null) {
			eventToUpdate.id = event.id;
			eventToUpdate.code = event.code;
			eventToUpdate.title = event.title;
			eventToUpdate.description = event.description;
		} else {

			Event newEvent = new Event(event.code, event.title, event.description, event.notes);
			entityManager.persist(newEvent);
		}
	}

	@Override
	public void addEvent(Event event) throws URISyntaxException {
		updateOrAdd(-1L, event);
	}

	@Override
	public void updateEvent(Long id, Event newEvent) throws URISyntaxException {
		updateOrAdd(id, newEvent);
	}

	@Override
	public void deleteEvent(Long id) {
		Event event = entityManager.find(Event.class, id);
		entityManager.remove(event);
	}
}
