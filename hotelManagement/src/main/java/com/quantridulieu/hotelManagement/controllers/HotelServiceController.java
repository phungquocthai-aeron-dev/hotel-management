package com.quantridulieu.hotelManagement.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quantridulieu.hotelManagement.entities.Customer;
import com.quantridulieu.hotelManagement.entities.HotelService;
import com.quantridulieu.hotelManagement.entities.Staff;
import com.quantridulieu.hotelManagement.repositories.HotelServiceRepository;
import com.quantridulieu.hotelManagement.services.HotelServiceService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/service")
public class HotelServiceController {

    @Autowired
    private HotelServiceRepository hotelServiceRepository;

    @Autowired
    private HotelServiceService hotelServiceService;

    @GetMapping
    public String getAllServices(Model model, HttpSession session) {
        Staff staff = (Staff)session.getAttribute("loggedInStaff");
        
//		Chưa đăng nhập --> cook
        if(staff == null) return "redirect:/login";
        
            model.addAttribute("services", hotelServiceRepository.findAll());
            List<HotelService> list = (List<HotelService>) model.getAttribute("searchResult");
    		
    		// Nếu có dữ liệu đỗ về từ search thì bỏ qua
    		// Không có thì xu lấy tất cả
    		if(list == null) list = hotelServiceService.getAllServices();
       
        model.addAttribute("services", list);
		model.addAttribute("staff", staff);

        return "service"; // Trả về file service.jsp
    }

    @PostMapping("/save")
    public String saveService(@ModelAttribute HotelService service, RedirectAttributes redirectAttributes) {
        if (hotelServiceRepository.existsById(service.getServiceId())) {
            redirectAttributes.addFlashAttribute("error", "ID dịch vụ đã tồn tại, vui lòng chọn ID khác.");
            return "redirect:/service";
        }
        hotelServiceRepository.save(service);
        return "redirect:/service";
    }

    @GetMapping(value = "/details")
    public String getServiceById(@RequestParam("id") String serviceId, Model model, HttpSession session) {
        Staff staff = (Staff)session.getAttribute("loggedInStaff");
        
//		Chưa đăng nhập --> cook
        if(staff == null) return "redirect:/login";
        
    	HotelService service = hotelServiceService.getServiceById(serviceId);
        model.addAttribute("service", service);

        Map<String, String> errors = (Map<String, String>) model.getAttribute("errors");
        if (errors != null) {
            model.addAttribute("errors", errors);
        }

        return "update_service"; // Trả về trang update_service.jsp
    }

    @PostMapping("/update")
    public String updateService(
            @ModelAttribute HotelService service,
            RedirectAttributes redirectAttributes) {
        if (service.getServicePrice() == null || service.getServicePrice() < 0) {
            Map<String, String> errors = new HashMap<>();
            errors.put("price", "Giá dịch vụ không hợp lệ");
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:/service/details?id=" + service.getServiceId();
        }

        hotelServiceService.save(service);
        return "redirect:/service";
    }
    @PostMapping("/delete")
    public String deleteService(@RequestParam("id") String serviceId) {
      hotelServiceService.delete(serviceId);
      return "redirect:/service";
    }
    
    @GetMapping("/search")
    public String searchService(
    		HttpSession session,
            RedirectAttributes redirectAttributes,
            @RequestParam(required = false, defaultValue = "") String serviceId,
            @RequestParam(required = false, defaultValue = "") String serviceName,
            @RequestParam(required = false, defaultValue = "") String servicePrice) {
        Staff staff = (Staff)session.getAttribute("loggedInStaff");
        
//		Chưa đăng nhập --> cook
        if(staff == null) return "redirect:/login";
        
        List<HotelService> services = hotelServiceService.searchServices(
                serviceId,
                serviceName,
                servicePrice.isEmpty() ? null : Float.parseFloat(servicePrice) // Chuyển đổi giá sang Float
        );

        redirectAttributes.addFlashAttribute("searchResult", services);
        return "redirect:/service";
    }

    
    @PostMapping("/export")
    public ResponseEntity<byte[]> exportHotelServiceToExcel(
    		@RequestParam("servicesExport") List<String> serviceIds) {

    	
    	byte[] excelData = new byte[0];;
    	
    	if (serviceIds == null || serviceIds.isEmpty()) {
    		excelData = new byte[0];
        }
    	else {
            try {
				excelData = hotelServiceService.exportHotelServiceToExcelByListIds(serviceIds);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	            
            // Thiết lập header cho response
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=danh_sach_dich_vu.xlsx");
            
            // Trả về file Excel
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(excelData);
    }

}