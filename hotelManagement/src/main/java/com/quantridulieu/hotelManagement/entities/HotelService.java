package com.quantridulieu.hotelManagement.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Service")
public class HotelService {
    @Id
    @Column(name = "service_id")
    private String serviceId;
    
    @Column(name = "service_name")
    private String serviceName;
    
    @Column(name = "service_price")
    private Float servicePrice;
    
    public HotelService() {}

	public HotelService(String serviceId, String serviceName, Float servicePrice) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.servicePrice = servicePrice;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Float getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(Float servicePrice) {
		this.servicePrice = servicePrice;
	}
    
}
