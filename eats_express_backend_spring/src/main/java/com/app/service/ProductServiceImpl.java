package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDtoWithId;
import com.app.entities.Category;
import com.app.entities.Product;
import com.app.entities.User;
import com.app.exception.customException;
import com.app.repository.CategoryRepository;
import com.app.repository.ProductRepository;
import com.app.repository.UserRepository;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository prodRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<ProductDtoWithId> getAllProducts() {
		return prodRepository.findAll()
				.stream()
				.map(prod-> 
				DtoConverter(prod))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse addNewProduct(ProductDtoWithId productDtoWithId) {
		Product product=ProductConverter( productDtoWithId);			
		prodRepository.save(product);
		return new ApiResponse("Product Added Successfully");
	}

	@Override
	public ApiResponse updateProduct(Long id, ProductDtoWithId productDto) throws customException {
		
		if(prodRepository.existsById(id)) {		
			Product prod= prodRepository.findById(id).get();
			prod.setAddedBy(userRepository.findById(productDto.getUserId()).get());
			prod.setChosenCategory(categoryRepository.findById(productDto.getCategoryId()).get());
			prod.setName(productDto.getName());
			prod.setPrice(productDto.getPrice());
			prod.setQuantity(prod.getQuantity());
			return new ApiResponse("Product details updated successfully");
		}
		else
			throw new customException("could not update product details for product with id "+id);
	}

	@Override
	public ApiResponse deleteProduct(Long productId) throws customException {
		if(prodRepository.existsById(productId)) {
			prodRepository.deleteById(productId);
			return new ApiResponse("Product with Id : "+productId+" deleted Successfully ");
		}
		else
			throw new customException("Could not found Product with Id : "+productId);
	}
	
	private Product ProductConverter(ProductDtoWithId productDtoWithId) {
		Product product=new Product();
		product.setName(productDtoWithId.getName());
		product.setPrice(productDtoWithId.getPrice());
		product.setQuantity(productDtoWithId.getQuantity());
		
		Category category= categoryRepository.findById(productDtoWithId.getCategoryId()).get();
		product.setChosenCategory(category);
		User user = userRepository.findById(productDtoWithId.getUserId()).get();
		product.setAddedBy(user);
		return product;
	}
	
	private ProductDtoWithId DtoConverter(Product product) {
		ProductDtoWithId productDto=new ProductDtoWithId();
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice());
		productDto.setQuantity(product.getQuantity());
		productDto.setCategoryId(product.getChosenCategory().getId());
		productDto.setUserId(product.getAddedBy().getUserId());
		return productDto;
	}
	
}
