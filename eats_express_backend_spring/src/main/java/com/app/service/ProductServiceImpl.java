package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDto;
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
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<ProductDto> getAllProducts() {
		return prodRepository.findAll()
				.stream()
				.map(prod-> 
				mapper.map(prod, ProductDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse addNewProduct(ProductDtoWithId productDtoWithId) {
		ProductDto productDto=new ProductDto();
		productDto.setName(productDtoWithId.getName());
		productDto.setPrice(productDtoWithId.getPrice());
		productDto.setQuantity(productDtoWithId.getQuantity());
		
		Category category= categoryRepository.findById(productDtoWithId.getCategoryId()).get();
		productDto.setChosenCategory(category);
		User user = userRepository.findById(productDtoWithId.getUserId()).get();
		productDto.setAddedBy(user);
		
		Product product=mapper.map(productDto, Product.class);
		
		prodRepository.save(product);
		return new ApiResponse("Product Added Successfully");
	}

	@Override
	public ApiResponse updateProduct(Long id, ProductDtoWithId productDtoWithId) throws customException {
		
		if(prodRepository.existsById(id)) {
			ProductDto productDto=new ProductDto();
			productDto.setName(productDtoWithId.getName());
			productDto.setPrice(productDtoWithId.getPrice());
			productDto.setQuantity(productDtoWithId.getQuantity());
			
			Category category= categoryRepository.findById(productDtoWithId.getCategoryId()).get();
			productDto.setChosenCategory(category);
			User user = userRepository.findById(productDtoWithId.getUserId()).get();
			productDto.setAddedBy(user);
			
			Product prod= prodRepository.findById(id).get();
			prod.setAddedBy(productDto.getAddedBy());
			prod.setChosenCategory(productDto.getChosenCategory());
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

}
