package com.quantridulieu.hotelManagement.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quantridulieu.hotelManagement.entities.HotelService;
import com.quantridulieu.hotelManagement.entities.Promotion;
import com.quantridulieu.hotelManagement.entities.RoomRental;
import com.quantridulieu.hotelManagement.entities.Staff;
import com.quantridulieu.hotelManagement.entities.UseService;
import com.quantridulieu.hotelManagement.repositories.HotelServiceRepository;
import com.quantridulieu.hotelManagement.services.HotelServiceService;
import com.quantridulieu.hotelManagement.services.PromotionService;
import com.quantridulieu.hotelManagement.services.RoomRentalService;
import com.quantridulieu.hotelManagement.services.RoomService;
import com.quantridulieu.hotelManagement.services.UseServiceService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/us")
public class UserServiceController {
	
	@Autowired
	private UseServiceService useServiceService;
    
    @Autowired
    private PromotionService promotionService;
    
    @Autowired
    private RoomRentalService roomRentalService;

    @Autowired
    private HotelServiceService hotelServiceService;
    

    @GetMapping
    public String getAllServices(Model model, HttpSession session) {
        Staff staff = (Staff)session.getAttribute("loggedInStaff");
        
//		Chưa đăng nhập --> cook
        if(staff == null) return "redirect:/login";
        
            List<UseService> list = (List<UseService>) model.getAttribute("searchResult");
    		
    		// Nếu có dữ liệu đỗ về từ search thì bỏ qua
    		// Không có thì xu lấy tất cả
    		if(list == null) list = useServiceService.getAllUseServices();
       
    	String error = (String) model.getAttribute("error");
    	if(error != null) model.addAttribute("error", error);
    	
    	List<HotelService> services = hotelServiceService.getAllServices();
    	List<Promotion> promotions = promotionService.findActivePromotions();
    	List<RoomRental> rents = roomRentalService.getAllRoomRentals();
    	
    	model.addAttribute("services", services);
    	model.addAttribute("promotions", promotions);
    	model.addAttribute("rents", rents);
        model.addAttribute("usServices", list);
		model.addAttribute("staff", staff);

        return "useService"; // Trả về file service.jsp
    }

    @PostMapping("/save")
    public String save(@ModelAttribute UseService us,
    		@RequestParam(value = "serviceId", required = true) String serviceId,
            @RequestParam(value = "rentId", required = true) String rentId,
            @RequestParam(value = "promotionId", required = false, defaultValue = "") String promotionId,
    		RedirectAttributes redirectAttributes) {
    	System.out.println(us.getQuantity());
        try {
        	Promotion promotion = null;
        	HotelService hotelService = hotelServiceService.getServiceById(serviceId);
        	if(!promotionId.isBlank()) promotion = promotionService.getPromotionById(promotionId);
        	RoomRental roomRental = roomRentalService.getRentalById(rentId);
        	        	
        	us.setPromotion(promotion);
        	us.setService(hotelService);
        	us.setRoomRental(roomRental);
        	
        	useServiceService.save(us);
        	
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("error", "Đăng ký giao dịch thất bại.");
            return "redirect:/us";
        }
        return "redirect:/us";
    }

    @PostMapping("/delete")
    public String deleteService(@RequestParam("id") String id) {
      useServiceService.delete(id);
      return "redirect:/us";
    }
    
    @GetMapping("/search")
    public String searchUseService(
    		HttpSession session,
            RedirectAttributes redirectAttributes,
            @RequestParam(value = "usId", required = false) String usId,
            @RequestParam(value = "quantity", required = false) Integer quantity,
            @RequestParam(value = "usDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date usDate,
            @RequestParam(value = "serviceId", required = false) String serviceId,
            @RequestParam(value="rentId", required = false) String rentId,
            @RequestParam(value="promotionId", required = false) String promotionId) {
    	
        Staff staff = (Staff)session.getAttribute("loggedInStaff");
        
        if(staff == null) return "redirect:/login";
        
        List<UseService> uss = useServiceService.searchUseService(usId, quantity, usDate, serviceId, rentId, promotionId);
        
        redirectAttributes.addFlashAttribute("searchResult", uss);
        return "redirect:/us";
    }

    
    @PostMapping("/export")
    public ResponseEntity<byte[]> exportHotelServiceToExcel(
    		@RequestParam("usExport") List<String> usIds) {

    	
    	byte[] excelData = new byte[0];;
    	
    	if (usIds == null || usIds.isEmpty()) {
    		excelData = new byte[0];
        }
    	else {
            try {
				excelData = useServiceService.exportUSToExcelByIds(usIds);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	            
            // Thiết lập header cho response
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=danh_sach_dang_ky_dich_vu.xlsx");
            
            // Trả về file Excel
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(excelData);
    }

}