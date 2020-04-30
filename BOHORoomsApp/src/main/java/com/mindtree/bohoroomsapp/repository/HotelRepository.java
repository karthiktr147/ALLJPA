package com.mindtree.bohoroomsapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.bohoroomsapp.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

	Optional<Hotel> findByHotelName(String hotelName);

}
