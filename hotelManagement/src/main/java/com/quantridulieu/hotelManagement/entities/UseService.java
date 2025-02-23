package com.quantridulieu.hotelManagement.entities;

import java.util.Date;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Use_Service")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}