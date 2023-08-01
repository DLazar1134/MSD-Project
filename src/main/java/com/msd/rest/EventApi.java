package com.msd.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msd.model.Event;
import com.msd.service.event.EventService;

@RestController
@CrossOrigin
@Transactional
@RequestMapping("/api/event")
public class EventApi {

	@Autowired
	EventService service;

	@GetMapping
	public List<Event> getEvent() {
		return service.getEvent();
	}

	@GetMapping("/{id}")
	public Event getOneEvent(@PathVariable Long id) {
		return service.getEvent(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateEvent(@PathVariable long id, @RequestBody Event event) throws URISyntaxException {
		service.updateEvent(id, event);
		return ResponseEntity.created(new URI("http://localhost:8080/api/event/" + id)).build();
	}

	@PostMapping
	public ResponseEntity<?> addEvent(@RequestBody Event event) throws URISyntaxException {
		service.addEvent(event);
		return ResponseEntity.created(new URI("http://localhost:8080/api/event/" + event.id)).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEvent(@PathVariable Long id) throws URISyntaxException {
		service.deleteEvent(id);
		return ResponseEntity.ok().build();
	}

}
