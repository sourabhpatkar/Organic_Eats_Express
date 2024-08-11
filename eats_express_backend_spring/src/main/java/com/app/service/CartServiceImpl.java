package com.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.ApiResponse;
import com.app.dto.CartDto;
import com.app.entities.Cart;
import com.app.entities.Product;
import com.app.entities.User;
import com.app.exception.customException;
import com.app.repository.CartRepository;
import com.app.repository.ProductRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public CartDto getCartById(Long userId)throws customException{
		if(userRepository.existsById(userId)) {
			User user = userRepository.findById(userId).get();
			return DtoConverter(user.getCart());
		}else {
			throw new customException("User not found");
		}
	}

	@Override
	public ApiResponse updateCart(CartDto cartDto) {
		Cart cart = CartConverter(cartDto);
		cartRepository.save(cart);
		return new ApiResponse("Added to cart");
	}
	
	private CartDto DtoConverter(Cart cart) {
		CartDto cartDto=new CartDto();
		cartDto.setCartId(cart.getCartId());
		for(Product product : cart.getProducts()) {
			cartDto.getProducts().add(product.getId());
		}
		cartDto.setTotalPrice(cart.getTotalPrice());
		return cartDto;
	}
	
	private Cart CartConverter(CartDto cartDto) {
		Cart cart = cartRepository.findById(cartDto.getCartId()).get();
		cart.setProducts(new ArrayList<Product>());
		for(Long productId : cartDto.getProducts()) {
			cart.getProducts().add(productRepository.findById(productId).get());
		}
		cart.setTotalPrice(cart.getTotalPrice());
		return cart;
	}

}
