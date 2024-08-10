
package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerDto{
	
	@JsonProperty(access = Access.READ_ONLY) 
	private Long id;
	private String firstName;
	private String LastName;
	private String email;
	private String password;
	private Long moNumber;
	private Long Address;
}

