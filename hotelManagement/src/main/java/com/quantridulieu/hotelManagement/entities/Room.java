package com.quantridulieu.hotelManagement.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;

@Entity
@Table(name = "Room")
public class Room {
    @Id
    @Column(name = "room_id")
    private String roomId;
    
    @Column(name = "room_number")
    private Integer roomNumber;
    
    @Column(name = "status")
    private String status;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Category category;
    
    public Room() {}

	public Room(String roomId, Integer roomNumber, String status, Category category) {
		super();
		this.roomId = roomId;
		this.roomNumber = roomNumber;
		this.status = status;
		this.category = category;
	}

	 // Getter và Setter cho Room
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
    
    public Integer getRoomNumber() { return roomNumber; }
    public void setRoomNumber(Integer roomNumber) { this.roomNumber = roomNumber; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    // Getter cho categoryId, categoryName, roomPrice từ Category
    public String getCategoryId() {
        return category != null ? category.getCategoryId() : null;
    }

    public void setCategoryId(String categoryId) {
        if (category != null) {
            category.setCategoryId(categoryId);
        }
    }

    public String getCategoryName() {
        return category != null ? category.getCategoryName() : null;
    }

    public void setCategoryName(String categoryName) {
        if (category != null) {
            category.setCategoryName(categoryName);
        }
    }
    
}