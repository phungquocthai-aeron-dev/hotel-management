package com.quantridulieu.hotelManagement.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.io.IOException;
import java.util.Date;

import com.quantridulieu.hotelManagement.repositories.UseServiceRepository;
import com.quantridulieu.hotelManagement.entities.UseService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class UseServiceService {
	@Autowired
    UseServiceRepository useServiceRepository;
	
	@Autowired
	ExcelExportUtil excelExportUtil;
	
	// Xuất file excel
	public byte[] exportCustomerToExcel() throws IOException {
        return excelExportUtil.exportToExcel(useServiceRepository.findAll(), null, "Danh sách sử dụng dịch vụ");
    }

    public void save(UseService useService) {
        if (useService.getUsId() == null) useService.setUsId(generateId());
        useServiceRepository.save(useService);
    }

    public void delete(String id) {
        useServiceRepository.deleteById(id);
    }

    public List<UseService> getAllUseServices() {
        return useServiceRepository.findAll();
    }

    public UseService getUseServiceById(String id) {
        return useServiceRepository.findById(id).orElseThrow();
    }

    public List<UseService> getUseServiceByDate(Date usDate) {
        return useServiceRepository.findByUsDate(usDate);
    }

    public List<UseService> getUseServiceByQuantity(int quantity) {
        return useServiceRepository.findByQuantity(quantity);
    }

    public List<UseService> getUseServiceByServiceId(String serviceId) {
        return useServiceRepository.findByServiceId(serviceId);
    }

    public List<UseService> getUseServiceByRentId(String rentId) {
        return useServiceRepository.findByRentId(rentId);
    }

    public List<UseService> getUseServiceByPromotionId(String promotionId) {
        return useServiceRepository.findByPromotionId(promotionId);
    }
    
    public List<UseService> getAllUseServiceNotInInvoice() {
        return useServiceRepository.getAllUseServiceNotInInvoice();
    }

    private String generateId() {
        Long count = useServiceRepository.count();
        return String.format("US%05d", count + 1);
    }
}