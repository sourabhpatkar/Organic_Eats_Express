package com.app.dto;

import java.sql.Date;

import com.app.entities.OrderDetails;
import com.app.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PaymentDto {
	@JsonProperty(access = Access.READ_ONLY) 
	private Long id;

	private OrderDetailsDto orderDetailsDto;
	private UserDto user;

	private OrderDetails order;
	private User userId;

	private double amount;
	private Date paymentDate;
}
