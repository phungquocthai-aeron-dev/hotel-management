package com.quantridulieu.hotelManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quantridulieu.hotelManagement.entities.RoomRental;

@Repository
public interface RoomRentalRepository extends JpaRepository<RoomRental, String> {
}
