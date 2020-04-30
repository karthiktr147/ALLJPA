package com.mindtree.watchstore.dto;

import javax.validation.constraints.NotNull;

public class PriceDTO {

	@NotNull(message = "Watch Price Cannot Be Empty")
	double price;

	public PriceDTO() {
	}

	public PriceDTO(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
