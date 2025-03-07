package com.quantridulieu.hotelManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantridulieu.hotelManagement.entities.Customer;
import com.quantridulieu.hotelManagement.repositories.CustomerRepository;

import java.io.IOException;
import java.util.List;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ExcelExportUtil excelExportUtil;
	
	// Xuất file excel
	public byte[] exportCustomerToExcel() throws IOException {
        return excelExportUtil.exportToExcel(customerRepository.findAll(), null, "Danh sách khách hàng");
    }
	
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	public void save(Customer customer) {
		if(customer.getCustomerId() == null) customer.setCustomerId(generateId());
		customerRepository.save(customer);
	}
	
	public void delete(String id) {
		customerRepository.deleteById(id);
	}
	
	public Customer getCustomerById(String id) {
		return customerRepository.findById(id).orElseThrow();
	}
	
	public List<Customer> findByCustomerName(String name) {
		return customerRepository.findCustomerByName(name);
	}
	
	public List<Customer> findByAddress(String address) {
		return customerRepository.findCustomerByAddress(address);
	}
	
	public Customer findByPhone(String phone) {
		return customerRepository.findCustomerByPhone(phone);
	}
	
	// C00001
	private String generateId() {
		Long count = customerRepository.count();
		return String.format("C%05d", count + 1);
	}
	
}
