package com.quantridulieu.hotelManagement.repositories;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quantridulieu.hotelManagement.entities.RoomRental;

@Repository
public interface RoomRentalRepository extends JpaRepository<RoomRental, String> {

	@Query(value = "SELECT * FROM room_rental WHERE rent_id = :rentalId", nativeQuery = true)
	RoomRental findByRentalId(@Param("rentalId") String rentalId);
	 
    @Query(value = "SELECT * FROM room_rental WHERE customer_id = :customerId", nativeQuery = true)
    List<RoomRental> findByCustomerId(@Param("customerId") String customerId);
    @Query(value = "SELECT * FROM room_rental WHERE rent_id IN :rentIds", nativeQuery = true)
    List<RoomRental> findByRentIdIn(@Param("rentIds") List<String> rentIds);

    @Query(value = "SELECT * FROM room_rental WHERE room_id = :roomId", nativeQuery = true)
    RoomRental findByRoomId(@Param("roomId") String roomId);

    @Query(value = "SELECT * FROM room_rental WHERE rental_status = :rentalStatus", nativeQuery = true)
    List<RoomRental> findByRentalStatus(@Param("rentalStatus") String rentalStatus);
    
    @Query(value = "SELECT * FROM room_rental WHERE check_in_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<RoomRental> findByCheckInDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT * FROM room_rental WHERE check_out_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<RoomRental> findByCheckOutDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Procedure(name = "SearchBooking")
    List<RoomRental> searchBooking(
        @Param("p_rent_id") String rentId,
        @Param("p_room_id") String roomId,
        @Param("p_customer_name") String customerName
    );
    
    @Query(value = "SELECT rr.* FROM " +
            "Room_Rental rr " +
            "WHERE DATE(rr.rental_date) = DATE(:rentalDate) AND rr.check_in_date IS NULL", nativeQuery = true)
	List<RoomRental> findRoomsByRentalDate(@Param("rentalDate") Date rentalDate);

	@Query(value = "SELECT rr.* FROM " +
            "Room_Rental rr NATURAL JOIN Room r " +
            "WHERE DATE(rr.check_out_date) = DATE(:checkOutDate) AND r.status LIKE '%Occupied%' ", nativeQuery = true)
	List<RoomRental> findRoomsByCheckOutDate(@Param("checkOutDate") Date checkOutDate);

	@Modifying
	@Transactional
	@Query(value = "UPDATE room_rental SET check_in_date = :checkInDate WHERE rent_id = :rent_id", nativeQuery = true)
	int updateCheckIn(@Param("rent_id") String rent_id, @Param("checkInDate") Date checkInDate);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE room_rental SET check_out_date = :checkOutDate WHERE rent_id = :rent_id", nativeQuery = true)
	int updateCheckOut(@Param("rent_id") String rent_id, @Param("checkOutDate") Date checkOutDate);
}
