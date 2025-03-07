package com.quantridulieu.hotelManagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantridulieu.hotelManagement.entities.Room;
import com.quantridulieu.hotelManagement.repositories.RoomRepository;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Transactional
    public void save(Room room) {
        if (room.getRoomId() == null) room.setRoomId(generateId());
        roomRepository.save(room);
    }

    @Transactional
    public void delete(String id) {
        roomRepository.deleteById(id);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(int roomId) {
        return roomRepository.findByRoomID(roomId);
    }

    public Room getRoomByNumber(int roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber);
    }

    public List<Room> getRoomsByStatus(String status) {
        return roomRepository.findByStatus(status);
    }

    public List<Room> getRoomsByCategory(String categoryId) {
        return roomRepository.findByCategory(categoryId);
    }

    private String generateId() {
        Long count = roomRepository.count();
        return String.format("RM%05d", count + 1);
    }
}
