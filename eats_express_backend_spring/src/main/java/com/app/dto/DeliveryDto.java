package com.app.dto;

import java.sql.Date;

import com.app.entities.OrderDetails;
import com.app.entities.Otp;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryDto{
	
	@JsonProperty(access = Access.READ_ONLY) 
	private Long id;
	private OrderDetails order;
	private UserDto user;
	private String deliveryPartner;
	private Date deliveryDate;
	private Otp otp;
}
