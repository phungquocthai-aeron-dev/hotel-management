package com.quantridulieu.hotelManagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quantridulieu.hotelManagement.entities.Staff;
import com.quantridulieu.hotelManagement.services.StaffService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class StaffController {
    @Autowired
    private StaffService staffService;

    // Xung đột có hai cái get /staff -> ko biết vô cái nào -> đổi tên 1 trong 2
    @GetMapping("/staff")
    public String showStaff(Model model) {
        Long totalStaff = staffService.getTotalStaff();
        model.addAttribute("totalStaff", totalStaff != null ? totalStaff : 0L);
        List<Staff> staffList = staffService.getAllStaff();
        model.addAttribute("staffList", staffList);
        
        return "staff"; // Tên file JSP của bạn
    }
}



