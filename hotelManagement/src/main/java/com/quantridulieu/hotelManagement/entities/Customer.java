package com.quantridulieu.hotelManagement.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @Column(name = "customer_id")
    private String customerId;
    
    @Column(name = "customer_name")
    private String customerName;
    
    @Column(name = "customer_phone")
    private String customerPhone;
    
    @Column(name = "address")
    private String address;
    
    public Customer() {}

	public Customer(String customerId, String customerName, String customerPhone, String address) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.address = address;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    
}