package com.quantridulieu.hotelManagement.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantridulieu.hotelManagement.entities.RoomRental;
import com.quantridulieu.hotelManagement.repositories.RoomRentalRepository;

@Service
public class RoomRentalService {
    private final RoomRentalRepository roomRentalRepository;

    @Autowired
    public RoomRentalService(RoomRentalRepository roomRentalRepository) {
        this.roomRentalRepository = roomRentalRepository;
    }

    @Transactional
    public void save(RoomRental roomRental) {
        if (roomRental.getRentId() == null) roomRental.setRentId(generateId());
        roomRentalRepository.save(roomRental);
    }

    @Transactional
    public void delete(String rentalId) {
        roomRentalRepository.deleteById(rentalId);
    }

    public List<RoomRental> getAllRentals() {
        return roomRentalRepository.findAll();
    }

    public RoomRental getRentalById(String rentalId) {
        return roomRentalRepository.findByRentalId(rentalId);
    }

    public List<RoomRental> getRentalsByCustomerId(String customerId) {
        return roomRentalRepository.findByCustomerId(customerId);
    }

    public RoomRental getRentalByRoomId(String roomId) {
        return roomRentalRepository.findByRoomId(roomId);
    }

    public List<RoomRental> getRentalsByStatus(String rentalStatus) {
        return roomRentalRepository.findByRentalStatus(rentalStatus);
    }

    public List<RoomRental> getRentalsByCheckInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return roomRentalRepository.findByCheckInDateRange(startDate, endDate);
    }

    public List<RoomRental> getRentalsByCheckOutDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return roomRentalRepository.findByCheckOutDateRange(startDate, endDate);
    }

    private String generateId() {
        Long count = roomRentalRepository.count();
        return String.format("RR%05d", count + 1);
    }
}
