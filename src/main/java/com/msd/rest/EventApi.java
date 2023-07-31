package com.msd.rest;

import java.util.List;

import com.msd.model.Event;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Transactional
@RequestMapping("/api/event")
public class EventApi {

	@PersistenceContext
	private EntityManager entityManager;

	// Get all @Get
	@GetMapping
	public List<Event> getEvent() {
		List<Event> allEvent = entityManager.createQuery("from Event", Event.class).getResultList();
		return allEvent;
	}

	// Get one @Get
	@GetMapping("/{id}")
	public Event getOneEvent(@PathVariable int id) {
		return getOneEvent(id);
	}

	// Update one @Put
	@PutMapping("/{id}")
	public void updateEvent(@PathVariable long id, @RequestBody Event event) {
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

	// Create one @Post
//	@PostMapping("/{id}")
//	public void addEvent(@PathVariable int id, @RequestBody Event event) {
//
//		Event newEvent = new Event(event.code, event.title, event.description, event.notes);
//		entityManager.persist(newEvent);
//	}

	// Delete one @Delete
	@DeleteMapping("/{id}")
	public void deleteEvent(@PathVariable long id) {
		Event eventToDelete = entityManager.find(Event.class, id);
		entityManager.remove(eventToDelete);
	}

}

