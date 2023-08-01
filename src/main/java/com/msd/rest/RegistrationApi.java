package com.msd.rest;

import com.msd.model.Registration;
import com.msd.service.registration.*;

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

@RestController
@CrossOrigin
@Transactional
@RequestMapping("/api/registrations")
public class RegistrationApi {
	
	@Autowired
	RegistrationService service;
	
	@GetMapping
	public List<Registration> getRegistrations() {
		return service.getRegistrations();
	}
	
	@GetMapping("/{id}")
	public Registration getRegistration(@PathVariable Long id) {
		return service.getRegistration(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateRegistration(@PathVariable Long id, @RequestBody Registration registration) throws URISyntaxException {
		service.updateRegistration(id, registration);
		return ResponseEntity.created(new URI("http://localhost:8080/api/registrations/" + id)).build();
	}

	@PostMapping
	public ResponseEntity<?> addRegistration(@RequestBody Registration registration) throws URISyntaxException {
		service.addRegistration(registration);
		return ResponseEntity.created(new URI("http://localhost:8080/api/registrations/" + registration.id)).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRegistration(@PathVariable Long id) throws URISyntaxException {
		service.deleteRegistration(id);
		return ResponseEntity.ok().build();
	}

}
