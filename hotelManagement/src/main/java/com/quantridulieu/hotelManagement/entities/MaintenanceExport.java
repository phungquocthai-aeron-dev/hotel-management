package com.quantridulieu.hotelManagement.entities;

import java.util.Date;
import jakarta.persistence.*;

public class MaintenanceExport {
    private String mtnId;

    private Date mtnDate;
    
    private String mtnDescription;
    
    private Float mtnFee;
    
    private Date mtnEnd;
    
    private String mtnStatus;
    
    private String room;
    
    private String staff;
    
    public MaintenanceExport() {}

	public MaintenanceExport(String mtnId, Date mtnDate, String mtnDescription, Float mtnFee, Date mtnEnd, String mtnStatus,
			String room, String staff) {
		super();
		this.mtnId = mtnId;
		this.mtnDate = mtnDate;
		this.mtnDescription = mtnDescription;
		this.mtnFee = mtnFee;
		this.mtnEnd = mtnEnd;
		this.mtnStatus = mtnStatus;
		this.room = room;
		this.staff = staff;
	}

	public String getMtnId() {
		return mtnId;
	}

	public void setMtnId(String mtnId) {
		this.mtnId = mtnId;
	}

	public Date getMtnDate() {
		return mtnDate;
	}

	public void setMtnDate(Date mtnDate) {
		this.mtnDate = mtnDate;
	}

	public String getMtnDescription() {
		return mtnDescription;
	}

	public void setMtnDescription(String mtnDescription) {
		this.mtnDescription = mtnDescription;
	}

	public Float getMtnFee() {
		return mtnFee;
	}

	public void setMtnFee(Float mtnFee) {
		this.mtnFee = mtnFee;
	}

	public Date getMtnEnd() {
		return mtnEnd;
	}

	public void setMtnEnd(Date mtnEnd) {
		this.mtnEnd = mtnEnd;
	}

	public String getMtnStatus() {
		return mtnStatus;
	}

	public void setMtnStatus(String mtnStatus) {
		this.mtnStatus = mtnStatus;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getStaffId() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}
    
	public MaintenanceExport(Maintenance maintenance) {
		this.mtnId = maintenance.getMtnId();
		this.mtnDate = maintenance.getMtnDate();
		this.mtnDescription = maintenance.getMtnDescription();
		this.mtnFee = maintenance.getMtnFee();
		this.mtnEnd = maintenance.getMtnEnd();
		this.mtnStatus = maintenance.getMtnStatus();
		this.room = maintenance.getRoom().getRoomId();
		this.staff = maintenance.getStaff().getStaffId();
	}
}