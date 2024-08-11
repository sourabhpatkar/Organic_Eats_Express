package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.LoginDto;
import com.app.dto.UserDtoWithAddress;
import com.app.dto.UserDtoWithId;
import com.app.exception.customException;

public interface UserService {
	List<UserDtoWithId> getAllUsers();
	ApiResponse addNewUser(UserDtoWithAddress newUser) ;
	ApiResponse updateUser(Long id,UserDtoWithId newDto) throws customException;
	ApiResponse deleteUser(Long userId) throws customException;
	UserDtoWithId userLogin(LoginDto loginDto)throws customException;
}
