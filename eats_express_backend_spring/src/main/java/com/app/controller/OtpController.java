package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.customException;
import com.app.service.OtpService;

@RestController
@RequestMapping("/otp")
public class OtpController {
	@Autowired
	private OtpService otpservice;
	
	@GetMapping("/getOtp/{deliveryId}")
	public ResponseEntity<?> getOtpByDelivery(@PathVariable Long deliveryId){
		try {
			return ResponseEntity.ok(otpservice.getOtpByDelivery(deliveryId));
		}catch(customException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
