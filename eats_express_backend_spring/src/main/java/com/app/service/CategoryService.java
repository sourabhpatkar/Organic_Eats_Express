package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.CategoryDto;
import com.app.exception.customException;

public interface CategoryService {
	List<CategoryDto> getAllCategories();
	ApiResponse addNewCategory(CategoryDto catDto);
	ApiResponse updateCategory(Long id, CategoryDto catDto) throws customException;
	ApiResponse deleteCategory(Long id)throws customException;
}
