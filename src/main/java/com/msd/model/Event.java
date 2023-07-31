package com.msd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="EVENT")
public class Event {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	public String code, title, description, notes;
	
		public Event() {}
		
		public Event(String Code, String Title, String Description, String notes) {
			code = Code;
			title= Title;
			description = Description;
			this.notes = notes;
		}
	}
