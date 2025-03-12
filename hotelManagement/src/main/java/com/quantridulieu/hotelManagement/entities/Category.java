package com.quantridulieu.hotelManagement.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Category")
public class Category {
    @Id
    @Column(name = "category_id")
    private String categoryId;
    
    @Column(name = "category_name")
    private String categoryName;
    
    @Column(name = "room_price")
    private Float roomPrice;
    @OneToMany(mappedBy = "category") // Liên kết với Room
    private List<Room> rooms;
    public Category() {}
    
	public Category(String categoryId, String categoryName, Float roomPrice) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.roomPrice = roomPrice;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Float getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(Float roomPrice) {
		this.roomPrice = roomPrice;
	}
    
    
}