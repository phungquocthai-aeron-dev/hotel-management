package com.quantridulieu.hotelManagement.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quantridulieu.hotelManagement.entities.Customer;
import com.quantridulieu.hotelManagement.entities.Maintenance;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, String> {
    
    @Query(value = "SELECT * FROM maintenance WHERE mtn_status = :status", nativeQuery = true)
    List<Maintenance> findByStatus(@Param("status") String status);
    
    @Query(value = "SELECT * FROM maintenance WHERE mtn_description = :description", nativeQuery = true)
    List<Maintenance> findByDescription(@Param("description") String description);
    
    @Query(value = "SELECT * FROM maintenance WHERE mtn_fee = :fee", nativeQuery = true)
    List<Maintenance> findByFee(@Param("fee") String fee);
    
    @Query(value = "SELECT * FROM maintenance WHERE room_id = :roomId", nativeQuery = true)
    Maintenance findByRoomId(@Param("roomId") String roomId);
    
    @Query(value = "SELECT * FROM maintenance WHERE mtn_id IN (:maintenanceIds)", nativeQuery = true)
    List<Maintenance> findByMaintenanceIds(@Param("maintenanceIds") List<String> maintenanceIds);
    
    @Query(value = "SELECT * FROM maintenance WHERE staff_id = :staffId", nativeQuery = true)
    List<Maintenance> findByStaffId(@Param("staffId") String staffId);

    @Query(value = "SELECT * FROM maintenance WHERE mtn_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Maintenance> findByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
