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
import com.app.dto.OrderDetailsDto;
import com.app.exception.customException;
import com.app.service.OrderDetailsService;

@RestController
@RequestMapping("/orderdetails")
public class OrderDetailsController {
	@Autowired
	private OrderDetailsService orderservice;
	
	public OrderDetailsController() {
		System.out.println("in controller of "+getClass());
	}
	
	@GetMapping
	public ResponseEntity<?> getAllAdmin(){
		try {
			return ResponseEntity.ok(orderservice.getAllOrderDetails());
		}catch(RuntimeException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PostMapping
	public ResponseEntity<?> addNewAdmin(@RequestBody OrderDetailsDto orderdetailsDto){
		try {
			return ResponseEntity.ok(orderservice.addNewOrderDetails(orderdetailsDto));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateAdmin(@PathVariable Long id,@RequestBody OrderDetailsDto orderdetailsDto){
		try {
			return ResponseEntity.ok(orderservice.updateOrderDetails(id,orderdetailsDto));
		}catch(customException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEntity(@PathVariable Long id){
		try {
			ApiResponse res= orderservice.deleteOrderDetails(id);
			return ResponseEntity.ok(res);
		}catch(customException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
}
