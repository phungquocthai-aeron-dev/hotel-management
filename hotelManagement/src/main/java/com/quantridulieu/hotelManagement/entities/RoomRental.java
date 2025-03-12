package com.quantridulieu.hotelManagement.entities;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "Room_Rental")
public class RoomRental {
    @Id
    @Column(name = "rent_id")
    private String rentId;
    
    @Column(name = "rental_date")
    private Date rentalDate;
    
    @Column(name = "check_in_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkInDate;
    
    @Column(name = "check_out_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkOutDate;
    
    @Column(name = "rental_status")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String rentalStatus;
    
    @ManyToOne
    @JoinColumn(name = "room_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Room room;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;
    
    public RoomRental() {}

	public RoomRental(String rentId, Date rentalDate, Date checkInDate, Date checkOutDate, String rentalStatus,
			Room room, Customer customer) {
		super();
		this.rentId = rentId;
		this.rentalDate = rentalDate;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.rentalStatus = rentalStatus;
		this.room = room;
		this.customer = customer;
	}

	public String getRentId() {
		return rentId;
	}

	public void setRentId(String rentId) {
		this.rentId = rentId;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getRentalStatus() {
		return rentalStatus;
	}

	public void setRentalStatus(String rentalStatus) {
		this.rentalStatus = rentalStatus;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
}