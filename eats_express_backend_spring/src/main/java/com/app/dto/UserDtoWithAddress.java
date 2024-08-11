package com.app.dto;

import com.app.entities.Address;
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
public class UserDtoWithAddress {
	@JsonProperty(access = Access.READ_ONLY) 
	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Long mobileNumber;
	private RoleEnum role;
	private Address address;

}
