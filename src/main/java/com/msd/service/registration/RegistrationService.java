package com.msd.service.registration;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.msd.model.Registration;

public interface RegistrationService {
	
	public Registration getRegistration(Long id);
	public List<Registration> getRegistrations();
	public void addRegistration(Registration registration) throws URISyntaxException;
	public void updateRegistration(Long id, Registration newRegistration) throws URISyntaxException;
	public void deleteRegistration(Long id);
	
}
