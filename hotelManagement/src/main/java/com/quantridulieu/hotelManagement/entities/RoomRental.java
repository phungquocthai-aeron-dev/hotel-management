package com.quantridulieu.hotelManagement.entities;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.*;

@Entity 
@Table(name = "room_rental", schema = "hotel_management")
    public class RoomRental {
    @Id
    @Column(name = "rent_id")
    private String rentId;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "rental_date")
    private Date rentalDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "check_in_date")
    private Date checkInDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "check_out_date")
    private Date checkOutDate;



    
    @ManyToOne(cascade = CascadeT e.PERSIST)
    @JoinColumn(name = "room_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Room room;
 
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "c
    ustomer_id")
    
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;
            
    public RoomRental() {
    }

    public RoomRental(String rentId, Date rentalDate, Date checkInDate, Date checkOutDate, String rentalStatus,
            Room room, Customer customer) {
        this.rentId = rentId;
        this.rentalDate = rentalDate;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.rentalStatus = rentalStatus;
        this.room = room;
        
    

        
        
    
    

        
    

        this.customer = customer;
        
    
        
    
        
    

    }
        
    

        
        
    

    
        
    

    // Getter và Setter
        
    

        
        
    
    
    public String getRentId
        ) {
    

        return rentId;
        
    
        
    
        
    

    
        
    
    }
        
    

    public void setRentId(String re
        tId) {
    

        this.rentId = rentId;
        
    
    }

        
    

    public Date getRentalDate() {
        
    
        return rentalDate;
    }
        
    

    
        
    
    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDat
        ;
    

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


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Getter và Setter bổ sung
    public String getRoomId() {
        return room != null ? room.getRoomId() : null;
    }

    public void setRoomId(String roomId) {
        if (room != null) {
            room.setRoomId(roomId);
        }
    }

    public String getCustomerId() {
        return customer != null ? customer.getCustomerId() : null;
    }

    public void setCustomerId(String customerId) {
        if (customer != null) {
            customer.setCustomerId(customerId);
        }
    }

    public String getCustomerName() {
        return customer != null ? customer.getCustomerName() : null;
    }

    public void setCustomerName(String customerName) {
        if (customer != null) {
            customer.setCustomerName(customerName);
        }
    }
}
