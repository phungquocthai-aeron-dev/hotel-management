package com.quantridulieu.hotelManagement.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Staff")

public class Staff {
    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Id
    @Column(name = "staff_id")
    private String staffId;
    
    @Column(name = "staff_name")
    private String staffName;
    
    @Column(name = "staff_phone")
    private String staffPhone;
    
    @Column(name = "dob")
    private Date dateOfBirth;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "role")
    private String role;
    
    public Staff() {}

	public Staff(String staffId, String staffName, String staffPhone, Date dateOfBirth, String password) {
		super();
		this.staffId = staffId;
		this.staffName = staffName;
		this.staffPhone = staffPhone;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
   
}