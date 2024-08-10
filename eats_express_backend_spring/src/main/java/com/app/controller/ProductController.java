
package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDto;
import com.app.dto.ProductDtoWithId;
import com.app.exception.customException;
import com.app.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productservice;
	
	public ProductController() {
		System.out.println("in controller of "+getClass());
	}
	
	@GetMapping
	public ResponseEntity<?> getAllProducts(){
		try {
			return ResponseEntity.ok(productservice.getAllProducts());
		}catch(RuntimeException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
//	@PostMapping("/{adminId}")
//	//public ResponseEntity<?> addNewProduct(@RequestBody ProductDto product){
//	public ResponseEntity<?> addNewProduct(@PathVariable Long adminId,@RequestBody ProductDto product, @RequestParam Long CategoryId){
//		try {
//			return ResponseEntity.ok(productservice.addNewProduct(adminId,product,CategoryId));
//		}catch(RuntimeException e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//					.body(new ApiResponse(e.getMessage()));
//		}
//	}
	@PostMapping
	public ResponseEntity<?> addNewProduct(@RequestBody ProductDtoWithId product){
		try {
			return ResponseEntity.ok(productservice.addNewProduct(product));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateAdmin(@PathVariable Long id,@RequestBody ProductDtoWithId productDtoWithId){
		try {
			return ResponseEntity.ok(productservice.updateProduct(id,productDtoWithId));
		}catch(customException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<?> deleteEntity(@PathVariable Long productId){
		try {
			ApiResponse res= productservice.deleteProduct(productId);
			return ResponseEntity.ok(res);
		}catch(customException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}

}

