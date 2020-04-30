package com.mindtree.kalingacompany.service.serviecimplementaion;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.kalingacompany.entity.BookingPortal;
import com.mindtree.kalingacompany.entity.MovieTicket;
import com.mindtree.kalingacompany.exception.service.KalingaCompanyAppServiceException;
import com.mindtree.kalingacompany.exception.service.custom.BookingPorttalNotFoundException;
import com.mindtree.kalingacompany.exception.service.custom.MovieNotFoundException;
import com.mindtree.kalingacompany.exception.service.custom.MovieTicketIsAlreadyBookedException;
import com.mindtree.kalingacompany.exception.service.custom.NoMovieTicketIsBookedException;
import com.mindtree.kalingacompany.repository.BookingPortalRepository;
import com.mindtree.kalingacompany.repository.MovieTicketRepository;
import com.mindtree.kalingacompany.service.MovieTicketService;

@Service
public class MovieTicketServiecImpl implements MovieTicketService {

	@Autowired
	private MovieTicketRepository movieTicketRepository;

	@Autowired
	private BookingPortalRepository bookingPortalRepository;

	@Override
	public MovieTicket addMovieTicketData(MovieTicket movieTicketObj, String portalName)
			throws KalingaCompanyAppServiceException {

		BookingPortal bookingPortal = bookingPortalRepository.findByPortalName(portalName)
				.orElseThrow(() -> new BookingPorttalNotFoundException("Booking Portal Not Found"));

		for (MovieTicket movieTicket2 : bookingPortal.getMovieTicket()) {
			if (movieTicket2.getMovieName().equalsIgnoreCase(movieTicketObj.getMovieName()))
				throw new MovieTicketIsAlreadyBookedException("Movie Ticket Is Already Booked");
		}

		bookingPortal.getMovieTicket().add(movieTicketObj);
		movieTicketObj.setBookingPortal(bookingPortal);
		movieTicketRepository.save(movieTicketObj);
		return new MovieTicket(movieTicketObj.getMovieTicketId(), movieTicketObj.getMovieName(),
				movieTicketObj.getTicketPrice());

	}

	@Override
	public void deleteMovieData(String movieName) throws KalingaCompanyAppServiceException {

		MovieTicket movieTicket = movieTicketRepository.findByMovieName(movieName)
				.orElseThrow(() -> new MovieNotFoundException("Movie Not Found."));

		movieTicketRepository.delete(movieTicket);

	}

	@Override
	public Set<MovieTicket> getMaxPriceOfMovieData(String portalName) throws KalingaCompanyAppServiceException {
		BookingPortal bookingPortal = bookingPortalRepository.findByPortalName(portalName)
				.orElseThrow(() -> new BookingPorttalNotFoundException("Booking Portal Not Found"));

		bookingPortal.getMovieTicket().stream().findAny()
				.orElseThrow(() -> new NoMovieTicketIsBookedException("No Movie ticket is booked."));

		return bookingPortal.getMovieTicket().stream().filter(movieTicket -> movieTicket.getTicketPrice() > 500)
				.collect(Collectors.toSet());

	}

}
