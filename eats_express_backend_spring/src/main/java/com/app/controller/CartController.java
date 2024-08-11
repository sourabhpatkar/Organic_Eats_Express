package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.CartDto;
import com.app.exception.customException;
import com.app.service.CartService;

@RestController("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getCartByUserId(@PathVariable Long userId){
		try {
			return ResponseEntity.ok(cartService.getCartById(userId));
		}catch(customException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PostMapping()
	public ResponseEntity<?> updateCart(@RequestBody CartDto cartDto){
		try {
			return ResponseEntity.ok(cartService.updateCart(cartDto));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
}
