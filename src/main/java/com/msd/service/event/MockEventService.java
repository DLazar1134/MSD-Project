package com.msd.service.event;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.msd.model.Event;

@Repository
public class MockEventService implements EventService {

	static List<Event> events = new ArrayList<Event>();

	static {
		events.add(new Event("1", "code", "title", "description"));
		events.add(new Event("2", "code", "title", "description"));
		events.add(new Event("3", "code", "title", "description"));
	}

	@Override
	public Event getEvent(Long id) {
		return events.get(id.intValue());
	}

	@Override
	public List<Event> getEvent() {
		return events;
	}

	@Override
	public void addEvent(Event event) {
		events.add(event);
	}

	@Override
	public void updateEvent(Long id, Event event) {
		events.add(id.intValue(), event);
	}

	@Override
	public void deleteEvent(Long id) {
		events.remove(id);

	}
}
