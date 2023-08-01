package com.msd.rest;

import com.msd.model.Registration;
import com.msd.service.registration.*;

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
	RegistrationServiceImpl service;
	
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
		return service.updateRegistration(id, registration);
	}

	@PostMapping
	public ResponseEntity<?> addRegistration(@RequestBody Registration registration) throws URISyntaxException {
		return service.addRegistration(registration);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRegistration(@PathVariable Long id) throws URISyntaxException {
		return service.deleteRegistration(id);
	}

}
