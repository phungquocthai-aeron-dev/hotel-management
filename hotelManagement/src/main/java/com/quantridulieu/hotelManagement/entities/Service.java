package com.quantridulieu.hotelManagement.entities;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Service")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    @Id
    @Column(name = "service_id")
    private String serviceId;
    
    @Column(name = "service_name")
    private String serviceName;
    
    @Column(name = "service_price")
    private Float servicePrice;
}
