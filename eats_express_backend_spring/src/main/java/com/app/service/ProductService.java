package com.app.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDto;
import com.app.dto.ProductDtoWithId;
import com.app.exception.customException;

public interface ProductService {

	List<ProductDto> getAllProducts();
	ApiResponse addNewProduct(ProductDtoWithId product);
	ApiResponse updateProduct(Long id, ProductDtoWithId productDtoWithId) throws customException;
	ApiResponse deleteProduct(Long productId)throws customException;
	
}
