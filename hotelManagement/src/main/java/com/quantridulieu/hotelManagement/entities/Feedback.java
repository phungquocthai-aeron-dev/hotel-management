package com.quantridulieu.hotelManagement.entities;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;

@Entity
@Table(name = "Feedback")
public class Feedback {
    @Id
    @Column(name = "feedback_id")
    private String feedbackId;
    
    @Column(name = "content")
    private String content;
    
    @Column(name = "feedback_time")
    private Date feedbackTime;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "us_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UseService useService;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;
    
    public Feedback() {}

	public Feedback(String feedbackId, String content, Date feedbackTime, UseService useService, Customer customer) {
		super();
		this.feedbackId = feedbackId;
		this.content = content;
		this.feedbackTime = feedbackTime;
		this.useService = useService;
		this.customer = customer;
	}

	public String getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public UseService getUseService() {
		return useService;
	}

	public void setUseService(UseService useService) {
		this.useService = useService;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
}