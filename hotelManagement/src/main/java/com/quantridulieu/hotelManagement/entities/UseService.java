package com.quantridulieu.hotelManagement.entities;

import java.util.Date;
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
    
    @ManyToOne
    @JoinColumn(name = "service_id", insertable = false, updatable = false)
    private Service service;
    
    @ManyToOne
    @JoinColumn(name = "rent_id", insertable = false, updatable = false)
    private RoomRental roomRental;
    
    @ManyToOne
    @JoinColumn(name = "promotion_id", insertable = false, updatable = false)
    private Promotion promotion;
    
    public UseService() {}

	public UseService(String usId, Integer quantity, Date usDate, Service service, RoomRental roomRental,
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

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
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