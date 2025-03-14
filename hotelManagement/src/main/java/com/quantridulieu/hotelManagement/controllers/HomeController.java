package com.quantridulieu.hotelManagement.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quantridulieu.hotelManagement.entities.Feedback;
import com.quantridulieu.hotelManagement.entities.Room;
import com.quantridulieu.hotelManagement.entities.RoomRental;
import com.quantridulieu.hotelManagement.entities.Staff;
import com.quantridulieu.hotelManagement.entities.StatisticsExport;
import com.quantridulieu.hotelManagement.services.ExcelExportUtil;
import com.quantridulieu.hotelManagement.services.FeedbackService;
import com.quantridulieu.hotelManagement.services.InvoiceService;
import com.quantridulieu.hotelManagement.services.MaintenanceService;
import com.quantridulieu.hotelManagement.services.RoomRentalService;
import com.quantridulieu.hotelManagement.services.RoomService;

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
	
	@Autowired
	MaintenanceService maintenanceService;
	
	@Autowired
	ExcelExportUtil excelExportUtil;
	
	@GetMapping(value = {"/", "/home"})
	public String home(Model model, HttpSession session) throws ParseException {
        Staff staff = (Staff)session.getAttribute("loggedInStaff");
        
//		Chưa đăng nhập --> cook
        if(staff == null) return "redirect:/login";
        
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
		model.addAttribute("staff", staff);

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
	
	@GetMapping(value = "/statistics")
	public String getStatisticPage(
			Model model, HttpSession session,
			@RequestParam(name = "year", defaultValue = "0") int year) {
        Staff staff = (Staff)session.getAttribute("loggedInStaff");
        
//		Chưa đăng nhập --> cook
        if(staff == null) return "redirect:/login";
        
		if(year == 0) {
			year = LocalDate.now().getYear();
		}
		System.out.println(year);
		
		List<Double> dataRevenues = new ArrayList<Double>();
		List<Double> dataExpenses = new ArrayList<Double>();
		List<Double> dataProfits = new ArrayList<Double>();
		
		for(int i = 1; i <= 12; i++) {
			double revenue = invoiceService.getMonthlyRevenue(i, year);
			double revenueRounded = Math.round(revenue * 100.0) / 100.0;
			dataRevenues.add(revenueRounded);
			
			double expense = maintenanceService.getMonthlyExpenses(i, year);
			double expenseRounded = Math.round(expense * 100.0) / 100.0;
			dataExpenses.add(expenseRounded);
			
			dataProfits.add(revenueRounded - expenseRounded);
			
		}
		
		model.addAttribute("dataRevenues", dataRevenues);
		model.addAttribute("dataExpenses", dataExpenses);
		model.addAttribute("dataProfits", dataProfits);
		model.addAttribute("year", year);
		
		return "statistics";
	}
	
	 @PostMapping(value = "/statistic/export")
	    public ResponseEntity<byte[]> exportCustomerToExcel(
	    		HttpSession session,
	    		@RequestParam("profits") List<Double> profits,
	    		@RequestParam("expenses") List<Double> expenses,
	    		@RequestParam("revenues") List<Double> revenues) {
//			Chưa đăng nhập --> cook
//			Staff staff = (Staff) session.getAttribute("loggedInStaff");
//	      if(staff == null) return "redirect:/login";
	    	List<StatisticsExport> list = new ArrayList<StatisticsExport>();
	    	for(int i = 0; i < 12; i++) {
	    		StatisticsExport item = new StatisticsExport(i+1, revenues.get(i), expenses.get(i), profits.get(i));
	    		list.add(item);
	    	}
	    	
	    	byte[] excelData = new byte[0];;
	    	
	    	if (list == null || list.isEmpty()) {
	    		excelData = new byte[0];
	        }
	    	else {
	            try {
					excelData = excelExportUtil.exportToExcel(list, null, "Thống kê thu chi và lợi nhuận");

				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    	            
	            // Thiết lập header cho response
	            HttpHeaders headers = new HttpHeaders();
	            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=thong_ke_thu_chi_loi_nhuan.xlsx");
	            
	            // Trả về file Excel
	            return ResponseEntity.ok()
	                    .headers(headers)
	                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
	                    .body(excelData);
	    }
}