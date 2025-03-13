package com.quantridulieu.hotelManagement.entities;

import java.util.Date;

public class InvoiceExport {

    private String invoiceId;
    
    private Date invoiceDate;
    
    private Float totalAmount;

    private String userviceId; 
    
    private String staffId;
    
	public InvoiceExport(String invoiceId, Date invoiceDate, Float totalAmount, String userviceId, String staffId) {
		super();
		this.invoiceId = invoiceId;
		this.invoiceDate = invoiceDate;
		this.totalAmount = totalAmount;
		this.userviceId = userviceId;
		this.staffId = staffId;
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

	public String getUserviceId() {
		return userviceId;
	}

	public void setUserviceId(String userviceId) {
		this.userviceId = userviceId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
    
    
}
