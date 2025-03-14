package com.quantridulieu.hotelManagement.entities;

public class RoomExport {
    private String roomId;
    private Integer roomNumber;
    private String status;
    private String categoryId;
    private String categoryName;

    public RoomExport(String roomId, Integer roomNumber, String status, String categoryId, String categoryName) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.status = status;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    // Getter & Setter
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public Integer getRoomNumber() { return roomNumber; }
    public void setRoomNumber(Integer roomNumber) { this.roomNumber = roomNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
