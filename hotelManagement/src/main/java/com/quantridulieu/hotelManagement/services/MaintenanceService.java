package com.quantridulieu.hotelManagement.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantridulieu.hotelManagement.entities.Maintenance;
import com.quantridulieu.hotelManagement.entities.MaintenanceExport;
import com.quantridulieu.hotelManagement.repositories.MaintenanceRepository;

@Service
public class MaintenanceService {
	@Autowired
    MaintenanceRepository maintenanceRepository;
    
    @Autowired
	ExcelExportUtil excelExportUtil;
    
 // Xuất file excel
 	public byte[] exportMaintenanceToExcel() throws IOException {
         return excelExportUtil.exportToExcel(maintenanceRepository.findAll(), null, "Danh sách bảo trì");
     }
 	
 	public byte[] exportMaintenanceToExcelByListIds(List<String> ids) throws IOException {
         return excelExportUtil.exportToExcel(maintenanceRepository.findByMaintenanceIds(ids), null, "Danh sách bảo trì");
     }

    @Autowired
    public MaintenanceService(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    public void save(Maintenance maintenance) {
        if (maintenance.getMtnId() == null) maintenance.setMtnId(generateId());
        maintenanceRepository.save(maintenance);
    }

    
    public void delete(String id) {
        maintenanceRepository.deleteById(id);
    }

    public List<Maintenance> getAllMaintenances() {
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

    private String generateId() {
        Long count = maintenanceRepository.count();
        return String.format("MTN%05d", count + 1);
    }
    
    public byte[] exportInvoiceToExcelByListIds(List<String> ids) throws IOException {
    	List<MaintenanceExport> list = new ArrayList<MaintenanceExport>();
    	List<Maintenance> data = maintenanceRepository.findByMaintenanceIds(ids);
    	int n = ids.size();
    	for(int i = 0; i < n; i++) {
    		Maintenance maintenance = data.get(i);
    		MaintenanceExport export = new MaintenanceExport(maintenance);
    		list.add(export);
    	}
        return excelExportUtil.exportToExcel(list, null, "Danh sách hóa đơn");
    }
}
