package com.app.dto;

import com.app.entities.Cart;
import com.app.entities.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartItemDto {
	@JsonProperty(access = Access.READ_ONLY) 
	private Long cartItemId;
	private Cart cart;
	private Product product;
	private Integer quantity;
	private Double productPrice;
}
