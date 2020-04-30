package com.mindtree.bohoroomsapp.dto;

import javax.validation.constraints.Min;

public class RoomDTO {

	private int id;

	@Min(value = 1000, message = "Room Cost Should be above 1000 !")
	private double cost;

	public RoomDTO() {
		// TODO Auto-generated constructor stub
	}

	public RoomDTO(int id, @Min(value = 1000, message = "Room Cost Should be above 1000 !") double cost) {
		this.id = id;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
