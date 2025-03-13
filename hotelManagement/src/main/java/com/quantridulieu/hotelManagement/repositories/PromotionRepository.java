package com.quantridulieu.hotelManagement.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.quantridulieu.hotelManagement.entities.Promotion;
@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String> {
    @Query(value = "SELECT * FROM promotion WHERE promotion_name = :promotionName", nativeQuery = true)
    List<Promotion> findPromotionByName(@Param("promotionName") String promotionName);

    @Query(value = "SELECT * FROM promotion WHERE promotion_id IN (:promotionids)", nativeQuery = true)
    List<Promotion> findByPromotionIds(@Param("promotionids") List<String> promotionids);

    
    @Query(value = "SELECT * FROM promotion WHERE promotion_value = :promotionValue", nativeQuery = true)
    List<Promotion> findPromotionByValue(@Param("promotionValue") Float promotionValue);

    @Query(value = "SELECT * FROM promotion WHERE promotion_start <= :endDate AND promotion_end >= :startDate", nativeQuery = true)
    List<Promotion> findActivePromotions(@Param("startDate") java.sql.Date startDate, @Param("endDate") java.sql.Date endDate);
    
    @Modifying
    @Procedure(name = "SearchPromotion")
    List<Promotion> searchPromotion(
        @Param("p_promotion_id") String promotionId,
        @Param("p_promotion_name") String promotionName,
        @Param("p_discountRange") String discountRange,
        @Param("p_startDate") LocalDate startDate
    );


}