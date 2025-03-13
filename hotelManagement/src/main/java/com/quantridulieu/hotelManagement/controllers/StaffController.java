package com.quantridulieu.hotelManagement.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quantridulieu.hotelManagement.entities.Staff;
import com.quantridulieu.hotelManagement.services.StaffService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StaffController {
    @Autowired
    private StaffService staffService;

    @GetMapping("/staff")
    public String showStaff(Model model, HttpSession session) {
    	Staff staff = (Staff) session.getAttribute("loggedInStaff");
    	
    	List<Staff> staffList = (List<Staff>) model.getAttribute("searchResult");
    	if(staffList == null) staffList = staffService.getAllStaffs();
    	
    	model.addAttribute("staffList", staffList);
    	model.addAttribute("staff", staff);
    	
        Long totalStaff = staffService.getTotalStaff();
        model.addAttribute("totalStaff", totalStaff != null ? totalStaff : 0L);

        return "staff"; // Tên file JSP của bạn
    }
    
    @GetMapping("/staff/search")
    public String searchStaff(
    		RedirectAttributes redirectAttributes,
    		@RequestParam(required = false, defaultValue = "") String staffId,
            @RequestParam(required = false, defaultValue = "") String staffName,
            @RequestParam(required = false, defaultValue = "") String staffPhone) {
    	
//		Chưa đăng nhập --> cook
//		Staff staff = (Staff) session.getAttribute("loggedInStaff");
//      if(staff == null) return "redirect:/login";
    	
    	List<Staff> staffList = staffService.searchStaffs(
                staffId,
                staffName,
                staffPhone
            );
    	
    	redirectAttributes.addFlashAttribute("searchResult", staffList);
    	return "redirect:/staff";
    }

    @GetMapping(value = "/staff/details")
    public String getStaffById(@RequestParam("id") String staffId, Model model, HttpSession session) {
        Staff loggedInStaff = (Staff) session.getAttribute("loggedInStaff");

        // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
        if (loggedInStaff == null) return "redirect:/login";

        // Nếu không phải admin, chuyển hướng về trang chủ
        if (!loggedInStaff.getRole().equals("ADMIN")) return "redirect:/home";

        Staff staff = staffService.getStaffById(staffId);
        model.addAttribute("staff", staff);
        model.addAttribute("loggedInStaff", loggedInStaff);

        // Kiểm tra lỗi từ request trước đó (nếu có)
        Map<String, String> errors = (Map<String, String>) model.getAttribute("errors");
        if (errors != null) {
            model.addAttribute("errors", errors);
        }
        

        return "editstaff"; // Trả về trang cập nhật thông tin nhân viên
    }
    
    @PostMapping("/staff/edit")
    public String updateStaff(
        @ModelAttribute Staff staff,
        RedirectAttributes redirectAttributes) {
        
        String regex = "^[0-9]{10}$";
        boolean isValid = Pattern.matches(regex, staff.getStaffPhone());
        System.out.println(staff.getDateOfBirth());
        if (isValid) {
            staffService.save(staff);
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("phone", "Số điện thoại không đúng định dạng");
            redirectAttributes.addFlashAttribute("errors", errors);

            return "redirect:/staff/details?id=" + staff.getStaffId(); // Quay lại trang sửa nhân viên
        }
        return "redirect:/staff"; // Chuyển hướng về danh sách nhân viên
    }
    
    @PostMapping("/staff/delete")
    public String deleteCustomer(@RequestParam("id") String staffId) {
        staffService.disableStaff(staffId); // Cập nhật role thành DISABLE
        return "redirect:/staff";
    }
    
    @PostMapping("/staff/export")
    public ResponseEntity<byte[]> exportStaffToExcel(
    		HttpSession session,
    		@RequestParam("staffsExport") List<String> staffIds) {
//		Chưa đăng nhập --> cook
//		Staff staff = (Staff) session.getAttribute("loggedInStaff");
//      if(staff == null) return "redirect:/login";
    	
    	byte[] excelData = new byte[0];;
    	
    	if (staffIds == null || staffIds.isEmpty()) {
    		excelData = new byte[0];
        }
    	else {
            try {
				excelData = staffService.exportStaffToExcelByListIds(staffIds);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	            
            // Thiết lập header cho response
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=danh_sach_nhan_vien.xlsx");
            
            // Trả về file Excel
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(excelData);
    }

}