package com.quantridulieu.hotelManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quantridulieu.hotelManagement.entities.HotelService;
import com.quantridulieu.hotelManagement.repositories.ServiceRepository;

import java.util.List;

@Service
public class HotelServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

 

    public List<HotelService> getAllServices() {
        return serviceRepository.findAll();
    }

    public void save(HotelService service) {
        if (service.getServiceId() == null) service.setServiceId(generateId());
        serviceRepository.save(service);
    }

    public void delete(String id) {
        serviceRepository.deleteById(id);
    }

    public HotelService getServiceById(String id) {
        return serviceRepository.findById(id).orElseThrow();
    }

    public List<HotelService> findByName(String name) {
        return serviceRepository.findServiceByName(name);
    }

    public List<HotelService> findByPrice(Float price) {
        return serviceRepository.findServiceByPrice(price);
    }

    public List<HotelService> findByPriceLessThan(Float maxPrice) {
        return serviceRepository.findServicesByPriceLessThan(maxPrice);
    }

    // S00001
    private String generateId() {
        Long count = serviceRepository.count();
        return String.format("S%05d", count + 1);
    }
}