package com.quantridulieu.hotelManagement.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantridulieu.hotelManagement.entities.Maintenance;
import com.quantridulieu.hotelManagement.repositories.MaintenanceRepository;

@Service
public class MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;

    @Autowired
    public MaintenanceService(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Autowired
	ExcelExportUtil excelExportUtil;
	
	// Xuất file excel
	public byte[] exportMaintenanceToExcel() throws IOException {
		return excelExportUtil.exportToExcel(maintenanceRepository.findAll(), null, "Danh sách bảo trì");
    }
	
    public void save(Maintenance maintenance) {
        if (maintenance.getMtnId() == null) maintenance.setMtnId(generateId());
        maintenanceRepository.save(maintenance);
    }

    
    public void delete(String id) {
        maintenanceRepository.deleteById(id);
    }

    public List<Maintenance> getAllMaintenance() {
        return maintenanceRepository.findAll();
    }

    public Maintenance getMaintenanceById(String id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Maintenance không tồn tại với ID: " + id));
    }

    public List<Maintenance> getMaintenanceByStatus(String status) {
        return maintenanceRepository.findByStatus(status);
    }

    public Maintenance getMaintenanceByRoomId(String roomId) {
        return maintenanceRepository.findByRoomId(roomId);
    }

    public List<Maintenance> getMaintenanceByStaffId(String staffId) {
        return maintenanceRepository.findByStaffId(staffId);
    }

    public List<Maintenance> getMaintenanceByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return maintenanceRepository.findByDateRange(startDate, endDate);
    }
    
    public double getMonthlyExpenses(Integer month, Integer year) {
    	return maintenanceRepository.getMonthlyExpenses(year, month);
    }

    private String generateId() {
        Long count = maintenanceRepository.count();
        return String.format("MTN%05d", count + 1);
    }
}
