package com.quantridulieu.hotelManagement.entities;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;

@Entity
@Table(name = "Use_Service")
public class UseService {
    @Id
    @Column(name = "us_id")
    private String usId;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "us_date")
    private Date usDate;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "service_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private HotelService service;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rent_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RoomRental roomRental;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "promotion_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Promotion promotion;
    
    public UseService() {}

	public UseService(String usId, Integer quantity, Date usDate, HotelService service, RoomRental roomRental,
			Promotion promotion) {
		super();
		this.usId = usId;
		this.quantity = quantity;
		this.usDate = usDate;
		this.service = service;
		this.roomRental = roomRental;
		this.promotion = promotion;
	}

	public String getUsId() {
		return usId;
	}

	public void setUsId(String usId) {
		this.usId = usId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getUsDate() {
		return usDate;
	}

	public void setUsDate(Date usDate) {
		this.usDate = usDate;
	}

	public HotelService getService() {
		return service;
	}

	public void setService(HotelService service) {
		this.service = service;
	}

	public RoomRental getRoomRental() {
		return roomRental;
	}

	public void setRoomRental(RoomRental roomRental) {
		this.roomRental = roomRental;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
    
}