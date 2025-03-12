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

    @ManyToOne
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

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
    
}