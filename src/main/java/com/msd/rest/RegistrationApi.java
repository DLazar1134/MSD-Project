package com.msd.rest;

import com.msd.model.Registration;
import com.msd.service.registration.*;

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

@RestController
@CrossOrigin
@Transactional
@RequestMapping("/api/registrations")
public class RegistrationApi {
	
	@Autowired
	RegistrationServiceImpl service;

			
//	@PersistenceContext
//	private EntityManager entityManager;
	
	@GetMapping
	public List<Registration> getRegistrations() {
		return service.getRegistrations();
//		List<Registration> allRegistrations = entityManager.createQuery("from Registration", Registration.class).getResultList();
//		return allRegistrations;
	}
	
	@GetMapping("/{id}")
	public Registration getRegistration(@PathVariable Long id) {
		return service.getRegistration(id);
//		return entityManager.find(Registration.class, id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateRegistration(@PathVariable Long id, @RequestBody Registration registration) throws URISyntaxException {
		return service.updateRegistration(id, registration);
//		String date;
//		if(registration.registration_date.contains("T")) {
//			date = registration.registration_date;
//		} else {
//			Long timestamp = Long.parseLong(registration.registration_date);
//			DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//			format.setTimeZone(TimeZone.getDefault());
//			date = format.format(new Date(timestamp));
//		}
//
//		Registration reg = entityManager.find(Registration.class, id);
//		if(reg != null) {
//			reg.event_id = registration.event_id;
//			reg.customer_id = registration.customer_id;
//			reg.registration_date = registration.registration_date;
//			reg.notes = registration.notes;
//			return ResponseEntity.ok().build();
//		} else {
//			reg = new Registration(registration.event_id, registration.customer_id, date, registration.notes);
//			entityManager.persist(reg);
//			return ResponseEntity.created(new URI(url + reg.id)).build();
//		}
	}

	@PostMapping
	public ResponseEntity<?> addRegistration(@RequestBody Registration registration) throws URISyntaxException {
		return service.addRegistration(registration);
//		String date;
//		if(registration.registration_date.contains("T")) {
//			date = registration.registration_date;
//		} else {
//			Long timestamp = Long.parseLong(registration.registration_date);
//			DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//			format.setTimeZone(TimeZone.getDefault());
//			date = format.format(new Date(timestamp));
//		}
//		Registration reg  = new Registration(registration.event_id, registration.customer_id, date, registration.notes);
//		entityManager.persist(reg);
//		
//		return ResponseEntity.created(new URI(url + reg.id)).build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteRegistration(@PathVariable Long id) throws URISyntaxException {
		return service.deleteRegistration(id);
//		Registration registration = entityManager.find(Registration.class, id);
//		entityManager.remove(registration);
//		
//		return ResponseEntity.ok().build();
	}

}
