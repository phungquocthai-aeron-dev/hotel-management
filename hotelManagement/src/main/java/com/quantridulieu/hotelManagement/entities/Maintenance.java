package com.quantridulieu.hotelManagement.entities;

import java.util.Date;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Maintenance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {
    @Id
    @Column(name = "mtn_id")
    private String mtnId;
    
    @Column(name = "mtn_date")
    private Date mtnDate;
    
    @Column(name = "mtn_description")
    private String mtnDescription;
    
    @Column(name = "mtn_fee")
    private Float mtnFee;
    
    @Column(name = "mtn_end")
    private Date mtnEnd;
    
    @Column(name = "mtn_status")
    private String mtnStatus;
    
    @ManyToOne
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    private Room room;
    
    @ManyToOne
    @JoinColumn(name = "staff_id", insertable = false, updatable = false)
    private Staff staff;
}