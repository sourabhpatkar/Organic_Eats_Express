package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.ApiResponse;
import com.app.dto.CategoryDto;
import com.app.entities.Category;
import com.app.exception.customException;
import com.app.repository.CategoryRepository;
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository catRepo;
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<CategoryDto> getAllCategories() {
		return catRepo.findAll()
				.stream()
				.map(category->
				mapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse addNewCategory(CategoryDto catDto) {
		catRepo.save(mapper.map(catDto, Category.class));
		return new ApiResponse("Category added ");
	}

	@Override
	public ApiResponse updateCategory(Long id, CategoryDto catDto) throws customException {
		if(catRepo.existsById(id)) {
			Category category = catRepo.findById(id).get();
			category.setName(catDto.getName());
			catRepo.save(category);
			return new ApiResponse("Category Updated");
		}else
			throw new customException("error occured : could not find category with id " + id);
	}

	@Override
	public ApiResponse deleteCategory(Long id) throws customException {
		if(catRepo.existsById(id)) {
			catRepo.deleteById(id);
			return new ApiResponse("Category with Id: "+id +" deleted");
		}else
			throw new customException("error occured : could not find category with id " + id);
	}

}
