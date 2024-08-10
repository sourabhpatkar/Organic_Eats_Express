package com.app.controller;

import java.util.List;

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

import com.app.dto.UserDto;
import com.app.dto.ApiResponse;
import com.app.dto.LoginDto;
import com.app.exception.customException;
import com.app.service.UserService;
import com.app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public UserController() {
		System.out.println("in controller of "+getClass());
	}
	
	@GetMapping
	public ResponseEntity<?> getAllUser(){
		try {
		//return ResponseEntity.status(HttpStatus.OK).body(adminservice.getAllAdmins());
			List<UserDto> listUser=userService.getAllUsers();
			return ResponseEntity.ok(listUser);
		}catch(RuntimeException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PostMapping
	public ResponseEntity<?> addNewUser(@RequestBody UserDto userDto){
		try {
			System.out.println("in user Post method-------");
			return ResponseEntity.ok(userService.addNewUser(userDto));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody UserDto userDto){
		try {
			return ResponseEntity.ok(userService.updateUser(id,userDto));
		}catch(customException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Long userId){
		try {
			ApiResponse res= userService.deleteUser(userId);
			return ResponseEntity.ok(res);
		}catch(customException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> UserLogin(@RequestBody LoginDto loginDto){
		try {
			return ResponseEntity.ok(userService.userLogin(loginDto));
		}catch(customException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}
}
