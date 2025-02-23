package com.quantridulieu.hotelManagement.entities;

import java.util.Date;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Promotion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
    @Id
    @Column(name = "promotion_id")
    private String promotionId;
    
    @Column(name = "promotion_name")
    private String promotionName;
    
    @Column(name = "promotion_value")
    private Float promotionValue;
    
    @Column(name = "promotion_description")
    private String promotionDescription;
    
    @Column(name = "promotion_start")
    private Date promotionStart;
    
    @Column(name = "promotion_end")
    private Date promotionEnd;
}
