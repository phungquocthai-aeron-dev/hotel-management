package com.quantridulieu.hotelManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantridulieu.hotelManagement.entities.Promotion;
import com.quantridulieu.hotelManagement.repositories.PromotionRepository;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

	@Autowired
	ExcelExportUtil excelExportUtil;
	
	// Xuất file excel
	public byte[] exportPromotionToExcel() throws IOException {
        return excelExportUtil.exportToExcel(promotionRepository.findAll(), null, "Danh sách khuyến mãi");
    }
	
	public byte[] exportPromotionToExcelByListIds(List<String> ids) throws IOException {
	    return excelExportUtil.exportToExcel(promotionRepository.findByPromotionIds(ids), null, "Danh sách khuyến mãi");
	}

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public void save(Promotion promotion) {
        if (promotion.getPromotionId() == null) promotion.setPromotionId(generateId());
        promotionRepository.save(promotion);
    }

    public void delete(String id) {
        promotionRepository.deleteById(id);
    }

    public Promotion getPromotionById(String id) {
        return promotionRepository.findById(id).orElseThrow();
    }

    public List<Promotion> findByName(String name) {
        return promotionRepository.findPromotionByName(name);
    }

    public List<Promotion> findByValue(Float value) {
        return promotionRepository.findPromotionByValue(value);
    }

    public List<Promotion> findActivePromotions(Date startDate, Date endDate) {
        return promotionRepository.findActivePromotions(startDate, endDate);
    }
    
    public List<Promotion> findActivePromotions() {
    	return promotionRepository.findActivePromotions();
    }
    
    @Transactional
    public void update(Promotion promotion) {
        // Kiểm tra ID có hợp lệ không
        if (promotion.getPromotionId() == null || promotion.getPromotionId().isEmpty()) {
            throw new IllegalArgumentException("ID khuyến mãi không được để trống!");
        }

        // Tìm khuyến mãi trong database
        Promotion existingPromotion = promotionRepository.findById(promotion.getPromotionId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khuyến mãi với ID: " + promotion.getPromotionId()));

        // Cập nhật thông tin
        existingPromotion.setPromotionName(promotion.getPromotionName());
        existingPromotion.setPromotionDescription(promotion.getPromotionDescription());
        existingPromotion.setPromotionStart(promotion.getPromotionStart());
        existingPromotion.setPromotionEnd(promotion.getPromotionEnd());
        existingPromotion.setPromotionValue(promotion.getPromotionValue());

        // Lưu thay đổi
        promotionRepository.save(existingPromotion);
    }

    // P00001
    private String generateId() {
        Long count = promotionRepository.count();
        return String.format("P%05d", count + 1);
    }
    
    
    @Transactional
    public List<Promotion> searchPromotions(String promotionId, String promotionName, String discountRange, String startDate) {
        return promotionRepository.searchPromotion(
            (promotionId == null || promotionId.isEmpty()) ? null : promotionId,
            (promotionName == null || promotionName.isEmpty()) ? null : promotionName,
            (discountRange == null || discountRange.isEmpty()) ? null : discountRange,
            (startDate == null || startDate.isEmpty()) ? null : LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );
    }



}