package com.mindtree.kalingacompany.dto;

import java.util.Comparator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.mindtree.kalingacompany.entity.BookingPortal;

public class MovieTicketDTO implements Comparator<MovieTicketDTO> {

	private long movieTicketId;

	@NotBlank(message = "Movie Name Cannot Be Empty")
	private String movieName;

	@Min(value = 100, message = "Movie Ticket Price Should Not Be Less than 100")
	private double ticketPrice;

	private BookingPortal bookingPortal;

	public MovieTicketDTO() {
		// TODO Auto-generated constructor stub
	}

	public MovieTicketDTO(long movieTicketId, String movieName, double ticketPrice, BookingPortal bookingPortal) {
		this.movieTicketId = movieTicketId;
		this.movieName = movieName;
		this.ticketPrice = ticketPrice;
		this.bookingPortal = bookingPortal;
	}

	public MovieTicketDTO(long movieTicketId, String movieName, double ticketPrice) {

		this.movieName = movieName;
		this.ticketPrice = ticketPrice;
		this.movieTicketId = movieTicketId;
	}

	public long getMovieTicketId() {
		return movieTicketId;
	}

	public void setMovieTicketId(long movieTicketId) {
		this.movieTicketId = movieTicketId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public BookingPortal getBookingPortal() {
		return bookingPortal;
	}

	public void setBookingPortal(BookingPortal bookingPortal) {
		this.bookingPortal = bookingPortal;
	}

	@Override
	public int compare(MovieTicketDTO o1, MovieTicketDTO o2) {
		// TODO Auto-generated method stub
		return Long.compare(o1.getMovieTicketId(), o2.getMovieTicketId());
	}

}
