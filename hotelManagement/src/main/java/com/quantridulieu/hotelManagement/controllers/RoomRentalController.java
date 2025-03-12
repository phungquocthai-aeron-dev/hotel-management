package com.quantridulieu.hotelManagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.quantridulieu.hotelManagement.repositories.RoomRentalRepository;
import com.quantridulieu.hotelManagement.entities.RoomRental;
import java.util.List;

@Controller
public class RoomRentalController {

    @Autowired
    private RoomRentalRepository roomRentalRepository;

    @GetMapping(value = { "/", "/roomdental" })
    public String home(Model model) {
        List<RoomRental> rentals = roomRentalRepository.findAll();
        if (rentals == null) {
            rentals = new java.util.ArrayList<>();
        }
        for (RoomRental rental : rentals) {
            if (rental != null && rental.getRentalStatus() != null) {
                switch (rental.getRentalStatus()) {
                    case "Confirmed":
                        rental.setRentalStatus("Đã xác nhận");
                        break;
                    case "Pending":
                        rental.setRentalStatus("Đang chờ");
                        break;
                    case "Completed":
                        rental.setRentalStatus("Đã hoàn thành");
                        break;
                    // Thêm case "Canceled" nếu cần
                    case "Canceled":
                        rental.setRentalStatus("Đã hủy");
                        break;
                }
            }
        }
        model.addAttribute("rentals", rentals);
        return "roomdental";
    }
}