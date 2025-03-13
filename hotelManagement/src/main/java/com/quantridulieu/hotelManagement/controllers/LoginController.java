package com.quantridulieu.hotelManagement.controllers;



import com.quantridulieu.hotelManagement.entities.Staff;
import com.quantridulieu.hotelManagement.services.StaffService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/") // Đảm bảo Spring nhận diện controller
public class LoginController {

    @Autowired
    private StaffService staffService;
    
    @PostMapping("/login")
    public String login(@RequestParam String phone, @RequestParam String password, Model model, HttpSession session) {
        Staff staff = staffService.getStaffByPhone(phone);
        if (staff != null && password.equals(staff.getPassword())) { 
        	 // Lưu thông tin vào session
            session.setAttribute("loggedInStaff", staff);
            session.setMaxInactiveInterval(5*60*60);
            return "redirect:/home";
        } else {
            // Đăng nhập thất bại, trả về trang login với thông báo lỗi
            model.addAttribute("error", "Sai số điện thoại hoặc mật khẩu. Vui lòng thử lại!");
            return "login"; // Trả về lại trang login.jsp
        }
    }
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Chỉ ra tên của JSP hoặc view bạn muốn trả về
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Hủy session
        return "redirect:/login"; // Chuyển hướng về trang đăng nhập
    }

}