package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpDto{
	@JsonProperty(access = Access.READ_ONLY) 
	private Long id;
	private int otpData;
	private DeliveryDto delivery;


}
