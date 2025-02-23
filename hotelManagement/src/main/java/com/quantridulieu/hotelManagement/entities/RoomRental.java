package com.quantridulieu.hotelManagement.entities;

import java.util.Date;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Room_Rental")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomRental {
    @Id
    @Column(name = "rent_id")
    private String rentId;
    
    @Column(name = "rental_date")
    private Date rentalDate;
    
    @Column(name = "check_in_date")
    private Date checkInDate;
    
    @Column(name = "check_out_date")
    private Date checkOutDate;
    
    @Column(name = "rental_status")
    private String rentalStatus;
    
    @ManyToOne
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    private Room room;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;
}