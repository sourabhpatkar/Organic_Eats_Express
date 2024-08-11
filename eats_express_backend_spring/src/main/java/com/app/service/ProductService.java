package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDtoWithId;
import com.app.exception.customException;

public interface ProductService {

	List<ProductDtoWithId> getAllProducts();
	ApiResponse addNewProduct(ProductDtoWithId product);
	ApiResponse updateProduct(Long id, ProductDtoWithId productDtoWithId) throws customException;
	ApiResponse deleteProduct(Long productId)throws customException;
	
}
