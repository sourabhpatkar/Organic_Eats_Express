package com.app.service;

import com.app.dto.OtpDto;
import com.app.entities.Delivery;
import com.app.exception.customException;

public interface OtpService {
	OtpDto getOtpByDelivery(Long deliveryId)throws customException;
	void createOtp(Delivery delivery);
}
