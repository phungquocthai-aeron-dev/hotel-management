package com.quantridulieu.hotelManagement.controllers;

import java.io.Console;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quantridulieu.hotelManagement.entities.Maintenance;
import com.quantridulieu.hotelManagement.entities.Room;
import com.quantridulieu.hotelManagement.entities.Staff;
import com.quantridulieu.hotelManagement.repositories.MaintenanceRepository;
import com.quantridulieu.hotelManagement.repositories.StaffRepository;
import com.quantridulieu.hotelManagement.repositories.RoomRepository;
import com.quantridulieu.hotelManagement.services.MaintenanceService;
import com.quantridulieu.hotelManagement.services.RoomService;
import com.quantridulieu.hotelManagement.services.StaffService;

import org.springframework.ui.Model;


import jakarta.servlet.http.HttpSession;

@Controller
public class MaintenanceController {
	@Autowired
	MaintenanceService maintenanceService;
	@Autowired
    private MaintenanceRepository maintenanceRepository;
	@Autowired
    private RoomRepository roomRepository;
	@Autowired
    private StaffRepository staffRepository;
	
	
	@GetMapping(value = "/maintenance")
	public String getPage(Model model, HttpSession session) {
		Staff staff = (Staff) session.getAttribute("loggedInStaff");
//		Chưa đăng nhập --> cook
//      if(staff == null) return "redirect:/login";
		
		List<Maintenance> list = (List<Maintenance>) model.getAttribute("searchResult");
		
		// Nếu có dữ liệu đỗ về từ search thì bỏ qua
		// Không có thì xu lấy tất cả
		if(list == null) list = maintenanceService.getAllMaintenances();
		Long totalMaintenance = maintenanceService.getTotalMaintenance();
		model.addAttribute("totalMaintenance", totalMaintenance != null ? totalMaintenance : 0L);
		
		model.addAttribute("maintenances", list);
		model.addAttribute("staff", staff);
		
		return "maintenance";
	}
	
	@GetMapping(value = "/maintenance/details")
    public String getMaintenanceById(@RequestParam("id") String mtnId, Model model, HttpSession session) {
        Staff staff = (Staff)session.getAttribute("loggedInStaff");
        
//		Chưa đăng nhập --> cook
        if(staff == null) return "redirect:/login";
//		Không phải admin --> cook
		if(!staff.getRole().equals("ADMIN")) return "redirect:/maintenance";
        Maintenance maintenance = maintenanceService.getMaintenanceById(mtnId);

        
        model.addAttribute("maintenance", maintenance);
        model.addAttribute("staff", staff);
        
		Map<String, String> errors = (Map<String, String>) model.getAttribute("errors");
		if(errors != null) {
			model.addAttribute("errors", errors);
		}
		
        return "editmaintenance";
    }
	
	@GetMapping("/maintenance/search")
    public String searchMaintenance(
    		RedirectAttributes redirectAttributes,
    		@RequestParam(required = false, defaultValue = "") String mtnId,
            @RequestParam(required = false, defaultValue = "") String room,
            @RequestParam(required = false, defaultValue = "") String staff) {
    	
//		Chưa đăng nhập --> cook
//		Staff staff = (Staff) session.getAttribute("loggedInStaff");
//      if(staff == null) return "redirect:/login";
    	
    	List<Maintenance> maintenances = maintenanceService.searchMaintenances(
    			mtnId,
    			room,
    			staff
            );
    	
    	redirectAttributes.addFlashAttribute("searchResult", maintenances);
    	return "redirect:/maintenance";
    }
	
	@PostMapping("/maintenance/update")
    public String getMaintenanceUpdate(
    		@ModelAttribute Maintenance maintenance) {
    		 maintenanceService.save(maintenance);

        
        return "redirect:/maintenance";
    }
	
	@PostMapping("/maintenance/delete")
    public String deleteCustomer(@RequestParam("id") String mtnId) {
		maintenanceService.delete(mtnId);
      return "redirect:/maintenance";
    }
	
	@GetMapping("maintenance/generate-id")
	public ResponseEntity<String> generateMaintenanceId() {
	    String lastId = maintenanceRepository.findLastMaintenanceId();

	    if (lastId == null) {
	        lastId = "MTN00010"; // Bắt đầu từ MTN00010
	    }

	    // Lấy số từ mã bảo trì (bỏ 'MTN')
	    int lastNumber = Integer.parseInt(lastId.replace("MTN", ""));

	    // Tăng giá trị lên 1
	    int newNumber = lastNumber + 1;

	    // Định dạng lại với 5 chữ số
	    String newId = String.format("MTN%05d", newNumber);

	    return ResponseEntity.ok(newId);
	}


	@PostMapping("/maintenance/create")
	public String createMaintenance(
	                                @RequestParam("mtnDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date mtnDate,
	                                @RequestParam("mtnDescription") String mtnDescription,
	                                @RequestParam("mtnFee") Float mtnFee,
	                                @RequestParam("mtnEnd") @DateTimeFormat(pattern = "yyyy-MM-dd") Date mtnEnd,
	                                @RequestParam("mtnStatus") String mtnStatus,
	                                @RequestParam("room")String roomId,  // ID của phòng
	                                @RequestParam("staff") String staffId) {  // ID của nhân viên

	    // Lấy Room và Staff từ database
//	    Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Phòng không tồn tại"));
//	    Staff staff = staffRepository.findById(staffId).orElseThrow(() -> new RuntimeException("Nhân viên không tồn tại"));

	    // Tạo đối tượng Maintenance với kiểu dữ liệu đúng
		try {
			Room room = roomRepository.findById(roomId).orElseThrow();
			Staff staff = staffRepository.findById(staffId).orElseThrow();
			
		    Maintenance maintenance = new Maintenance(null, mtnDate, mtnDescription, mtnFee, mtnEnd, mtnStatus, room, staff);
		    
		    // Lưu vào database
		    maintenanceService.save(maintenance);
		} catch(Exception e) {
			e.printStackTrace();
		    return "redirect:/maintenance";

		}
	    
	    return "redirect:/maintenance";
	}
	
	@PostMapping("/maintenance/export")
    public ResponseEntity<byte[]> exportMaintenanceToExcel(
    		HttpSession session,
    		@RequestParam("maintenancesExport") List<String> maintenanceIds) {
//		Chưa đăng nhập --> cook
//		Staff staff = (Staff) session.getAttribute("loggedInStaff");
//      if(staff == null) return "redirect:/login";
    	
    	byte[] excelData = new byte[0];;
    	
    	if (maintenanceIds == null || maintenanceIds.isEmpty()) {
    		excelData = new byte[0];
        }
    	else {
            try {
				excelData = maintenanceService.exportInvoiceToExcelByListIds(maintenanceIds);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	            
            // Thiết lập header cho response
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=danh_sach_bao_tri.xlsx");
            
            // Trả về file Excel
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(excelData);
    }
}
