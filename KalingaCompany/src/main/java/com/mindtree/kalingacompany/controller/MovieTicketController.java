package com.mindtree.kalingacompany.controller;

import java.util.Set;

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

import com.mindtree.kalingacompany.dto.MovieTicketDTO;
import com.mindtree.kalingacompany.dto.ResponseBody;
import com.mindtree.kalingacompany.entity.MovieTicket;
import com.mindtree.kalingacompany.exception.KalingaCompanyAppException;
import com.mindtree.kalingacompany.service.MovieTicketService;

@RestController
public class MovieTicketController {

	@Autowired
	private MovieTicketService movieTicketService;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * @param Movie ticket Object and portalName to store Movie Ticket in that
	 *              Booking Portal.
	 * @return saved Movie Ticket Object
	 *
	 */
	@PostMapping("/movieticket/{portalName}")
	public ResponseEntity<?> addMovieTicket(@Valid @RequestBody MovieTicketDTO movieTicket,
			@PathVariable String portalName) throws KalingaCompanyAppException {

		return new ResponseEntity<ResponseBody<MovieTicketDTO>>(
				new ResponseBody<MovieTicketDTO>(
						modelMapper.map(movieTicketService.addMovieTicketData(
								modelMapper.map(movieTicket, MovieTicket.class), portalName), MovieTicketDTO.class),
						null, "Movie ticket is booked Successfully", true),
				HttpStatus.OK);

	}

	/**
	 * @param movieName to delete Movie ticket.
	 *
	 */
	@DeleteMapping("/movieticket/{movieName}")
	public ResponseEntity<?> deleteMovie(@PathVariable String movieName) throws KalingaCompanyAppException {
		movieTicketService.deleteMovieData(movieName);
		return new ResponseEntity<ResponseBody<Void>>(
				new ResponseBody<Void>(null, null, "Movie " + movieName + " is Deleted.", true), HttpStatus.OK);

	}

	/**
	 * @param portalName to find movie ticket with price more than 500.
	 * @return set all Movie Ticket with price more than 500.
	 *
	 */
	@GetMapping("/movieticket/{portalName}")
	public ResponseEntity<?> getMaxPriceOfMovie(@PathVariable String portalName) throws KalingaCompanyAppException {

		return new ResponseEntity<ResponseBody<Set<MovieTicketDTO>>>(new ResponseBody<Set<MovieTicketDTO>>(modelMapper
				.map(movieTicketService.getMaxPriceOfMovieData(portalName), new TypeToken<Set<MovieTicketDTO>>() {
				}.getType()), null, "MovieTickte With price Above 500 is found.", true), HttpStatus.OK);
	}
}
