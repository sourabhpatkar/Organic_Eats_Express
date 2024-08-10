package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.DeliveryDto;
import com.app.dto.PaymentDto;
import com.app.exception.customException;
import com.app.service.DeliveryService;
import com.app.service.PaymentService;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
	@Autowired
	private DeliveryService deliveryservice;
	
	public DeliveryController() {
		System.out.println("in controller of "+getClass());
	}
	
	@GetMapping
	public ResponseEntity<?> getAllDelivaries(){
		try {
			return ResponseEntity.ok(deliveryservice.getAllDeliveries());
		}catch(RuntimeException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PostMapping
	public ResponseEntity<?> addNewDelivery(@RequestBody DeliveryDto deliveryDto){
		try {return ResponseEntity.ok(deliveryservice.addNewDelivery(deliveryDto));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateDelivery(@PathVariable Long id,@RequestBody DeliveryDto deliveryDto){
		try {
			return ResponseEntity.ok(deliveryservice.updateDelivery(id,deliveryDto));
		}catch(customException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDelivery(@PathVariable Long id){
		try {
			ApiResponse res= deliveryservice.deleteDelivery(id);
			return ResponseEntity.ok(res);
		}catch(customException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
}
