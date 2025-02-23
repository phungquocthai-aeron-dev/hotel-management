package com.quantridulieu.hotelManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quantridulieu.hotelManagement.entities.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, String> {
}
