package com.quantridulieu.hotelManagement.entities;

import java.util.Date;

public class RoomRentalExport {
    private String rentId;
    private Date rentalDate;
    private Date checkInDate;
    private Date checkOutDate;
    private String rentalStatus;
    private String roomId;
    private Integer roomNumber;
    private String customerId;
    private String customerName;

    public RoomRentalExport() {}

    public RoomRentalExport(String rentId, Date rentalDate, Date checkInDate, Date checkOutDate, String rentalStatus,
                            String roomId, Integer roomNumber, String customerId, String customerName) {
        this.rentId = rentId;
        this.rentalDate = rentalDate;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.rentalStatus = rentalStatus;
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.customerId = customerId;
        this.customerName = customerName;
    }

    // Getters và Setters
    public String getRentId() { return rentId; }
    public void setRentId(String rentId) { this.rentId = rentId; }

    public Date getRentalDate() { return rentalDate; }
    public void setRentalDate(Date rentalDate) { this.rentalDate = rentalDate; }

    public Date getCheckInDate() { return checkInDate; }
    public void setCheckInDate(Date checkInDate) { this.checkInDate = checkInDate; }

    public Date getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(Date checkOutDate) { this.checkOutDate = checkOutDate; }

    public String getRentalStatus() { return rentalStatus; }
    public void setRentalStatus(String rentalStatus) { this.rentalStatus = rentalStatus; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public Integer getRoomNumber() { return roomNumber; }
    public void setRoomNumber(Integer roomNumber) { this.roomNumber = roomNumber; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
}
