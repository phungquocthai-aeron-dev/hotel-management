package com.quantridulieu.hotelManagement.entities;

import java.util.Date;

public class StaffDTO {
    private String staffId;
    
    private String staffName;
    
    private String staffPhone;
    
    private Date dateOfBirth;
    
    private String role;
    
    
    public StaffDTO() {}
    
    public StaffDTO(Staff staff) {
    	this.staffId = staff.getStaffId();
		this.staffName = staff.getStaffName();
		this.staffPhone = staff.getStaffPhone();
		this.dateOfBirth = staff.getDateOfBirth();
		this.role = staff.getRole();
    }

	public StaffDTO(String staffId, String staffName, String staffPhone, Date dateOfBirth, String password) {
		super();
		this.staffId = staffId;
		this.staffName = staffName;
		this.staffPhone = staffPhone;
		this.dateOfBirth = dateOfBirth;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStaffPhone() {
		return staffPhone;
	}

	public void setStaffPhone(String staffPhone) {
		this.staffPhone = staffPhone;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
   
}