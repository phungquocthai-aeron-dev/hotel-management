package com.quantridulieu.hotelManagement.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @Column(name = "invoice_id")
    private String invoiceId;
    
    @Column(name = "invoice_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;
    
    @Column(name = "total_amount")
    private Float totalAmount;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "us_id")
    private UseService useService;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "staff_id")
    private Staff staff;
    
    public Invoice() {}

	public Invoice(String invoiceId, Date invoiceDate, Float totalAmount, UseService useService, Staff staff) {
		super();
		this.invoiceId = invoiceId;
		this.invoiceDate = invoiceDate;
		this.totalAmount = totalAmount;
		this.useService = useService;
		this.staff = staff;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public UseService getUseService() {
		return useService;
	}

	public void setUseService(UseService useService) {
		this.useService = useService;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
    
}
