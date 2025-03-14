package com.quantridulieu.hotelManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantridulieu.hotelManagement.entities.Customer;
import com.quantridulieu.hotelManagement.repositories.CustomerRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
	
	public byte[] exportCustomerToExcelByListIds(List<String> ids) throws IOException {
        return excelExportUtil.exportToExcel(customerRepository.findByCustomerIds(ids), null, "Danh sách khách hàng");
    }
	
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	public Optional<Customer> findById(String id) {
	    return customerRepository.findById(id);
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
	
	@Transactional
	public List<Customer> searchCustomers(String customerId, String customerName, String customerPhone, String address) {
	    return customerRepository.SearchCustomer(
	        customerId == null || customerId.isEmpty() ? null : customerId,
	        customerName == null || customerName.isEmpty() ? null : customerName,
	        customerPhone == null || customerPhone.isEmpty() ? null : customerPhone,
	        address == null || address.isEmpty() ? null : address
	    );
	}
	
	// C00001
	private String generateId() {
		Long count = customerRepository.count();
		return String.format("CS%05d", count + 1);
	}
	
}
