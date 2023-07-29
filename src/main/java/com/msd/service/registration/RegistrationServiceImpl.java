package com.msd.service.registration;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.msd.model.Registration;

@Repository
public class RegistrationServiceImpl implements RegistrationService {
		
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Registration> getRegistrations() {
		return entityManager.createQuery("from Registration", Registration.class).getResultList();
	}

	@Override
	public Registration getRegistration(Long id) {
		return entityManager.find(Registration.class, id);
	}

	@Override
	public ResponseEntity<?> addRegistration(Registration registration) throws URISyntaxException {
		return updateOrAdd(-1L, registration);
	}

	@Override
	public ResponseEntity<?> updateRegistration(Long id, Registration newRegistration) throws URISyntaxException {
		return updateOrAdd(id, newRegistration);
	}

	@Override
	public ResponseEntity<?> deleteRegistration(Long id) {
		Registration registration = entityManager.find(Registration.class, id);
		entityManager.remove(registration);
		return ResponseEntity.ok().build();		
	}
	
	private ResponseEntity<?> updateOrAdd(Long id, Registration registration) throws URISyntaxException {
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
			return ResponseEntity.created(new URI("http://localhost:8080/api/registrations/" + reg.id)).build();
		}
	}
	
}
