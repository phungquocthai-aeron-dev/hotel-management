package com.quantridulieu.hotelManagement.entities;

import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Staff")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
    // Content remains the same, just updated import
    @Id
    @Column(name = "staff_id")
    private String staffId;
    
    @Column(name = "staff_name")
    private String staffName;
    
    @Column(name = "staff_phone")
    private String staffPhone;
    
    @Column(name = "dob")
    private Date dateOfBirth;
}