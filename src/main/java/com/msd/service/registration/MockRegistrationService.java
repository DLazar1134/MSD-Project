package com.msd.service.registration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.msd.model.Registration;

@Repository
@Profile("development")
public class MockRegistrationService implements RegistrationService {
	
	static List<Registration> registrations = new ArrayList<Registration>();
	
	static {
		registrations.add(new Registration("1", "1", "2023-01-17T00:00:00.000+0000", "My notes"));
		registrations.add(new Registration("2", "2", "2023-01-17T00:00:00.000+0000", "My notes"));
		registrations.add(new Registration("3", "3", "2023-01-17T00:00:00.000+0000", "My notes"));
	}

	@Override
	public Registration getRegistration(Long id) {
		return registrations.get(id.intValue());
	}

	@Override
	public List<Registration> getRegistrations() {
		return registrations;
	}

	@Override
	public void addRegistration(Registration registration) {
		registrations.add(registration);
	}

	@Override
	public void updateRegistration(Long id, Registration registration) {
		registrations.add(id.intValue(), registration);
	}

	@Override
	public void deleteRegistration(Long id) {
		registrations.remove(id);
	}

}
