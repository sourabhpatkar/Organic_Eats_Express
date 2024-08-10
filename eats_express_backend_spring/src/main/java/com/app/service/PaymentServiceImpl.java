package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.ApiResponse;
import com.app.dto.PaymentDto;
import com.app.entities.Payment;
import com.app.exception.customException;
import com.app.repository.PaymentRepository;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<PaymentDto> getAllPayments() {
		return paymentRepository.findAll()
				.stream()
				.map(payment-> mapper.map(payment, PaymentDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse addNewPayment(PaymentDto paymentDto) {
		paymentRepository.save(mapper.map(paymentDto, Payment.class));
		return new ApiResponse("new Payment Added.");
	}

	@Override
	public ApiResponse updatePayment(Long id, PaymentDto paymentDto) throws customException {
		if(paymentRepository.existsById(id)) {
			Payment payment=paymentRepository.findById(id).get();
			payment.setAmount(paymentDto.getAmount());
		//	payment.setCustId(paymentDto.getCustId());
			payment.setId(paymentDto.getId());
		//	payment.setOrder(paymentDto.getOrder());

		//  payment.setCustId(paymentDto.getCustId());
			payment.setUserId(paymentDto.getUserId());
			payment.setOrder(paymentDto.getOrder());
			payment.setPaymentDate(paymentDto.getPaymentDate());
			return new ApiResponse("Payment with Id: "+id+" updated");
		}else 
			throw new customException("Could not find Payment with Id: "+id);
	}

	@Override
	public ApiResponse deletePayment(Long id) throws customException {
		if(paymentRepository.existsById(id)) {
			paymentRepository.deleteById(id);
			return new ApiResponse("Payment with id :"+id+" deleted.");
		}else 
			throw new customException("Could not find Payment with Id: "+id);
		}

}
