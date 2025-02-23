package com.quantridulieu.hotelManagement.entities;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @Column(name = "category_id")
    private String categoryId;
    
    @Column(name = "category_name")
    private String categoryName;
    
    @Column(name = "room_price")
    private Float roomPrice;
}