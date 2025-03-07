package com.quantridulieu.hotelManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantridulieu.hotelManagement.entities.Category;
import com.quantridulieu.hotelManagement.repositories.CategoryRepository;

import java.io.IOException;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

  
	@Autowired
	ExcelExportUtil excelExportUtil;
	
	// Xuất file excel
	public byte[] exportCategoryToExcel() throws IOException {
        return excelExportUtil.exportToExcel(categoryRepository.findAll(), null, "Danh sách loại");
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void save(Category category) {
        if (category.getCategoryId() == null) category.setCategoryId(generateId());
        categoryRepository.save(category);
    }

    public void delete(String id) {
        categoryRepository.deleteById(id);
    }

    public Category getCategoryById(String id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    public List<Category> findByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    public List<Category> findByRoomPrice(Float roomPrice) {
        return categoryRepository.findCategoryByRoomPrice(roomPrice);
    }

    public List<Category> findByRoomPriceGreaterThan(Float minPrice) {
        return categoryRepository.findCategoriesByRoomPriceGreaterThan(minPrice);
    }

    // C00001
    private String generateId() {
        Long count = categoryRepository.count();
        return String.format("C%05d", count + 1);
    }
}