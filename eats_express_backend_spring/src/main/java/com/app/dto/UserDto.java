package com.app.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.app.entities.Address;
import com.app.entities.Cart;
import com.app.entities.RoleEnum;
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
public class UserDto{
	@JsonProperty(access = Access.READ_ONLY) 
	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Long mobileNumber;
	
	@Enumerated(EnumType.STRING)
	private RoleEnum role;
	
	private Address address;
	
	private Cart cart;
	
}
