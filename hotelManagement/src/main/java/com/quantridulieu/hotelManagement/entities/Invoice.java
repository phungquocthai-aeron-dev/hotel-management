package com.quantridulieu.hotelManagement.entities;

import java.util.Date;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "invoice")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Invoice {
    @Id
    @Column(name = "invoice_id")
    private String invoiceId;
    
    @Column(name = "invoice_date")
    private Date invoiceDate;
    
    @Column(name = "total_amount")
    private Float totalAmount;
    
    @ManyToOne
    @JoinColumn(name = "us_id")
    private UseService useService;
    
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;
}
