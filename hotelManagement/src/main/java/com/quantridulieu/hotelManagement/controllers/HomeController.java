package com.quantridulieu.hotelManagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping(value = {"/", "/home"})
	public String home() {
		return "home";
	}
}
