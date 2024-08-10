package com.app.service;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.OtpDto;
import com.app.entities.Delivery;
import com.app.entities.Otp;
import com.app.exception.customException;
import com.app.repository.DeliveryRepository;
import com.app.repository.OtpRepository;
@Service
@Transactional
public class OtpServiceImpl implements OtpService {
	@Autowired
	private OtpRepository otpRepository;
	@Autowired
	private DeliveryRepository deliveryRepository;
	@Autowired
	private ModelMapper mapper;
	
	Random random=new Random();
	
	@Override
	public void createOtp( Delivery delivery) {
		int otpNo= 100000 + random.nextInt(900000);
		Otp otp=new Otp( otpNo, delivery);
		otpRepository.save(otp);
	}
	
	@Override
	public OtpDto getOtpByDelivery(Long deliveryId) throws customException {
		
		Delivery delivery = deliveryRepository.findById(deliveryId).get();
		createOtp(delivery);
		Otp opt=otpRepository.findByDelivery(delivery).get();
		return mapper.map(opt, OtpDto.class);
	}

}
