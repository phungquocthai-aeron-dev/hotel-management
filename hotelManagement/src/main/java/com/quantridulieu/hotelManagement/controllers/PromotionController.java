package com.quantridulieu.hotelManagement.controllers;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quantridulieu.hotelManagement.entities.Promotion;
import com.quantridulieu.hotelManagement.entities.Staff;
import com.quantridulieu.hotelManagement.services.PromotionService;
import com.quantridulieu.hotelManagement.services.StaffService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PromotionController {

	@Autowired
	private PromotionService promotionService;

	@Autowired
	private StaffService staffService;

	@GetMapping("/promotion")
	public String listPromotions(Model model) {
		List<Promotion> promotions = (List<Promotion>) model.getAttribute("searchResult");
		if (promotions == null)
			promotions = promotionService.getAllPromotions();

		Map<String, String> errors = (Map<String, String>) model.getAttribute("error");
		if (errors != null) {
			model.addAttribute("error", errors);
		}
		model.addAttribute("promotions", promotions);
		return "promotion";
	}

	@GetMapping("/promotion/search")
	public String searchPromotions(RedirectAttributes redirectAttributes,
			@RequestParam(required = false, defaultValue = "") String promotionId,
			@RequestParam(required = false, defaultValue = "") String promotionName,
			@RequestParam(required = false, defaultValue = "") String discountRange,
			@RequestParam(required = false, defaultValue = "") String startDate) {

		List<Promotion> promotions = promotionService.searchPromotions(promotionId, promotionName, discountRange,
				startDate);
		redirectAttributes.addFlashAttribute("searchResult", promotions);
		return "redirect:/promotion";
	}

	@GetMapping("/promotion/detail")
	public String getPromotionById(@RequestParam("id") String promotionId, Model model) {
		Promotion promotion = promotionService.getPromotionById(promotionId);
		model.addAttribute("promotion", promotion);
		return "promotion_detail";
	}

	@PostMapping("/promotion/save")
	public String savePromotion(@ModelAttribute Promotion promotion, 
	                            RedirectAttributes redirectAttributes) {

	    if (promotion.getPromotionId() == null || promotion.getPromotionId().isEmpty()) {
	        // Nếu không có ID -> Tạo mới khuyến mãi
	        promotionService.save(promotion);
	        redirectAttributes.addFlashAttribute("message", "Tạo mới khuyến mãi thành công!");
	    } else {
	        // Nếu có ID -> Cập nhật khuyến mãi
	        Promotion existingPromotion = promotionService.getPromotionById(promotion.getPromotionId());
	        if (existingPromotion != null) {
	            promotionService.update(promotion); // Gọi hàm update thay vì save
	            redirectAttributes.addFlashAttribute("message",
	                    "Cập nhật khuyến mãi " + promotion.getPromotionId() + " thành công!");
	        } else {
	            redirectAttributes.addFlashAttribute("error",
	                    "Không tìm thấy khuyến mãi với ID: " + promotion.getPromotionId());
	        }
	    }

	    return "redirect:/promotion";
	}



	@PostMapping("/promotion/delete")
	public String deletePromotion(HttpSession session, @RequestParam("id") String promotionId,
			RedirectAttributes redirectAttributes) {

		Staff staff = (Staff) session.getAttribute("loggedInStaff");
		if (staff == null)
			return "redirect:/login";
//		Không phải admin --> cook
		if (!staff.getRole().equals("ADMIN")) {
			Map<String, String> errors = new HashMap<>();
			errors.put("error", "Chỉ admin mới xóa được khuyến mãi!");
			redirectAttributes.addFlashAttribute("error", errors);
			return "redirect:/promotion";
		}

		promotionService.delete(promotionId);
		redirectAttributes.addFlashAttribute("message", "Xóa khuyến mãi " + promotionId + " thành công!");
		return "redirect:/promotion";
	}

	@GetMapping("/promotion/add")
	public String showPromotionForm(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		Staff staff = (Staff) session.getAttribute("loggedInStaff");
		if (staff == null)
			return "redirect:/login";
//		Không phải admin --> cook
		if (!staff.getRole().equals("ADMIN")) {
			Map<String, String> errors = new HashMap<>();
			errors.put("error", "Chỉ admin mới thêm được khuyến mãi!");
			redirectAttributes.addFlashAttribute("message", errors);
			return "redirect:/promotion";
		}
		Promotion promotion = new Promotion();
		model.addAttribute("promotion", promotion);
		return "promotion_add";
	}

	@PostMapping("/promotion/add")
	public String updatePromotion(HttpSession session, @ModelAttribute Promotion promotion,
			RedirectAttributes redirectAttributes, Model model) {
		// Kiểm tra đăng nhập
		Staff staff = (Staff) session.getAttribute("loggedInStaff");
		if (staff == null) {
			return "redirect:/login";
		}

		// Kiểm tra quyền admin
		if (!"ADMIN".equals(staff.getRole())) {
			Map<String, String> errors = new HashMap<>();
			errors.put("error", "Chỉ admin mới có quyền chỉnh sửa khuyến mãi!");
			redirectAttributes.addFlashAttribute("message", errors);
			return "redirect:/promotion";
		}

		// Kiểm tra xem ID có hợp lệ không
		Promotion existingPromotion = promotionService.getPromotionById(promotion.getPromotionId());
		if (existingPromotion == null) {
			redirectAttributes.addFlashAttribute("error", "Khuyến mãi không tồn tại!");
			return "redirect:/promotion";
		}

		// Trả về trang chỉnh sửa với Promotion cần cập nhật
		model.addAttribute("promotion", existingPromotion);
		return "promotion_add";
	}

	@PostMapping("/promotion/export")
	public ResponseEntity<byte[]> exportPromotionToExcel(HttpSession session,
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
		return ResponseEntity.ok().headers(headers)
				.contentType(
						MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
				.body(excelData);
	}

}