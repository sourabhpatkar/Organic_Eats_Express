package com.app.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.app.entities.RoleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
	
	private AddressDTO address;
	
}
