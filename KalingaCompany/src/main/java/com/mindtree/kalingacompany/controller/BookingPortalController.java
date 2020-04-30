package com.mindtree.kalingacompany.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.kalingacompany.dto.BookingPortalDTO;
import com.mindtree.kalingacompany.dto.ResponseBody;
import com.mindtree.kalingacompany.entity.BookingPortal;
import com.mindtree.kalingacompany.exception.KalingaCompanyAppException;
import com.mindtree.kalingacompany.service.BookingPortalService;

@RestController

public class BookingPortalController {

	@Autowired
	private BookingPortalService bookingPortalService;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * @param Booking Portal Object to save
	 * @return saved Booking Portal Object
	 */
	@PostMapping("/bookingportal") 
	public ResponseEntity<?> addBookingPortal(@Valid @RequestBody BookingPortalDTO bookingPortal)
			throws KalingaCompanyAppException {

		return new ResponseEntity<ResponseBody<BookingPortalDTO>>(
				new ResponseBody<BookingPortalDTO>(
						modelMapper.map(bookingPortalService.addBookingPortalData(
								modelMapper.map(bookingPortal, BookingPortal.class)), BookingPortalDTO.class),
						null, "Booking Portal Added Sucessfully", true),
				HttpStatus.OK);

	}

	/**
	 * @return List of all Booking Portal and Movie Ticket in their
	 */
	@GetMapping("/bookingportal")
	public ResponseEntity<?> getAllBookingPortal() throws KalingaCompanyAppException {

		return new ResponseEntity<ResponseBody<List<BookingPortalDTO>>>(new ResponseBody<List<BookingPortalDTO>>(
				modelMapper.map(bookingPortalService.getBookingPortalData(), new TypeToken<List<BookingPortalDTO>>() {
				}.getType()), null, "Booking Portal List Found", true), HttpStatus.OK);

	}

	/**
	 * @param portalName to delete Booking Portal.
	 *  
	 */
	@DeleteMapping("/bookingportal/{portalName}")
	public ResponseEntity<?> deleteBookingPortal(@PathVariable String portalName) throws KalingaCompanyAppException {

		bookingPortalService.deleteBookingPorttalData(portalName);
		return new ResponseEntity<ResponseBody<Void>>(
				new ResponseBody<Void>(null, null, "Booking Porttal with name " + portalName + " is deleted. ", true),
				HttpStatus.OK);

	}
}

/*
 * BookingPortal dtotoentity = modelMapper.map(bookingPortal,
 * BookingPortal.class); BookingPortal outputofservice =
 * bookingPortalService.addBookingPortalData(dtotoentity); BookingPortalDTO
 * entitytodto = modelMapper.map(outputofservice, BookingPortalDTO.class);
 * return new ResponseEntity<ResponseBody<BookingPortalDTO>>( new
 * ResponseBody<BookingPortalDTO>(entitytodto, null,
 * "Booking Portal Added Sucessfully", true), HttpStatus.OK);
 */
