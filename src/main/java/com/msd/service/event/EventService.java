package com.msd.service.event;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.msd.model.Event;

public interface EventService {

	public Event getEvent(Long id);

	public List<Event> getEvent();

	public void addEvent(Event event) throws URISyntaxException;

	public void updateEvent(Long id, Event newEvent) throws URISyntaxException;

	public void deleteEvent(Long id);

}
