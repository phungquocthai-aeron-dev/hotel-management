package com.quantridulieu.hotelManagement.controllers;

import com.quantridulieu.hotelManagement.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // Chỉ ra tên của JSP hoặc view bạn muốn trả về
    }

}