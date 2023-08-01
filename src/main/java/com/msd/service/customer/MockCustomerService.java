package com.msd.service.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.msd.model.Customer;

public class MockCustomerService implements CustomerService {

static List<Customer> customers = new ArrayList<Customer>();

	static {
		customers.add(new Customer("Miki", "abc", "m@email.com" ));
		customers.add(new Customer("Aaron", "12345", "a@email.com"));
		customers.add(new Customer("D'Artagnan", "zyx", "d@email.com"));
	}

	@Override
	public List<Customer> getCustomers() {
		return customers;
	}
	
	@Override
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}
	
	@Override
	public List<Customer> getCustomersByName(String name) {
		ArrayList<Customer> matches = new ArrayList<Customer>();
		for (Customer customer : customers) {
			if (customer.getName().equalsIgnoreCase(name)) {
				matches.add(customer);
			}
		}
		return matches;
	}
	
	@Override
	public List<Customer> getCustomersByNamePost(Customer customer) {
		return getCustomersByName(customer.getName());
	}
	
	@Override
	public Customer getCustomersById(int id) {
		return customers.get(id);
	}
	
	@Override
	public void addCustomer(int id, Customer customer) {
		customers.add(id, customer);
	}
	
	@Override
	public void deleteCustomer(int id) {
		customers.remove(id);
	}
	
	
}
