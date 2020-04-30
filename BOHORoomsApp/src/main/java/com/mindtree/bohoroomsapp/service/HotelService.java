package com.mindtree.bohoroomsapp.service;

import java.util.Map;

import com.mindtree.bohoroomsapp.entity.Hotel;
import com.mindtree.bohoroomsapp.exception.service.BohoRoomsAppServiceException;

public interface HotelService {

	Hotel addHotelData(Hotel Hotel) throws BohoRoomsAppServiceException;

	public Map<Hotel, Double> hotelsGreaterThan(double amount);

	public Map<Hotel, Double> getRevenue();

	public Map<Hotel, Double> getAverage();

	public Double getTotalSum();

	public Map<Hotel, Integer> getForUsersGreaterThanTwo();

	Map<Hotel, Double> getForUsersGreaterThanTwoRatings();

}
