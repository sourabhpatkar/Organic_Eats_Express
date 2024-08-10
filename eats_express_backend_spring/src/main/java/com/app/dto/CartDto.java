package com.app.dto;

import java.util.ArrayList;
import java.util.List;

import com.app.entities.CartItem;
import com.app.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartDto {
	@JsonProperty(access = Access.READ_ONLY) 
	private Long cartId;
	private User user;
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	private Double totalPrice = 0.0;
}
