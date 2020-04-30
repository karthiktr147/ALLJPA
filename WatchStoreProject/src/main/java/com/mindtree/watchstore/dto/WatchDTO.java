package com.mindtree.watchstore.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class WatchDTO {

	private long id;

	@NotNull(message = "Watch Name Cannot Be Empty")
	private String name;

//	@NotNull(message = "Watch Price Cannot Be Empty")
	@Size(min= 100, max=10000, message= "Watch price should be between this rang")
	private double price;

	public WatchDTO() {
	}

	public WatchDTO(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public WatchDTO(long id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
