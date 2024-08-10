package com.app.service;

import java.util.List;

import com.app.dto.UserDto;
import com.app.dto.ApiResponse;
import com.app.dto.LoginDto;
import com.app.exception.customException;

public interface UserService {
	List<UserDto> getAllUsers();
	ApiResponse addNewUser(UserDto newUser) ;
	ApiResponse updateUser(Long id,UserDto user) throws customException;
	ApiResponse deleteUser(Long userId) throws customException;
	UserDto userLogin(LoginDto loginDto)throws customException;
}
