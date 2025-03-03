package com.quantridulieu.hotelManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quantridulieu.hotelManagement.entities.UseService;

@Repository
public interface UseServiceRepository extends JpaRepository<UseService, String> {
}
