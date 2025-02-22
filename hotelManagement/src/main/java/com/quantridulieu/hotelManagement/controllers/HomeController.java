package com.quantridulieu.hotelManagement.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping(value = {"/home", "/"})
	public String home(Model model) {
		
		Map<String, String> info = new HashMap<String, String>();
		info.put("title", "HOME");
		info.put("techology", "Spring Boot");
		info.put("message", "Xin chào đây là website quản lý khách sạn :3."
				+ " Tôi có thể giúp gì cho quý khách!");
		info.put("linkTitle", "Xem phòng");
		info.put("link", "room");
		model.addAllAttributes(info);
		
		return "home";
	}
	
	@GetMapping(value = {"/room"})
	public String room(Model model) {
		
		Map<String, String> info = new HashMap<String, String>();
		info.put("title", "ROOM");
		info.put("techology", "Spring Boot");
		info.put("message", "Xin chào đây là website quản lý khách sạn :3."
				+ " Đây là danh sách phòng của quý khách");
		info.put("linkTitle", "Trang chủ");
		info.put("link", "home");
		model.addAllAttributes(info);
		
		return "home";
	}
}
