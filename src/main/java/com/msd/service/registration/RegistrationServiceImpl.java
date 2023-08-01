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

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.msd.model.Registration;

@Repository
@Profile("production")
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
	public void addRegistration(Registration registration) throws URISyntaxException {
		updateOrAdd(-1L, registration);
	}

	@Override
	public void updateRegistration(Long id, Registration newRegistration) throws URISyntaxException {
		updateOrAdd(id, newRegistration);
	}

	@Override
	public void deleteRegistration(Long id) {
		Registration registration = entityManager.find(Registration.class, id);
		entityManager.remove(registration);
	}
	
	private void updateOrAdd(Long id, Registration registration) throws URISyntaxException {
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
		} else {
			reg = new Registration(registration.event_id, registration.customer_id, date, registration.notes);
			entityManager.persist(reg);
		}
	}
	
}
