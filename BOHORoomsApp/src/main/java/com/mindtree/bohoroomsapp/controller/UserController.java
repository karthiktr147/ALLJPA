package com.mindtree.bohoroomsapp.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.bohoroomsapp.dto.ResponseBody;
import com.mindtree.bohoroomsapp.dto.UserDTO;
import com.mindtree.bohoroomsapp.entity.Hotel;
import com.mindtree.bohoroomsapp.entity.User;
import com.mindtree.bohoroomsapp.exception.BohoRoomsAppException;
import com.mindtree.bohoroomsapp.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/users/{id}")
	public ResponseEntity<?> getAllUsers(@Valid @PathVariable int id) throws BohoRoomsAppException {
		return new ResponseEntity<ResponseBody<Map<Hotel, User>>>(new ResponseBody<Map<Hotel, User>>(
				modelMapper.map(userService.getAllUsers(id), new TypeToken<Map<Hotel, User>>() {
				}.getType()), null, "User List Found", true), HttpStatus.OK);
	}

	@PutMapping(value = "/checkout/{user_id}/{room_id}/{rating}")
	public ResponseEntity<?> checkoutFromHotel(@Valid @PathVariable int user_id, @Valid @PathVariable int room_id,
			@Valid @PathVariable double rating) throws BohoRoomsAppException {

		return new ResponseEntity<ResponseBody<UserDTO>>(new ResponseBody<UserDTO>(
				modelMapper.map(userService.checkout(user_id, room_id, rating), UserDTO.class), null,
				"checkout Sucessfull", true), HttpStatus.OK);
	}

	@PutMapping(value = "/checkin/{user_id}/{room_id}")
	public ResponseEntity<?> checkInHotel(@PathVariable int user_id, @PathVariable int room_id)
			throws BohoRoomsAppException {
		return new ResponseEntity<ResponseBody<UserDTO>>(
				new ResponseBody<UserDTO>(modelMapper.map(userService.checkin(user_id, room_id), UserDTO.class), null,
						"checkin Sucessfully", true),
				HttpStatus.OK);
	}

	@PostMapping(value = "/add/user")
	public ResponseEntity<?> addUserPortal(@Valid @RequestBody UserDTO user) throws BohoRoomsAppException {

		return new ResponseEntity<ResponseBody<UserDTO>>(new ResponseBody<UserDTO>(modelMapper
				.map(userService.insertUser(modelMapper.map(user, User.class), user.getRooms()), UserDTO.class), null,
				"User Added Sucessfully", true), HttpStatus.OK);

	}

	@GetMapping(value = "/booked/users")
	public ResponseEntity<?> getBookedUsers() {

		return new ResponseEntity<ResponseBody<List<UserDTO>>>(new ResponseBody<List<UserDTO>>(
				modelMapper.map(userService.bookedUsers(), new TypeToken<List<UserDTO>>() {
				}.getType()), null, "Users List Found", true), HttpStatus.OK);

	}

	@GetMapping(value = "/checkout/users")
	public ResponseEntity<?> getCheckoutUsers() {
		return new ResponseEntity<ResponseBody<Map<Integer, UserDTO>>>(new ResponseBody<Map<Integer, UserDTO>>(
				modelMapper.map(userService.getCheckedOutUsers(), new TypeToken<Map<Integer, UserDTO>>() {
				}.getType()), null, "Users List Found", true), HttpStatus.OK);

	}

}
