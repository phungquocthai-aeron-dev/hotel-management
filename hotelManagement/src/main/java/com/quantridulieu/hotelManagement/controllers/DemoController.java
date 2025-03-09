//package com.quantridulieu.hotelManagement.controllers;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.quantridulieu.hotelManagement.entities.Customer;
//import com.quantridulieu.hotelManagement.services.CustomerService;
//
//@Controller
//public class DemoController {
//	@Autowired
//	CustomerService customerService;
//	
//	@GetMapping(value = {"/demo"})
//	public String demo(Model model) {
//		
//		Map<String, String> info = new HashMap<String, String>();
//		info.put("title", "HOME");
//		info.put("techology", "Spring Boot");
//		info.put("message", "Xin chào đây là website quản lý khách sạn :3."
//				+ " Tôi có thể giúp gì cho quý khách!");
//		info.put("linkTitle", "Xem danh sách khách hàng");
//		info.put("link", "all_customers");
//		model.addAllAttributes(info);
//		
//		return "demo";
//	}
//	
//	@GetMapping(value = {"/all_customers"})
//	public String room(Model model) {
//		
//		Map<String, String> info = new HashMap<String, String>();
//		info.put("title", "ROOM");
//		info.put("techology", "Spring Boot");
//		info.put("message", "Xin chào đây là website quản lý khách sạn :3."
//				+ " Đây là danh sách phòng của quý khách");
//		info.put("linkTitle", "Quay lại");
//		info.put("link", "demo");
//		model.addAllAttributes(info);
//		
//		List<Customer> customers = customerService.getAllCustomers();
//		model.addAttribute("customers" ,customers);
//		
//		return "demo";
//	}
//
//    @PostMapping("/saveCustomer")
//    public String saveCustomer(@ModelAttribute Customer customer) {
//        customerService.save(customer);
//        return "redirect:/all_customers";
//    }
//    
//    @PostMapping("/customer/delete")
//    public String deleteCustomer(@RequestParam("id") String customerId) {
//        customerService.delete(customerId);
//        return "redirect:/all_customers";
//    }
//    
//    @GetMapping("/export")
//    public ResponseEntity<byte[]> exportCustomerToExcel() {
//    	try {
//
//            byte[] excelData = customerService.exportCustomerToExcel();
//            
//            // Thiết lập header cho response
//            HttpHeaders headers = new HttpHeaders();
//            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=danh_sach_khach_hang.xlsx");
//            
//            // Trả về file Excel
//            return ResponseEntity.ok()
//                    .headers(headers)
//                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
//                    .body(excelData);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.internalServerError().build();
//        }
//    }
//}
