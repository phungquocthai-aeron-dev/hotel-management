package com.quantridulieu.hotelManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quantridulieu.hotelManagement.entities.HotelService;

@Repository
public interface HotelServiceRepository extends JpaRepository<HotelService, String> {
    @Query(value = "SELECT * FROM service WHERE service_name = :serviceName", nativeQuery = true)
    List<HotelService> findServiceByName(@Param("serviceName") String serviceName);

    @Query(value = "SELECT * FROM service WHERE service_price = :servicePrice", nativeQuery = true)
    List<HotelService> findServiceByPrice(@Param("servicePrice") Float servicePrice);

    @Query(value = "SELECT * FROM service WHERE service_price < :maxPrice", nativeQuery = true)
    List<HotelService> findServicesByPriceLessThan(@Param("maxPrice") Float maxPrice);
    @Modifying
    @Transactional
    @Query(value = "CALL add_service(:serviceId, :serviceName, :servicePrice)", nativeQuery = true)
    void addServiceQuery(@Param("serviceId") String serviceId,
                         @Param("serviceName") String serviceName,
                         @Param("servicePrice") Float servicePrice);
}