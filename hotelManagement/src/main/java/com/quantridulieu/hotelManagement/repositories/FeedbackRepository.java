package com.quantridulieu.hotelManagement.repositories;

import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quantridulieu.hotelManagement.entities.Feedback;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, String> {
    
    @Query(value = "SELECT * FROM feedback WHERE feedback_date = :feedbackDate", nativeQuery = true)
    List<Feedback> findByFeedbackDate(@Param("feedbackDate") Date feedbackDate);

    @Query(value = "SELECT * FROM feedback WHERE rating = :rating", nativeQuery = true)
    List<Feedback> findByRating(@Param("rating") int rating);

    @Query(value = "SELECT * FROM feedback WHERE customer_id = :customerId", nativeQuery = true)
    List<Feedback> findByCustomerId(@Param("customerId") String customerId);

    @Query(value = "SELECT * FROM feedback WHERE service_id = :serviceId", nativeQuery = true)
    List<Feedback> findByServiceId(@Param("serviceId") String serviceId);
}
