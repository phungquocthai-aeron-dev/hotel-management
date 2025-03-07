package com.quantridulieu.hotelManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.quantridulieu.hotelManagement.entities.Promotion;
@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String> {
    @Query(value = "SELECT * FROM promotion WHERE promotion_name = :promotionName", nativeQuery = true)
    List<Promotion> findPromotionByName(@Param("promotionName") String promotionName);

    @Query(value = "SELECT * FROM promotion WHERE promotion_value = :promotionValue", nativeQuery = true)
    List<Promotion> findPromotionByValue(@Param("promotionValue") Float promotionValue);

    @Query(value = "SELECT * FROM promotion WHERE promotion_start <= :endDate AND promotion_end >= :startDate", nativeQuery = true)
    List<Promotion> findActivePromotions(@Param("startDate") java.sql.Date startDate, @Param("endDate") java.sql.Date endDate);
}