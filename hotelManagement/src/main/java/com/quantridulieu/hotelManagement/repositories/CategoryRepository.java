package com.quantridulieu.hotelManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quantridulieu.hotelManagement.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query(value = "SELECT * FROM category WHERE category_name = :categoryName", nativeQuery = true)
    List<Category> findCategoryByName(@Param("categoryName") String categoryName);

    @Query(value = "SELECT * FROM category WHERE room_price = :roomPrice", nativeQuery = true)
    List<Category> findCategoryByRoomPrice(@Param("roomPrice") Float roomPrice);

    @Query(value = "SELECT * FROM category WHERE room_price > :minPrice", nativeQuery = true)
    List<Category> findCategoriesByRoomPriceGreaterThan(@Param("minPrice") Float minPrice);
}