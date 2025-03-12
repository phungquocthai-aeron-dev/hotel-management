package com.quantridulieu.hotelManagement.services;

import java.util.List;
import java.io.IOException;
import java.util.Date;

import com.quantridulieu.hotelManagement.repositories.FeedbackRepository;
import com.quantridulieu.hotelManagement.entities.Feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
	@Autowired
    FeedbackRepository feedbackRepository;
	
	@Autowired
	ExcelExportUtil excelExportUtil;
	
	// Xuất file excel
	public byte[] exportCustomerToExcel() throws IOException {
        return excelExportUtil.exportToExcel(feedbackRepository.findAll(), null, "Danh sách phản hồi");
    }

    public void save(Feedback feedback) {
        if (feedback.getFeedbackId() == null) feedback.setFeedbackId(generateId());
        feedbackRepository.save(feedback);
    }

    public void delete(String id) {
        feedbackRepository.deleteById(id);
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Feedback getFeedbackById(String id) {
        return feedbackRepository.findById(id).orElseThrow();
    }

    public List<Feedback> getFeedbackByDate(Date feedbackDate) {
        return feedbackRepository.findByFeedbackDate(feedbackDate);
    }

    public List<Feedback> getFeedbackByRating(int rating) {
        return feedbackRepository.findByRating(rating);
    }

    public List<Feedback> getFeedbackByCustomerId(String customerId) {
        return feedbackRepository.findByCustomerId(customerId);
    }

    public List<Feedback> getFeedbackByServiceId(String serviceId) {
        return feedbackRepository.findByServiceId(serviceId);
    }

    private String generateId() {
        Long count = feedbackRepository.count();
        return String.format("FB%05d", count + 1);
    }
}