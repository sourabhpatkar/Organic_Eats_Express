package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
	
	private Integer flatNo;
	
	private String buildingName;
	
	private String street;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String pinCode;
}