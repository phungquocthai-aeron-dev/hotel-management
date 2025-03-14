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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/") // Đảm bảo Spring nhận diện controller
public class LoginController {

    @Autowired
    private StaffService staffService;
    
    @PostMapping("/login")
    public String login(@RequestParam String phone, @RequestParam String password,
    		HttpSession session,
    		RedirectAttributes redirectAttributes) {
        Staff staff = staffService.getStaffByPhone(phone);
        
        if (staff != null && password.equals(staff.getPassword()) && !staff.getRole().equals("DISABLE")) { 
        	if(staff.getRole().equals("DISABLE")) {
        		redirectAttributes.addFlashAttribute("error", "Người dùng không tồn tại. Vui lòng đăng nhập bằng tài khoản khác!");
                 return "redirect:/login"; // Trả về lại trang login.jsp
        	}
 
        	// Lưu thông tin vào session
        	
            session.setAttribute("loggedInStaff", staff);
            session.setMaxInactiveInterval(5*60*60); 
            return "redirect:/home";
        } else {
            // Đăng nhập thất bại, trả về trang login với thông báo lỗi

        	if(staff == null) redirectAttributes.addFlashAttribute("error", "Số điện thoại không tồn tại. Vui lòng thử lại!");
        	else redirectAttributes.addFlashAttribute("error", "Mật khẩu không chính xác. Vui lòng thử lại!");
            return "redirect:/login"; // Trả về lại trang login.jsp
        }
    }
    @GetMapping("/login")
    public String showLoginPage(Model model) {
    	String error = (String)  model.getAttribute("error");
    	if(error != null) model.addAttribute("error", error);
        return "login"; // Chỉ ra tên của JSP hoặc view bạn muốn trả về
    }
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Hủy session
        return "redirect:/login"; // Chuyển hướng về trang đăng nhập
    }


}