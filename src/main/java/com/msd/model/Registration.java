package com.msd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REGISTRATION")
public class Registration{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	public String event_id, customer_id, registration_date, notes;

	public Registration() {}
	
	public Registration(String eventId, String customerId, String registrationDate, String notes) {
		event_id = eventId;
		customer_id = customerId;
		registration_date = registrationDate;
		this.notes = notes;
	}
}
