package com.quantridulieu.hotelManagement.entities;

import java.util.Date;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Feedback")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    @Id
    @Column(name = "feedback_id")
    private String feedbackId;
    
    @Column(name = "content")
    private String content;
    
    @Column(name = "feedback_time")
    private Date feedbackTime;
    
    @ManyToOne
    @JoinColumn(name = "us_id", insertable = false, updatable = false)
    private UseService useService;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;
}