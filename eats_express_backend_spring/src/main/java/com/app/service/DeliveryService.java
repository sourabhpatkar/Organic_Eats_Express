package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.DeliveryDto;
import com.app.exception.customException;

public interface DeliveryService {
	List<DeliveryDto> getAllDeliveries();
	ApiResponse addNewDelivery(DeliveryDto deliveryDto) ;
	ApiResponse updateDelivery(Long id,DeliveryDto deliveryDto) throws customException;
	ApiResponse deleteDelivery(Long id) throws customException;
}
