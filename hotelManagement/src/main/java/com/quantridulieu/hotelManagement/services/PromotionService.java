package com.quantridulieu.hotelManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantridulieu.hotelManagement.entities.Promotion;
import com.quantridulieu.hotelManagement.repositories.PromotionRepository;

import java.sql.Date;
import java.util.List;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

   
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

    // P00001
    private String generateId() {
        Long count = promotionRepository.count();
        return String.format("P%05d", count + 1);
    }
}