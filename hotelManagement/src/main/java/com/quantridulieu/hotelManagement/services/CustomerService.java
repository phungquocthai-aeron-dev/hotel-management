package com.quantridulieu.hotelManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantridulieu.hotelManagement.entities.Customer;
import com.quantridulieu.hotelManagement.repositories.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	public void save(Customer customer) {
		customer.setCustomerId(generateId());
		customerRepository.save(customer);
	}
	
	public void delete(String id) {
		customerRepository.deleteById(id);
	}
	
	public Customer getCustomerById(String id) {
		return customerRepository.findById(id).orElseThrow();
	}
	
	// C00001
	private String generateId() {
		Long count = customerRepository.count();
		return String.format("S%05d", count + 1);
	}
	
}
