package com.quantridulieu.hotelManagement.controllers;

import java.io.IOException;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quantridulieu.hotelManagement.entities.Promotion;
import com.quantridulieu.hotelManagement.entities.Staff;
import com.quantridulieu.hotelManagement.services.PromotionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping("/promotion")
    public String listPromotions(Model model, HttpSession session) {
		Staff staff = (Staff) session.getAttribute("loggedInStaff");
		if(staff == null) return "redirect:/login";
		
        List<Promotion> promotions = (List<Promotion>) model.getAttribute("searchResult");
        if (promotions == null) promotions = promotionService.getAllPromotions();
        
        model.addAttribute("promotions", promotions);
		model.addAttribute("staff", staff);

        return "promotion";
    }

    @GetMapping("/promotion/search")
    public String searchPromotions(
    		HttpSession session,
            RedirectAttributes redirectAttributes,
            @RequestParam(required = false, defaultValue = "") String promotionId,
            @RequestParam(required = false, defaultValue = "") String promotionName,
            @RequestParam(required = false, defaultValue = "") String discountRange,
            @RequestParam(required = false, defaultValue = "") String startDate) {
		Staff staff = (Staff) session.getAttribute("loggedInStaff");
		if(staff == null) return "redirect:/login";
		
        List<Promotion> promotions = promotionService.searchPromotions(promotionId, promotionName, discountRange, startDate);
        redirectAttributes.addFlashAttribute("searchResult", promotions);
        return "redirect:/promotion";
    }


    @GetMapping("/promotion/detail")
    public String getPromotionById(HttpSession session,
    		@RequestParam("id") String promotionId, Model model) {
		Staff staff = (Staff) session.getAttribute("loggedInStaff");
		if(staff == null) return "redirect:/login";
		
        Promotion promotion = promotionService.getPromotionById(promotionId);
        model.addAttribute("promotion", promotion);
        return "promotion_detail";
    }

    @PostMapping("/promotion/save")
    public String savePromotion(@ModelAttribute Promotion promotion, RedirectAttributes redirectAttributes) {
        promotionService.save(promotion);
        redirectAttributes.addFlashAttribute("message", "Hiệu chỉnh khuyến mãi " + promotion.getPromotionId() + " thành công!");
        return "redirect:/promotion";
    }

    @PostMapping("/promotion/delete")
    public String deletePromotion(@RequestParam("id") String promotionId, RedirectAttributes redirectAttributes) {
        promotionService.delete(promotionId);
        redirectAttributes.addFlashAttribute("message", "Xóa khuyến mãi " + promotionId + " thành công!");
        return "redirect:/promotion";
    }

    @GetMapping("/promotion/add")
    public String showPromotionForm(@RequestParam(value = "id", required = false) String id, Model model) {
        Promotion promotion;
        
        if (id != null) {
            promotion = promotionService.getPromotionById(id);
        } else {
            promotion = new Promotion();
        }
        
        model.addAttribute("promotion", promotion);
        return "promotion_add";
    }
    
    @PostMapping("/promotion/export")
    public ResponseEntity<byte[]> exportPromotionToExcel(
            HttpSession session,
            @RequestParam("promotionsExport") List<String> promotionIds) {

//          Chưa đăng nhập --> cook
//          Staff staff = (Staff) session.getAttribute("loggedInStaff");
//          if (staff == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        byte[] excelData = new byte[0];

        if (promotionIds == null || promotionIds.isEmpty()) {
            excelData = new byte[0];
        } else {
            try {
            	excelData = promotionService.exportPromotionToExcelByListIds(promotionIds);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.internalServerError().build();
            }
        }

        // Thiết lập header cho response
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=danh_sach_khuyen_mai.xlsx");

        // Trả về file Excel
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(excelData);
    }


}