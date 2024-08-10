package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.OrderDetailsDto;
import com.app.exception.customException;

public interface OrderDetailsService {
	List<OrderDetailsDto> getAllOrderDetails();
	ApiResponse addNewOrderDetails(OrderDetailsDto orderdetailsdto) ;
	ApiResponse updateOrderDetails(Long id,OrderDetailsDto orderdetailsdto) throws customException;
	ApiResponse deleteOrderDetails(Long id) throws customException;
}
