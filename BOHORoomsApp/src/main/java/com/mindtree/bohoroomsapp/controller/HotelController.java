package com.mindtree.bohoroomsapp.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.bohoroomsapp.dto.HotelDTO;
import com.mindtree.bohoroomsapp.dto.ResponseBody;
import com.mindtree.bohoroomsapp.entity.Hotel;
import com.mindtree.bohoroomsapp.exception.BohoRoomsAppException;
import com.mindtree.bohoroomsapp.service.HotelService;

@RestController
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping(value = "/hotel")
	public ResponseEntity<?> addHotel(@Valid @RequestBody HotelDTO hotel) throws BohoRoomsAppException {

		return new ResponseEntity<ResponseBody<HotelDTO>>(new ResponseBody<HotelDTO>(
				modelMapper.map(hotelService.addHotelData(modelMapper.map(hotel, Hotel.class)), HotelDTO.class), null,
				"Hotel Add With Rooms Sucessfully", true), HttpStatus.OK);
	}

	@GetMapping(value = "/hotel/{amount}")
	public ResponseEntity<?> getAllhotels(@PathVariable double amount) {
		return new ResponseEntity<ResponseBody<Map<Hotel, Double>>>(new ResponseBody<Map<Hotel, Double>>(
				modelMapper.map(hotelService.hotelsGreaterThan(amount), new TypeToken<Map<Hotel, Double>>() {
				}.getType()), null, "Hotel List Found", true), HttpStatus.OK);

	}

	@GetMapping(value = "/revenue")
	public ResponseEntity<?> getRevenues() {
		return new ResponseEntity<ResponseBody<Map<Hotel, Double>>>(new ResponseBody<Map<Hotel, Double>>(
				modelMapper.map(hotelService.getRevenue(), new TypeToken<Map<Hotel, Double>>() {
				}.getType()), null, "Hotel Revenue List Found", true), HttpStatus.OK);
	}

	@GetMapping(value = "/average")
	public ResponseEntity<?> getAverages() {
		return new ResponseEntity<ResponseBody<Map<Hotel, Double>>>(new ResponseBody<Map<Hotel, Double>>(
				modelMapper.map(hotelService.getAverage(), new TypeToken<Map<Hotel, Double>>() {
				}.getType()), null, "Hotel Average List Found", true), HttpStatus.OK);
	}

	@GetMapping(value = "/sum")
	public ResponseEntity<?> getSum() {
		return new ResponseEntity<ResponseBody<Double>>(
				new ResponseBody<Double>(modelMapper.map(hotelService.getTotalSum(), Double.class), null,
						"Hotels Revenue Sum Found", true),
				HttpStatus.OK);
	}

	@GetMapping(value = "/greater")
	public ResponseEntity<?> getUsersMoreThan2() {
		return new ResponseEntity<ResponseBody<Map<Hotel, Integer>>>(new ResponseBody<Map<Hotel, Integer>>(
				modelMapper.map(hotelService.getForUsersGreaterThanTwo(), new TypeToken<Map<Hotel, Integer>>() {
				}.getType()), null, "Hotel list for users greater than 2 Found", true), HttpStatus.OK);
	}

	@GetMapping(value = "/Rating/greater")
	public ResponseEntity<?> getUsersMoreThan2Rating() {
		return new ResponseEntity<ResponseBody<Map<HotelDTO, Double>>>(
				new ResponseBody<Map<HotelDTO, Double>>(modelMapper.map(hotelService.getForUsersGreaterThanTwoRatings(),
						new TypeToken<Map<HotelDTO, Double>>() {
						}.getType()), null, "Hotel list for users greater than 2 and rating greater than 4 Found",
						true),
				HttpStatus.OK);
	}

}
