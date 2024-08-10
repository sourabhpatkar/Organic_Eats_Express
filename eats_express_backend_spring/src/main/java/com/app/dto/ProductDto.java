package com.app.dto;

import com.app.entities.Category;
import com.app.entities.User;
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
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto{
	@JsonProperty(access = Access.READ_ONLY) 
	private Long id;
	private String name;
	private Double price;
	private Integer quantity;
	private Category chosenCategory;
	private User addedBy;
	
	public ProductDto(String name, Double price, Integer quantity, Category chosenCategory) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.chosenCategory = chosenCategory;
	}
	
	
}
