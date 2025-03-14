package com.quantridulieu.hotelManagement.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import com.quantridulieu.hotelManagement.entities.Customer;
import com.quantridulieu.hotelManagement.entities.Staff;
import com.quantridulieu.hotelManagement.services.CustomerService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@GetMapping(value = "/customer")
	public String getPage(Model model, HttpSession session) {
		Staff staff = (Staff) session.getAttribute("loggedInStaff");
//		Chưa đăng nhập --> cook
//      if(staff == null) return "redirect:/login";
		
		List<Customer> list = (List<Customer>) model.getAttribute("searchResult");
		
		// Nếu có dữ liệu đỗ về từ search thì bỏ qua
		// Không có thì xu lấy tất cả
		if(list == null) list = customerService.getAllCustomers();
		
		model.addAttribute("customers", list);
		model.addAttribute("staff", staff);
		
		return "customer";
	}
	
	 @GetMapping("/customer/search")
	    public String searchCustomer(
	    		RedirectAttributes redirectAttributes,
	    		@RequestParam(required = false, defaultValue = "") String customerId,
	            @RequestParam(required = false, defaultValue = "") String customerName,
	            @RequestParam(required = false, defaultValue = "") String customerPhone,
	            @RequestParam(required = false, defaultValue = "") String address) {
	    	
//			Chưa đăng nhập --> cook
//			Staff staff = (Staff) session.getAttribute("loggedInStaff");
//	      if(staff == null) return "redirect:/login";
	    	
	    	List<Customer> customers = customerService.searchCustomers(
	                customerId,
	                customerName,
	                customerPhone,
	                address
	            );
	    	
	    	redirectAttributes.addFlashAttribute("searchResult", customers);
	    	return "redirect:/customer";
	    }
	
    @GetMapping(value = "/customer/details")
    public String getCustomerById(@RequestParam("id") String customerId, Model model, HttpSession session) {
        Staff staff = (Staff)session.getAttribute("loggedInStaff");
        
//		Chưa đăng nhập --> cook
//        if(staff == null) return "redirect:/login";
//		Không phải admin --> cook
//		if(!staff.getRole().equals("ADMIN")) return "redirect:/home";
        Customer customer = customerService.getCustomerById(customerId);
        model.addAttribute("customer", customer);
        model.addAttribute("staff", staff);
        
		Map<String, String> errors = (Map<String, String>) model.getAttribute("errors");
		if(errors != null) {
			model.addAttribute("errors", errors);
		}
		
        return "update_customer";
    }
    
    @PostMapping("/customer/update")
    public String getCustomeUpdate(
    		@ModelAttribute Customer customer,
    		RedirectAttributes redirectAttributes) {
    	String regex = "^[0-9]{10}$";
    	boolean isValid = Pattern.matches(regex, customer.getCustomerPhone());
   
        if(isValid) customerService.save(customer);  
        else {
        	Map<String, String> errors = new HashMap<>();
        	errors.put("phone", "Số điện thoại không đúng định dạng");
        	redirectAttributes.addFlashAttribute("errors", errors);

        	return "redirect:/customer/details?id=" + customer.getCustomerId();
        }
        
        return "redirect:/customer";
    }
    
    @PostMapping("/customer/delete")
    public String deleteCustomer(@RequestParam("id") String customerId) {
      customerService.delete(customerId);
      return "redirect:/customer";
    }
    
    @PostMapping("/customer/export")
    public ResponseEntity<byte[]> exportCustomerToExcel(
    		HttpSession session,
    		@RequestParam("customersExport") List<String> customerIds) {
//		Chưa đăng nhập --> cook
//		Staff staff = (Staff) session.getAttribute("loggedInStaff");
//      if(staff == null) return "redirect:/login";
    	
    	byte[] excelData = new byte[0];;
    	
    	if (customerIds == null || customerIds.isEmpty()) {
    		excelData = new byte[0];
        }
    	else {
            try {
				excelData = customerService.exportCustomerToExcelByListIds(customerIds);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	            
            // Thiết lập header cho response
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=danh_sach_khach_hang.xlsx");
            
            // Trả về file Excel
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(excelData);
    }
    
}
