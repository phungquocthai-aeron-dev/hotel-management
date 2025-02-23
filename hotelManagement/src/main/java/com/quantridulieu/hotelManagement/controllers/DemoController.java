package com.quantridulieu.hotelManagement.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quantridulieu.hotelManagement.entities.Customer;
import com.quantridulieu.hotelManagement.services.CustomerService;

@Controller
public class DemoController {
	@Autowired
	CustomerService customerService;
	
	@GetMapping(value = {"/demo"})
	public String demo(Model model) {
		
		Map<String, String> info = new HashMap<String, String>();
		info.put("title", "HOME");
		info.put("techology", "Spring Boot");
		info.put("message", "Xin chào đây là website quản lý khách sạn :3."
				+ " Tôi có thể giúp gì cho quý khách!");
		info.put("linkTitle", "Xem danh sách khách hàng");
		info.put("link", "all_customers");
		model.addAllAttributes(info);
		
		return "demo";
	}
	
	@GetMapping(value = {"/all_customers"})
	public String room(Model model) {
		
		Map<String, String> info = new HashMap<String, String>();
		info.put("title", "ROOM");
		info.put("techology", "Spring Boot");
		info.put("message", "Xin chào đây là website quản lý khách sạn :3."
				+ " Đây là danh sách phòng của quý khách");
		info.put("linkTitle", "Quay lại");
		info.put("link", "demo");
		model.addAllAttributes(info);
		
		List<Customer> customers = customerService.getAllCustomers();
		model.addAttribute("customers" ,customers);
		
		return "demo";
	}

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/all_customers";
    }
    
    @PostMapping("/customer/delete")
    public String deleteCustomer(@RequestParam("id") String customerId) {
        customerService.delete(customerId);
        return "redirect:/all_customers";
    }

    @GetMapping("/customer")
    public String getCustomerById(@RequestParam("id") String customerId, Model model) {
        Customer customer = customerService.getCustomerById(customerId);
        model.addAttribute("customer", customer);
        return "customer_detail";
    }
}
