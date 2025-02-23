package com.quantridulieu.hotelManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quantridulieu.hotelManagement.entities.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String> {}
