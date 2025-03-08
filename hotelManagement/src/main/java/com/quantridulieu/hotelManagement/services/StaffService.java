package com.quantridulieu.hotelManagement.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantridulieu.hotelManagement.entities.Staff;
import com.quantridulieu.hotelManagement.entities.StaffDTO;
import com.quantridulieu.hotelManagement.repositories.StaffRepository;

@Service
public class StaffService {
	@Autowired
	StaffRepository staffRepository;
	
	@Autowired
	ExcelExportUtil excelExportUtil;
	
	// Xuất file excel
	public byte[] exportCustomerToExcel() throws IOException {
		List<Staff> staffList = staffRepository.findAll();
		if(!staffList.isEmpty()) {
			List<StaffDTO> result = staffList.stream().map(staff -> new StaffDTO(staff)).collect(Collectors.toList());
			return excelExportUtil.exportToExcel(result, null, "Danh sách nhân viên");
		}
		return null;
    }
	
	// Lấy nhân viên theo số điện thoại
	public Staff getStaffByPhone(String phone) {
        return staffRepository.findStaffByPhone(phone); 
    }
	
	public void save(Staff staff) {
		if(staff.getStaffId() == null) staff.setStaffId(generateId());
		staffRepository.save(staff);
	}
	
	public void delete(String id) {
		staffRepository.deleteById(id);
	}
	
	public List<Staff> getAllStaff() {
		return staffRepository.findAll();
	}
	
	public Staff getStaffById(String id) {
		return staffRepository.findById(id).orElseThrow();
	}
	
	private String generateId() {
		Long count = staffRepository.count();
		return String.format("ST%05d", count + 1);
	}
}
