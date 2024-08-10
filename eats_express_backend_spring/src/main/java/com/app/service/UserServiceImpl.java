package com.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.UserDto;
import com.app.dto.ApiResponse;
import com.app.dto.LoginDto;
import com.app.entities.Address;
import com.app.entities.User;
import com.app.exception.customException;
import com.app.repository.AddressRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<UserDto> getAllUsers() {
		return userRepository.findAll().stream().map(user -> 
				mapper.map(user, UserDto.class))
				.collect(Collectors.toList());

	}

	@Override
	public ApiResponse addNewUser(UserDto newUser) {
		User user = mapper.map(newUser, User.class);
		userRepository.save(user);
		return new ApiResponse("new user added");
	}

	@Override
	public ApiResponse updateUser(Long id, UserDto userDto) throws customException {

		if (userRepository.existsById(id)) {
			Optional<User> optionalUser = userRepository.findById(id);
			User userUpdate = optionalUser.get();
			userUpdate.setFirstName(userDto.getFirstName());
			userUpdate.setLastName(userDto.getLastName());
			userUpdate.setEmail(userDto.getEmail());
			userUpdate.setMobileNumber(userDto.getMobileNumber());
			userUpdate.setPassword(userDto.getPassword());
			userUpdate.setAddress(mapper.map(userDto.getAddress(), Address.class));
			
			userRepository.save(userUpdate);
			return new ApiResponse("user updated with Id" + id);
		} else
			throw new customException("error occured : could not find user with id " + id);

	}

	@Override
	public ApiResponse deleteUser(Long userId) throws customException{
		if(userRepository.existsById(userId)) {
			userRepository.deleteById(userId);
			return new ApiResponse("User deleted successfully");
		}
		else 
			throw new customException("error occured : could not find user with id " + userId);
	}

	@Override
	public UserDto userLogin(LoginDto loginDto) throws customException{
		User user= userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword())
				.orElseThrow(()->new customException("Invalid Email or Password"));
		return mapper.map(user, UserDto.class);
	}
	
	
}
