package msd;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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

@RestController
@CrossOrigin
@Transactional
@RequestMapping("/api/registrations")
public class RegistrationApi {
	
	String url = "http://localhost:8080/api/registrations/";
		
	@PersistenceContext
	private EntityManager entityManager;
	
	@GetMapping
	public List<Registration> getRegistrations() {
		List<Registration> allRegistrations = entityManager.createQuery("from Registration", Registration.class).getResultList();
		return allRegistrations;
	}
	
	@GetMapping("/{id}")
	public Registration getRegistration(@PathVariable Long id) {
		return entityManager.find(Registration.class, id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateRegistration(@PathVariable Long id, @RequestBody Registration registration) throws URISyntaxException {
		String date;
		if(registration.registration_date.contains("T")) {
			date = registration.registration_date;
		} else {
			Long timestamp = Long.parseLong(registration.registration_date);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			format.setTimeZone(TimeZone.getDefault());
			date = format.format(new Date(timestamp));
		}

		Registration reg = entityManager.find(Registration.class, id);
		if(reg != null) {
			reg.event_id = registration.event_id;
			reg.customer_id = registration.customer_id;
			reg.registration_date = registration.registration_date;
			reg.notes = registration.notes;
			return ResponseEntity.ok().build();
		} else {
			reg = new Registration(registration.event_id, registration.customer_id, date, registration.notes);
			entityManager.persist(reg);
			return ResponseEntity.created(new URI(url + reg.id)).build();
		}
	}

	@PostMapping
	public ResponseEntity<?> addRegistration(@RequestBody Registration registration) throws URISyntaxException {
		String date;
		if(registration.registration_date.contains("T")) {
			date = registration.registration_date;
		} else {
			Long timestamp = Long.parseLong(registration.registration_date);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			format.setTimeZone(TimeZone.getDefault());
			date = format.format(new Date(timestamp));
		}
		Registration reg  = new Registration(registration.event_id, registration.customer_id, date, registration.notes);
		entityManager.persist(reg);
		
		URI location = new URI(url + reg.id);
		ResponseEntity<?> response = ResponseEntity.created(location).build();
		return response;
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteRegistration(@PathVariable Long id) throws URISyntaxException {
		Registration registration = entityManager.find(Registration.class, id);
		entityManager.remove(registration);
		
		ResponseEntity<?> response = ResponseEntity.ok().build();
		return response;
	}

}
