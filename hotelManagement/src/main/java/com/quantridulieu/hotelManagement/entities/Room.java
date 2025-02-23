package com.quantridulieu.hotelManagement.entities;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Room")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @Column(name = "room_id")
    private String roomId;
    
    @Column(name = "room_number")
    private Integer roomNumber;
    
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;
}