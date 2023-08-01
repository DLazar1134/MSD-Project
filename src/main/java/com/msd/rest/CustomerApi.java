package com.msd.rest;

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

import com.msd.model.Customer;
import com.msd.service.customer.CustomerServiceImpl;

@RestController
@CrossOrigin
@Transactional
@RequestMapping("/api/customers")
public class CustomerApi {
	
	
	
	@Autowired 
	CustomerServiceImpl service;
	
	@GetMapping
	public List<Customer> getCustomers() {
		return service.getCustomers();
	}
	
	@PostMapping
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) throws URISyntaxException {
		service.addCustomer(customer);
		return ResponseEntity.created(new URI("http://localhost:8080/api/customers/" + customer.getId())).build();
	}
	
	@PostMapping("/byname/{name}")
	public List<Customer> getCustomersByName(@PathVariable String name) {
		return service.getCustomersByName(name);
	}
	
	@PostMapping("/byname")
	public List<Customer> getCustomersByNamePost(@RequestBody Customer customer) {
		return service.getCustomersByName(customer.getName());
	}
	
	@GetMapping("/{id}")
	public Customer getCustomersById(@PathVariable int id) {
		return service.getCustomersById(id);
	}
	
	@PutMapping ("/{id}")
	public ResponseEntity<?> addCustomer(@PathVariable int id, @RequestBody Customer customer) throws URISyntaxException {
		service.addCustomer(id, customer);
		return ResponseEntity.created(new URI("http://localhost:8080/api/customers/" + customer.getId())).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int id) throws URISyntaxException {
		service.deleteCustomer(id);
		return ResponseEntity.ok().build();
	}
	
}
