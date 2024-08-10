package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.PaymentDto;
import com.app.exception.customException;

public interface PaymentService {
	List<PaymentDto> getAllPayments();
	ApiResponse addNewPayment(PaymentDto paymentDto) ;
	ApiResponse updatePayment(Long id,PaymentDto paymentDto) throws customException;
	ApiResponse deletePayment(Long id) throws customException;
}
