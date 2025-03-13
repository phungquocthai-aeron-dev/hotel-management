package com.quantridulieu.hotelManagement.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantridulieu.hotelManagement.entities.HotelService;
import com.quantridulieu.hotelManagement.entities.Room;
import com.quantridulieu.hotelManagement.entities.RoomExport;
import com.quantridulieu.hotelManagement.repositories.HotelServiceRepository;
import com.quantridulieu.hotelManagement.repositories.RoomRepository;

@Service
public class RoomService {

	@Autowired
    private RoomRepository roomRepository;
    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Autowired
	ExcelExportUtil excelExportUtil;
	
	// Xuất file excel
	public byte[] exportRoomToExcel() throws IOException {
		return excelExportUtil.exportToExcel(roomRepository.findAll(), null, "Danh sách bảo trì");
    }
	public byte[] exportRoomsToExcelByListIds(List<String> ids) throws IOException {
        List<RoomExport> list = new ArrayList<>();
        List<Room> data = roomRepository.findByRoomIDs(ids);

        for (Room room : data) {
            RoomExport export = new RoomExport(
                room.getRoomId(),
                room.getRoomNumber(),
                room.getStatus(),
                room.getCategoryId(),
                room.getCategoryName()
            );
            list.add(export);
        }

        return excelExportUtil.exportToExcel(list, null, "Danh sách phòng khách sạn");
    }

    public void save(Room room) {
        if (room.getRoomId() == null) room.setRoomId(generateId());
        roomRepository.save(room);
    }

    public void delete(String id) {
        roomRepository.deleteById(id);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(String roomId) {
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
    @Transactional
    public List<Room> searchRoom(String roomId, Integer roomNumber, String status, String categoryId, String categoryName) {
        return roomRepository.searchRoom(
            (roomId == null || roomId.isEmpty()) ? null : roomId,
            (roomNumber == null) ? null : roomNumber, // Đã sửa
            (status == null || status.isEmpty()) ? null : status,
            (categoryId == null || categoryId.isEmpty()) ? null : categoryId,
            (categoryName == null || categoryName.isEmpty()) ? null : categoryName
        );
    }


}
