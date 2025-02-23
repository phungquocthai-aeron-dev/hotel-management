package com.quantridulieu.hotelManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quantridulieu.hotelManagement.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
}