package com.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.ApiResponse;
import com.app.dto.LoginDto;
import com.app.dto.UserDtoWithAddress;
import com.app.dto.UserDtoWithId;
import com.app.entities.Address;
import com.app.entities.Cart;
import com.app.entities.User;
import com.app.exception.customException;
import com.app.repository.AddressRepository;
import com.app.repository.CartRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<UserDtoWithId> getAllUsers() {
		return userRepository.findAll().stream().map(user -> 
				DtoConverter(user))
				.collect(Collectors.toList());

	}

	@Override
	public ApiResponse addNewUser(UserDtoWithAddress newUser) {
		User user= new User();
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setEmail(newUser.getEmail());
		user.setPassword(newUser.getPassword());
		user.setMobileNumber(newUser.getMobileNumber());
		user.setAddress(newUser.getAddress());
		user.setCart(new Cart());
		userRepository.save(user);
		return new ApiResponse("new user added");
	}

	@Override
	public ApiResponse updateUser(Long id, UserDtoWithId newDto) throws customException {

		if (userRepository.existsById(id)) {
			User userDto = UserConverter(newDto);
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
	public UserDtoWithId userLogin(LoginDto loginDto) throws customException{
		User user= userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword())
				.orElseThrow(()->new customException("Invalid Email or Password"));
		return DtoConverter(user);
	}
	
	private User UserConverter(UserDtoWithId newUser){
		User user= new User();
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setEmail(newUser.getEmail());
		user.setPassword(newUser.getPassword());
		user.setMobileNumber(newUser.getMobileNumber());
		
		user.setAddress(addressRepository.findById(newUser.getAddressId()).get());
		Cart cart = cartRepository.findById(newUser.getCartId()).get();
		if(cart==null){
			cart = new Cart();
		}
		user.setCart(cart);
		return user;
	}
	
	private UserDtoWithId DtoConverter(User user) {
		UserDtoWithId userDto= new UserDtoWithId();
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setMobileNumber(user.getMobileNumber());
		
		userDto.setAddressId(user.getAddress().getAddressId());
		userDto.setCartId(user.getCart().getCartId());
		return userDto;
	}
}

















