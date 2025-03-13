package com.quantridulieu.hotelManagement.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quantridulieu.hotelManagement.entities.Maintenance;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, String> {

    @Query(value = "SELECT * FROM maintenance WHERE mtn_status = :status", nativeQuery = true)
    List<Maintenance> findByStatus(@Param("status") String status);

    @Query(value = "SELECT * FROM maintenance WHERE room_id = :roomId", nativeQuery = true)
    Maintenance findByRoomId(@Param("roomId") String roomId);

    @Query(value = "SELECT * FROM maintenance WHERE staff_id = :staffId", nativeQuery = true)
    List<Maintenance> findByStaffId(@Param("staffId") String staffId);

    @Query(value = "SELECT * FROM maintenance WHERE mtn_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Maintenance> findByDateRange(@Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT GetMonthlyExpenses(:year, :month)", nativeQuery = true)
    double getMonthlyExpenses(@Param("year") Integer year, @Param("month") Integer month);

    @Modifying
    @Procedure(name = "SearchMaintenance")
    List<Maintenance> SearchMaintenance(
            @Param("p_main_id") String mainId,
            @Param("p_room_id") String room,
            @Param("p_staff_id") String staff);

    @Query("SELECT COUNT(s) FROM Maintenance s")
    Long getTotalMaintenance();

    @Query("SELECT MAX(m.mtnId) FROM Maintenance m")
    String findLastMaintenanceId();

}
