package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.ApiResponse;
import com.app.dto.DeliveryDto;
import com.app.entities.Delivery;
import com.app.exception.customException;
import com.app.repository.DeliveryRepository;

@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {
	@Autowired
	private DeliveryRepository deliveryRepository;
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<DeliveryDto> getAllDeliveries() {
		return deliveryRepository.findAll()
				.stream()
				.map(delivery->mapper.map(delivery, DeliveryDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse addNewDelivery(DeliveryDto deliveryDto) {
		deliveryRepository.save(mapper.map(deliveryDto, Delivery.class));
		return new ApiResponse("New Delivery Added.");
	}

	@Override
	public ApiResponse updateDelivery(Long id, DeliveryDto deliveryDto) throws customException {
		if(deliveryRepository.existsById(id)) {
			Delivery delivery= deliveryRepository.findById(id).get();
		//	delivery.setCustomer(deliveryDto.get());
		
			//delivery.setCustomer(deliveryDto.getCustomer());
			delivery.setDeliveryDate(deliveryDto.getDeliveryDate());
			delivery.setDeliveryPartner(deliveryDto.getDeliveryPartner());
			delivery.setOrder(deliveryDto.getOrder());
			delivery.setOtp(deliveryDto.getOtp());
			return new ApiResponse("Delivery with Id: "+id+" is Updated");
		}else
			throw new customException("Could not find delivery with id: "+id);
	}

	@Override
	public ApiResponse deleteDelivery(Long id) throws customException {
		if(deliveryRepository.existsById(id)) {
			deliveryRepository.deleteById(id);
			return new ApiResponse("Delivery with Id: "+id+" deleted");
		}else
			throw new customException("Could not find delivery with id: "+id);
	}

}
