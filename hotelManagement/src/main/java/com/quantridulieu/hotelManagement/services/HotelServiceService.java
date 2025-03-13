package com.quantridulieu.hotelManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantridulieu.hotelManagement.entities.HotelService;
import com.quantridulieu.hotelManagement.repositories.HotelServiceRepository;

import java.io.IOException;
import java.util.List;

@Service
public class HotelServiceService {

    @Autowired
    private HotelServiceRepository serviceRepository;

 
	@Autowired
	ExcelExportUtil excelExportUtil;
	
	// Xuất file excel
	public byte[] exportHotelServiceToExcel() throws IOException {
        return excelExportUtil.exportToExcel(getAllServices(), null, "Danh sách dịch vụ khách sạn");
    }
	
	public byte[] exportHotelServiceToExcelByListIds(List<String> ids) throws IOException {
	    return excelExportUtil.exportToExcel(serviceRepository.findByServiceIds(ids), null, "Danh sách dịch vụ khách sạn");
	}
	
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
    
    @Transactional
    public List<HotelService> searchServices(String serviceId, String serviceName, Float servicePrice) {
        return serviceRepository.searchService( // Sửa từ hotelServiceRepository thành serviceRepository
                serviceId == null || serviceId.isEmpty() ? null : serviceId,
                serviceName == null || serviceName.isEmpty() ? null : serviceName,
                servicePrice
        );
    }

    // S00001
    private String generateId() {
        Long count = serviceRepository.count();
        return String.format("S%05d", count + 1);
    }
}