package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.ApiResponse;
import com.app.dto.OrderDetailsDto;
import com.app.entities.OrderDetails;
import com.app.exception.customException;
import com.app.repository.OrderDetailsRepository;
@Service
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService {
	@Autowired
	private OrderDetailsRepository detailsRepository;
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<OrderDetailsDto> getAllOrderDetails() {
		return detailsRepository.findAll()
				.stream()
				.map(orderDetails -> mapper.map(orderDetails, OrderDetailsDto.class))
				.collect(Collectors.toList());
		
	}

	@Override
	public ApiResponse addNewOrderDetails(OrderDetailsDto orderdetailsdto) {
		detailsRepository.save(mapper.map(orderdetailsdto, OrderDetails.class));
		return new ApiResponse("new Order Details added");
	}

	@Override
	public ApiResponse updateOrderDetails(Long id, OrderDetailsDto orderdetailsdto) throws customException {
		if(detailsRepository.existsById(id)) {
			OrderDetails orderDetails=detailsRepository.findById(id).get();
//			orderDetails.setAmount(orderdetailsdto.getAmount());
//			orderDetails.setCust(orderdetailsdto.getCust());
//			orderDetails.setPayment(orderdetailsdto.getPayment());
			orderDetails.setProduct(orderdetailsdto.getProduct());
			
			detailsRepository.save(orderDetails);
			return new ApiResponse("Order Details updated with id: "+ id);
		}else 
			throw new customException("could not find Order details with Id : "+id);
	}

	@Override
	public ApiResponse deleteOrderDetails(Long id) throws customException {
		if(detailsRepository.existsById(id)) {
			detailsRepository.deleteById(id);
			return new ApiResponse("Order with id : "+id+" deleted");
		}else 
			throw new customException("could not find Order details with Id : "+id);
	}

}
