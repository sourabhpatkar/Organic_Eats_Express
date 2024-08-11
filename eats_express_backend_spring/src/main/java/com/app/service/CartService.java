package com.app.service;

import com.app.dto.ApiResponse;
import com.app.dto.CartDto;
import com.app.exception.customException;

public interface CartService {
	CartDto getCartById(Long userId) throws customException;

	ApiResponse updateCart(CartDto cartDto);
}
