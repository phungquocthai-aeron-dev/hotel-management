package com.quantridulieu.hotelManagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.quantridulieu.hotelManagement.repositories.HotelServiceRepository;

@Controller
@RequestMapping("/service") // Đảm bảo có đúng đường dẫn
public class HotelServiceController {

    @Autowired
    private HotelServiceRepository hotelServiceRepository;

    @GetMapping
    public String getAllServices(Model model) {
        model.addAttribute("services", hotelServiceRepository.findAll());
        return "service"; // Trả về file service.jsp
    }

    @PostMapping("/add")
    @ResponseBody
    public String addService(@RequestParam String serviceId,
                             @RequestParam String serviceName,
                             @RequestParam Float servicePrice) {
        try {
            hotelServiceRepository.addServiceQuery(serviceId, serviceName, servicePrice);
            return "Dịch vụ đã được thêm thành công!";
        } catch (Exception e) {
            return "Lỗi khi thêm dịch vụ: " + e.getMessage();
        }
    }
}
