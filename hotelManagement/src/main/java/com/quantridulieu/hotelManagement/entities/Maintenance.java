package com.quantridulieu.hotelManagement.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "Maintenance")
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

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "room_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Room room;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "staff_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Staff staff;

	public Maintenance() {
	}

	public Maintenance(String mtnId, Date mtnDate, String mtnDescription, Float mtnFee, Date mtnEnd, String mtnStatus,
			Room room, Staff staff) {
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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}