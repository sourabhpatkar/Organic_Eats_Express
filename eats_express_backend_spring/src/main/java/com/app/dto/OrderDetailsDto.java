package com.app.dto;

import java.util.ArrayList;
import java.util.List;

import com.app.entities.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class OrderDetailsDto{
	@JsonProperty(access = Access.READ_ONLY) 
	private Long id;

	private UserDto user;
	private List<Product> product=new ArrayList<Product>();
	private PaymentDto payment;
	private Double totalAmount;
	

}
