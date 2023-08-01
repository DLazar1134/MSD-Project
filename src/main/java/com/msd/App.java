package com.msd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com", "com.msd", "com.msd.rest", "com.msd.service", "com.msd.service.customer", "com.msd.service.registration", "com.msd.model"})
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
