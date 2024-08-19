package com.app.dtos;

import com.app.entites.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
	private String street;
	
	private String buildingName;
	
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String pincode;
}
