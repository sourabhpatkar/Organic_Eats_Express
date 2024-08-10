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
import com.app.dto.PaymentDto;
import com.app.exception.customException;
import com.app.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	private PaymentService paymentservice;
	
	public PaymentController() {
		System.out.println("in controller of "+getClass());
	}
	
	@GetMapping
	public ResponseEntity<?> getAllPayments(){
		try {
			return ResponseEntity.ok(paymentservice.getAllPayments());
		}catch(RuntimeException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PostMapping
	public ResponseEntity<?> addNewPayment(@RequestBody PaymentDto paymentDto){
		try {return ResponseEntity.ok(paymentservice.addNewPayment(paymentDto));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePayment(@PathVariable Long id,@RequestBody PaymentDto paymentDto){
		try {
			return ResponseEntity.ok(paymentservice.updatePayment(id,paymentDto));
		}catch(customException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePayment(@PathVariable Long id){
		try {
			ApiResponse res= paymentservice.deletePayment(id);
			return ResponseEntity.ok(res);
		}catch(customException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
}
