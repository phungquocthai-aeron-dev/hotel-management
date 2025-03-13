package com.quantridulieu.hotelManagement.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantridulieu.hotelManagement.entities.Maintenance;

import com.quantridulieu.hotelManagement.entities.MaintenanceExport;
import com.quantridulieu.hotelManagement.entities.Staff;
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
        if (maintenance.getMtnId() == null)
            maintenance.setMtnId(generateId());
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

    public Long getTotalMaintenance() {
        return maintenanceRepository.getTotalMaintenance();
    }

    private String generateId() {
        Long count = maintenanceRepository.count();
        return String.format("MTN%05d", count + 1);
    }

    public byte[] exportInvoiceToExcelByListIds(List<String> ids) throws IOException {
        List<MaintenanceExport> list = new ArrayList<MaintenanceExport>();
        List<Maintenance> data = maintenanceRepository.findByMaintenanceIds(ids);
        int n = ids.size();
        for (int i = 0; i < n; i++) {
            Maintenance maintenance = data.get(i);
            MaintenanceExport export = new MaintenanceExport(maintenance);
            list.add(export);
        }
        return excelExportUtil.exportToExcel(list, null, "Danh sách hóa đơn");
    }

    @Transactional
    public List<Maintenance> searchMaintenances(String mtnId, String room, String staff) {
        return maintenanceRepository.SearchMaintenance(
                mtnId == null || mtnId.isEmpty() ? null : mtnId,
                room == null || room.isEmpty() ? null : room,
                staff == null || staff.isEmpty() ? null : staff);
    }

    // public String generateNewMtnId() {
    // String lastMtnId = maintenanceRepository.findLastMtnId(); // Lấy mã bảo trì
    // cuối cùng
    // if (lastMtnId == null) {
    // return "MTN001"; // Nếu chưa có dữ liệu, bắt đầu từ MTN001
    // }
    //
    // // Trích số từ mã cuối cùng (VD: "MTN005" -> 5)
    // int number = Integer.parseInt(lastMtnId.replace("MTN", ""));
    // number++; // Tăng lên 1
    //
    // return String.format("MTN%03d", number); // Định dạng thành "MTNxxx" (VD:
    // MTN006)
    // }
}
