package com.mindtree.kalingacompany.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.mindtree.kalingacompany.entity.MovieTicket;

public class BookingPortalDTO {

	private long porttalId;

	@NotNull(message = "Portal Name Cannot Be Empty")
	private String portalName;

	private List<MovieTicket> movieTicket;

	public BookingPortalDTO() {
		// TODO Auto-generated constructor stub
	}

	public BookingPortalDTO(long porttalId, String portalName, List<MovieTicket> movieTicket) {
		this.porttalId = porttalId;
		this.portalName = portalName;
		this.movieTicket = movieTicket;
	}

	public BookingPortalDTO(long porttalId, String portalName) {
		this.portalName = portalName;
		this.porttalId = porttalId;
	}
	
	public long getPorttalId() {
		return porttalId;
	}

	public void setPorttalId(long porttalId) {
		this.porttalId = porttalId;
	}

	public String getPortalName() {
		return portalName;
	}

	public void setPortalName(String portalName) {
		this.portalName = portalName;
	}

	public List<MovieTicket> getMovieTicket() {
		return movieTicket;
	}

	public void setMovieTicket(List<MovieTicket> movieTicket) {
		this.movieTicket = movieTicket;
	}

	
	
	

}
