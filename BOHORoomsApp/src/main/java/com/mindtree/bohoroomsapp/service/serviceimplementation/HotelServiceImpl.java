package com.mindtree.bohoroomsapp.service.serviceimplementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.bohoroomsapp.entity.Hotel;
import com.mindtree.bohoroomsapp.entity.Rating;
import com.mindtree.bohoroomsapp.entity.Room;
import com.mindtree.bohoroomsapp.exception.service.BohoRoomsAppServiceException;
import com.mindtree.bohoroomsapp.exception.service.custom.HotelAlreadyExistException;
import com.mindtree.bohoroomsapp.repository.HotelRepository;
import com.mindtree.bohoroomsapp.repository.RatingRepository;
import com.mindtree.bohoroomsapp.repository.RoomRepository;
import com.mindtree.bohoroomsapp.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Hotel addHotelData(Hotel hotel) throws BohoRoomsAppServiceException {
		if (hotelRepository.findByHotelName(hotel.getHotelName()).isPresent()) {
			throw new HotelAlreadyExistException("Hotel Already Exists !");
		}

		Hotel hotel2 = new Hotel();
		hotel2.setHotelName(hotel.getHotelName());
		hotel2.setRooms(hotel.getRooms());
		hotel2 = hotelRepository.save(hotel2);

		// boolean exist = false;

		Set<Room> room = hotel.getRooms();
		for (Room r : room) {

			r.setHotel(hotel2);
		}

		this.roomRepository.saveAll(room);

		return hotelRepository.getOne(hotel2.getHotelId());
	}

	public Map<Hotel, Double> hotelsGreaterThan(double amount) {
		List<Room> rooms = roomRepository.findAll();
		List<Hotel> hotels = hotelRepository.findAll();
		Map<Hotel, Double> hotel = new HashMap<Hotel, Double>();
		for (Hotel h : hotels) {
			double sum = rooms.stream().filter(r -> r.getHotel() == h).map(r -> r.getCost()).reduce(0.0, Double::sum);

			if (sum > amount) {
				hotel.put(h, sum);
			}
		}
		return hotel;
	}

	public Map<Hotel, Double> getRevenue() {
		List<Hotel> hotel = hotelRepository.findAll();
		List<Room> rooms = roomRepository.findAll();

		Map<Hotel, Double> revenue = new HashMap<Hotel, Double>();
		for (Hotel h : hotel) {
			double sum = rooms.stream().filter(r -> r.getHotel() == h).map(r -> r.getCost()).reduce(0.0, Double::sum);
			revenue.put(h, sum);

		}
		revenue.forEach((k, v) -> System.out.println(k + " " + v));
		return revenue;
	}

	public Map<Hotel, Double> getAverage() {
		List<Hotel> hotel = hotelRepository.findAll();
		List<Room> rooms = roomRepository.findAll();

		Map<Hotel, Double> revenue = new HashMap<Hotel, Double>();
		for (Hotel h : hotel) {
			List<Room> room = rooms.stream().filter(r -> r.getHotel() == h).map(r -> r).collect(Collectors.toList());
			double sum = rooms.stream().filter(r -> r.getHotel() == h).map(r -> r.getCost()).reduce(0.0, Double::sum);
			double average = sum / room.size();
			revenue.put(h, average);

		}
		revenue.forEach((k, v) -> System.out.println(k + " " + v));
		return revenue;
	}

	public Double getTotalSum() {
		List<Room> rooms = roomRepository.findAll();
		double sum = rooms.stream().filter(r -> r.getCost() > 0).map(r -> r.getCost()).reduce(0.0, Double::sum);
		return sum;
	}

	public Map<Hotel, Integer> getForUsersGreaterThanTwo() {
		List<Hotel> hotel = hotelRepository.findAll();
		List<Room> rooms = roomRepository.findAll();
		Map<Hotel, Integer> users = new HashMap<Hotel, Integer>();
		for (Hotel h : hotel) {
			int count = 0;
			List<Room> room = rooms.stream().filter(r -> r.getHotel() == h).map(r -> r).collect(Collectors.toList());
			for (Room r : room) {
				if (r.getUser() != null)
					count++;
			}
			if (count > 2)
				users.put(h, count);
		}
		return users;
	}

	public Map<Hotel, Double> getForUsersGreaterThanTwoRatings() {
		List<Hotel> hotel = hotelRepository.findAll();
		List<Rating> rating = ratingRepository.findAll();
		List<Room> rooms = roomRepository.findAll();
		Map<Hotel, Double> users = new HashMap<Hotel, Double>();

		for (Hotel h : hotel) {
			List<Rating> rating2 = rating.stream().filter(r -> r.getHotel() == h).map(r -> r)
					.collect(Collectors.toList());
			double rating1 = rating.stream().filter(r -> r.getHotel() == h).map(r -> r.getRating()).reduce(0.0,
					Double::sum);
			int count = 0;
			double finalRating = rating1 / rating2.size();
			List<Room> room = rooms.stream().filter(r -> r.getHotel() == h).map(r -> r).collect(Collectors.toList());
			for (Room r : room) {
				if (r.getUser() != null)
					count++;
			}
			if (count > 2) {
				if (finalRating > 4)
					users.put(h, finalRating);
			}

		}
		return users;
	}

}
