package com.quantridulieu.hotelManagement.repositories;

import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import com.quantridulieu.hotelManagement.entities.UseService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

@Repository
public interface UseServiceRepository extends JpaRepository<UseService, String> {
	@Query(value = "SELECT * FROM use_service WHERE us_date = :usDate", nativeQuery = true)
    List<UseService> findByUsDate(@Param("usDate") Date usDate);

    @Query(value = "SELECT * FROM use_service WHERE quantity = :quantity", nativeQuery = true)
    List<UseService> findByQuantity(@Param("quantity") int quantity);

    @Query(value = "SELECT * FROM use_service WHERE service_id = :serviceId", nativeQuery = true)
    List<UseService> findByServiceId(@Param("serviceId") String serviceId);

    @Query(value = "SELECT * FROM use_service WHERE rent_id = :rentId", nativeQuery = true)
    List<UseService> findByRentId(@Param("rentId") String rentId);

    @Query(value = "SELECT * FROM use_service WHERE promotion_id = :promotionId", nativeQuery = true)
    List<UseService> findByPromotionId(@Param("promotionId") String promotionId);

    @Query(value = "SELECT * FROM use_service WHERE us_id NOT IN (SELECT us_id FROM invoice)", nativeQuery = true)
    List<UseService> getAllUseServiceNotInInvoice();
    
    @Modifying
    @Procedure(name = "SearchUseService")
    List<UseService> searchUseService(
            @Param("p_us_id") String usId,
            @Param("p_quantity") Integer quantity,
            @Param("p_us_date") Date usDate,
            @Param("p_service_id") String serviceId,
            @Param("p_rent_id") String rentId,
            @Param("p_promotion_id") String promotionId
    );
}
