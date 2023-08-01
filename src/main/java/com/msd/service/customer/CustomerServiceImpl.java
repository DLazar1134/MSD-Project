package com.msd.service.customer;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.msd.model.Customer;

@Repository
public class CustomerServiceImpl implements CustomerService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Customer> getCustomers() {
		List<Customer> allCustomers = entityManager.createQuery("from Customer", Customer.class).getResultList();
		return allCustomers;
	}
	
	@Override
	public void addCustomer(Customer customer) throws URISyntaxException {
		Customer newCustomer = new Customer(customer.getName(), customer.getPassword(), customer.getEmail());
		entityManager.persist(newCustomer);
	}
	
	@Override
	public List<Customer> getCustomersByName(String name) {
		List<Customer> customerToGet = entityManager.createQuery("SELECT c FROM Customer c WHERE c.name LIKE :custName").setParameter("custName", name).getResultList();
		return customerToGet;
	}
	
	@Override
	public List<Customer> getCustomersByNamePost(Customer customer) {
		return getCustomersByName(customer.getName());
	}
	
	@Override
	public Customer getCustomersById(int id) {
		Customer customerToGet = entityManager.find(Customer.class, id);
		return customerToGet;
	}
	
	@Override
	public void addCustomer(int id, Customer customer) throws URISyntaxException {
		Customer newCustomer = new Customer(customer.getName(), customer.getPassword(), customer.getEmail());
		entityManager.persist(newCustomer);
	}
	
	@Override
	public void deleteCustomer(int id) throws URISyntaxException {
		Customer customerToDelete = entityManager.find(Customer.class, id);
		entityManager.remove(customerToDelete);
	}
}
