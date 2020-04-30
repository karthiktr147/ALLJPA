package com.mindtree.bohoroomsapp.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.mindtree.bohoroomsapp.entity.Room;

public class HotelDTO {

	private int hotelId;

	@NotBlank(message = "Hotel Name Can Not Be Empty !")
	private String hotelname;

	private Set<Room> rooms = new HashSet<Room>();

	public HotelDTO() {
		// TODO Auto-generated constructor stub
	}

	public HotelDTO(int hotelId, @NotBlank(message = "Hotel Name Can Not Be Empty !") String hotelname,
			Set<Room> rooms) {
		this.hotelId = hotelId;
		this.hotelname = hotelname;
		this.rooms = rooms;
	}

	public HotelDTO(int hotelId, @NotBlank(message = "Hotel Name Can Not Be Empty !") String hotelname) {
		this.hotelId = hotelId;
		this.hotelname = hotelname;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

}
