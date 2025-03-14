package com.quantridulieu.hotelManagement.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.quantridulieu.hotelManagement.entities.Room;
import com.quantridulieu.hotelManagement.entities.RoomRental;
import com.quantridulieu.hotelManagement.entities.RoomRentalExport;
import com.quantridulieu.hotelManagement.repositories.RoomRentalRepository;

@Service
public class RoomRentalService {
    private final RoomRentalRepository roomRentalRepository;

    @Autowired
    public RoomRentalService(RoomRentalRepository roomRentalRepository) {
        this.roomRentalRepository = roomRentalRepository;
    }

   
    public void save(RoomRental roomRental) {
        if (roomRental.getRentId() == null) roomRental.setRentId(generateId());
        roomRentalRepository.save(roomRental);
    }
    
    @Autowired
   	ExcelExportUtil excelExportUtil;
   	
   	// Xuất file excel
   	public byte[] exportRoomRentalToExcel() throws IOException {
   		return excelExportUtil.exportToExcel(roomRentalRepository.findAll(), null, "Danh sách mượn phòng");
       }
   	public byte[] exportRoomRentalsToExcelByListIds(List<String> roomRentalIds) throws IOException {
   	    List<RoomRentalExport> exportList = new ArrayList<>();
   	    List<RoomRental> roomRentals = roomRentalRepository.findByRentIdIn(roomRentalIds);

   	    for (RoomRental rental : roomRentals) {
   	        Room room = rental.getRoom(); // 🛑 Kiểm tra null trước khi gọi phương thức!
   	        Integer roomNumber = (room != null) ? room.getRoomNumber() : null;

   	        RoomRentalExport export = new RoomRentalExport(
   	            rental.getRentId(),
   	            rental.getRentalDate(),
   	            rental.getCheckInDate(),
   	            rental.getCheckOutDate(),
   	            rental.getRentalStatus(),
   	            rental.getRoomId(),
   	            roomNumber, // 🛑 Tránh gọi null.getRoomNumber()
   	            rental.getCustomerId(),
   	            rental.getCustomerName()
   	        );
   	        exportList.add(export);
   	    }

   	    return excelExportUtil.exportToExcel(exportList, null, "Danh sách thuê phòng");
   	}

    public void delete(String rentalId) {
        roomRentalRepository.deleteById(rentalId);
    }

    public List<RoomRental> getAllRoomRentals() {
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
    
    public List<RoomRental> findRoomsByRentalDate(Date date) {
        return roomRentalRepository.findRoomsByRentalDate(date);
    }
    
    public List<RoomRental> findRoomsByCheckOutDate(Date date) {
        return roomRentalRepository.findRoomsByCheckOutDate(date);
    }
    
    public void updateCheckOut(String id, Date date) {
    	roomRentalRepository.updateCheckOut(id, date);
    }
    
    public void updateCheckIn(String id, Date date) {
    	roomRentalRepository.updateCheckIn(id, date);
    }
    
    @Transactional
    public List<RoomRental> searchBooking(String rentId, String roomId, String customerName) {
        return roomRentalRepository.searchBooking(
            (rentId == null || rentId.isEmpty()) ? null : rentId,
            (roomId == null || roomId.isEmpty()) ? null : roomId,
            (customerName == null || customerName.isEmpty()) ? null : customerName
        );
    }
}
