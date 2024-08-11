package com.app.dto;

import java.util.ArrayList;
import java.util.List;

import com.app.entities.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
//	@JsonProperty(access = Access.READ_ONLY) 
	private Long cartId;
//	private Long userId;
	private List<Long> products=new ArrayList<>();
	private Double totalPrice;
}
