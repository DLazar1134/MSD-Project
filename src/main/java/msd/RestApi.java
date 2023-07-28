package msd;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Transactional
@RequestMapping("/api")
public class RestApi {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@GetMapping("/event")
	public List<Event> getEvent() {
		List<Event> allEvent = entityManager.createQuery("from Event", Event.class).getResultList();
		return allEvent;
	}
	@PutMapping("/event/{id}")
	public void addEvent(@PathVariable int id, @RequestBody Event event) {

		
		Event newEvent = new Event(event.code, event.title, event.description, event.notes);
		entityManager.persist(newEvent);
	}
	@DeleteMapping("/event/{id}")
	public void deleteEvent(@PathVariable Long id) {
		Event eventToDelete = entityManager.find(Event.class, id);
		entityManager.remove(eventToDelete);
	}
}
