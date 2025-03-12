package com.quantridulieu.hotelManagement.controllers;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quantridulieu.hotelManagement.entities.Feedback;
import com.quantridulieu.hotelManagement.entities.Room;
import com.quantridulieu.hotelManagement.entities.RoomRental;
import com.quantridulieu.hotelManagement.services.FeedbackService;
import com.quantridulieu.hotelManagement.services.InvoiceService;
import com.quantridulieu.hotelManagement.services.RoomRentalService;
import com.quantridulieu.hotelManagement.services.RoomService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	RoomService roomService;
	
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	RoomRentalService roomRentalService;
	
	@Autowired
	FeedbackService feedbackService;
	
	@GetMapping(value = {"/", "/home"})
	public String home(Model model, HttpSession session) throws ParseException {

        
		double revenueData = invoiceService.getDailyRevenue(new Date()) / 1000000;
		double revenue = Math.round(revenueData * 100.0) / 100.0;
		
		List<RoomRental> givenRooms = roomRentalService.findRoomsByRentalDate(new Date());
		List<RoomRental> checkOutRooms = roomRentalService.findRoomsByCheckOutDate(new Date());
		List<Room> occupiedRooms = roomService.findOccupiedRooms();
		List<Room> allRooms = roomService.getAllRooms();
		List<Feedback> feedbacks = feedbackService.getAllFeedback();
		Collections.reverse(feedbacks);
		
		int totalAvailable = 0;
		int totalOccupied = 0;
		int totalMaintenance = 0;
		int listRoomSize = allRooms.size();
		for(int i = 0; i < listRoomSize; i++) {
			if(allRooms.get(i).getStatus().equals("Maintenance")) totalMaintenance++;
			else if(allRooms.get(i).getStatus().equals("Available")) totalAvailable++;
			else if(allRooms.get(i).getStatus().equals("Occupied")) totalOccupied++;
		}
		
		model.addAttribute("revenue", revenue);
		model.addAttribute("givenRooms", givenRooms);
		model.addAttribute("checkOutRooms", checkOutRooms);
		model.addAttribute("occupiedRooms", occupiedRooms);
		model.addAttribute("rooms", allRooms);
		model.addAttribute("totalAvailable", totalAvailable);
		model.addAttribute("totalOccupied", totalOccupied);
		model.addAttribute("totalMaintenance", totalMaintenance);
		model.addAttribute("totalRooms", listRoomSize);
		model.addAttribute("feedbacks", feedbacks);
		
		return "home";
	}
	
	@PostMapping(value = "/checkin")
	public String updateCheckIn(
			@RequestParam("rentId") String rentId) {
		
		roomRentalService.updateCheckIn(rentId, new Date());
	    return "redirect:/home";
	}
	
	@PostMapping(value = "/checkout")
	public String checkOutRoom(
			@RequestParam("roomId") String roomId,
			@RequestParam("status") String status) {
		
		roomService.updateStatus(roomId, status);
		
		return "redirect:/home";
	}
	
//	@PostMapping(value = "status")
//	public String updateStatus(
//			HttpServletRequest request,
//			@RequestParam("roomId") String roomId,
//			@RequestParam("status") String status) {
//		
//		roomService.updateStatus(roomId, status);
//		
//		String referer = request.getHeader("Referer");
//	    return "redirect:" + (referer != null ? referer : "/home");
//	}
}
