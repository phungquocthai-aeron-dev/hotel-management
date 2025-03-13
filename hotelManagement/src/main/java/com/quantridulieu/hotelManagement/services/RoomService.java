package com.quantridulieu.hotelManagement.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantridulieu.hotelManagement.entities.RoomExport;
import com.quantridulieu.hotelManagement.repositories.HotelServiceRepository;
import com.quantridulieu.hotelManagement.repositories.RoomRepository;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
     

    

       ExcelEx
    
    
    // Xuất file excel
    public

    byte[] exportRoomToExcel() throws IOException {
        return excelExportUtil.exportToExcel(roomRepository.findAll(), null, "Danh sách bảo trì");

    

    public byte[] exportRoomsToExcelByListIds(List<String> ids) throws IOException {
        List<RoomExport> list = new ArrayList<>();
        
     

            RoomExport export = new RoomExport(
                room.getRoomId(),
                room.getRoomNumber(),
                room.getStatus(),
                room.getCategoryId(),
                room.getCategoryName()
            );
            list.add(export);
        }

     

    ublic void save(Room room) {

        if (room.getRoomId() == null)
            room.setRoomId(generateId());
        roomRepository.save(room);
    }

    public void delete(String id) {
        roomRepository.deleteById(id);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
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
