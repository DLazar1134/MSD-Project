package com.msd.rest;

import java.net.*;
import java.util.List;

import com.msd.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
@RequestMapping("/api/customers")
public class CustomerApi {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@GetMapping
	public List<Customer> getCustomers() {
		List<Customer> allCustomers = entityManager.createQuery("from Customer", Customer.class).getResultList();
		return allCustomers;
	}
	
	@PostMapping
	public void addCustomer(@RequestBody Customer customer) {
		Customer newCustomer = new Customer(customer.getName(), customer.getPassword(), customer.getEmail());
		entityManager.persist(newCustomer);
	}
	
	@GetMapping("/byname/{name}")
	public List<Customer> getCustomersByName(@PathVariable String name) {
		List<Customer> customerToGet = entityManager.createQuery("SELECT c FROM Customer c WHERE c.name LIKE :custName").setParameter("custName", name).getResultList();
		return customerToGet;
	}
	
	@PostMapping("/byname")
	public List<Customer> getCustomersByNamePost(@RequestBody Customer customer) {
		return getCustomersByName(customer.getName());
	}
	
	@GetMapping("/{id}")
	public Customer getCustomersById(@PathVariable int id) {
		Customer customerToGet = entityManager.find(Customer.class, id);
		return customerToGet;
	}
	
	@PutMapping("/{id}")
	public void addCustomer(@PathVariable int id, @RequestBody Customer customer) {
		Customer newCustomer = new Customer(customer.getName(), customer.getPassword(), customer.getEmail());
		entityManager.persist(newCustomer);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable int id) {
		Customer customerToDelete = entityManager.find(Customer.class, id);
		entityManager.remove(customerToDelete);
	}
	
}
