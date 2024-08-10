package com.app.dto;

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
public class ProductDtoWithId {
	@JsonProperty(access = Access.READ_ONLY) 
	private Long id;
	private String name;
	private Double price;
	private Integer quantity;
	private Long categoryId;
	private Long UserId;
	
}
