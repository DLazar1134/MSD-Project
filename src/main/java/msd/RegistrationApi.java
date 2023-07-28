package msd;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
public class RegistrationApi {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@GetMapping("/registrations")
	public List<Registration> getRegistrations() {
		List<Registration> allRegistrations = entityManager.createQuery("from Registration", Registration.class).getResultList();
		return allRegistrations;
	}
	
	@PutMapping("/registrations/{id}")
	public void addRegistration(@PathVariable int id, @RequestBody Registration registration) {
		String date;
		if(registration.registration_date.contains("T")) {
			date = registration.registration_date;
		} else {
			Long timestamp;
			timestamp = Long.parseLong(registration.registration_date);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			format.setTimeZone(TimeZone.getDefault());
			date = format.format(new Date(timestamp));
		}
		
		Registration newRegistration = new Registration(registration.event_id, registration.customer_id, date, registration.notes);
		entityManager.persist(newRegistration);
	}
	
	@DeleteMapping("/registrations/{id}")
	public void deleteRegistration(@PathVariable Long id) {
		Registration registrationToDelete = entityManager.find(Registration.class, id);
		entityManager.remove(registrationToDelete);
	}

}
