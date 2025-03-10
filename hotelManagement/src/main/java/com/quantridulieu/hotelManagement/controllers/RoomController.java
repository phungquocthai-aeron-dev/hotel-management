package com.quantridulieu.hotelManagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.quantridulieu.hotelManagement.repositories.RoomRepository;

@Controller
public class RoomController {
    
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping(value = {"/", "/room"})
    public String home(Model model) {
        // Lấy danh sách tất cả các phòng từ cơ sở dữ liệu
        model.addAttribute("rooms", roomRepository.findAll());
        return "room"; // Trả về file room.jsp
    }
}