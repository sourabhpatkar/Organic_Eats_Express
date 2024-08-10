package com.app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	
	private Integer flatNo;
	
	private String buildingName;
	
	private String street;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String pinCode;

//	public Address(Integer flatNo, String buildingName, String street, String city, String state, String country,
//			String pinCode) {
//		
//		this.flatNo = flatNo;
//		this.buildingName = buildingName;
//		this.street = street;
//		this.city = city;
//		this.state = state;
//		this.country = country;
//		this.pinCode = pinCode;
//	}
	
	
}
