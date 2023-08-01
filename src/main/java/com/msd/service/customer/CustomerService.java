package com.msd.service.customer;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.msd.model.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();
	public void addCustomer(Customer customer) throws URISyntaxException;
	public List<Customer> getCustomersByName(String name);
	public List<Customer> getCustomersByNamePost(Customer customer);
	public Customer getCustomersById(int id);
	public void addCustomer(int id, Customer customer) throws URISyntaxException;
	public void deleteCustomer(int id) throws URISyntaxException;
	

}
