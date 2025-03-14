package com.quantridulieu.hotelManagement.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;

import java.util.Date;

import com.quantridulieu.hotelManagement.repositories.UseServiceRepository;
import com.quantridulieu.hotelManagement.entities.USExport;
import com.quantridulieu.hotelManagement.entities.UseService;

import org.springframework.beans.factory.annotation.Autowired;



@Service
public class UseServiceService {
	@Autowired
    UseServiceRepository useServiceRepository;
	
	@Autowired
	ExcelExportUtil excelExportUtil;
	
	// Xuất file excel
	public byte[] exportUSToExcel() throws IOException {
        return excelExportUtil.exportToExcel(useServiceRepository.findAll(), null, "Danh sách đăng ký dịch vụ");
    }
	
	public byte[] exportUSToExcelByIds(List<String> ids) throws IOException {
		List<UseService> usList = useServiceRepository.findAllById(ids);
		List<USExport> list = usList.stream().map(USExport::new).collect(Collectors.toList());
	    return excelExportUtil.exportToExcel(list, null, "Danh sách đăng ký dịch vụ");
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
    
    @Transactional
    public List<UseService> searchUseService(String usId, Integer quantity, Date usDate, String serviceId, String rentId, String promotionId) {
        return useServiceRepository.searchUseService(
            (usId == null || usId.isEmpty()) ? null : usId,
            quantity == null ? null : quantity,
            usDate == null ? null : usDate,
            (serviceId == null || serviceId.isEmpty()) ? null : serviceId,
            (rentId == null || rentId.isEmpty()) ? null : rentId,
            (promotionId == null || promotionId.isEmpty()) ? null : promotionId
        );
    }


    private String generateId() {
        Long count = useServiceRepository.count();
        return String.format("US%05d", count + 1);
    }
}