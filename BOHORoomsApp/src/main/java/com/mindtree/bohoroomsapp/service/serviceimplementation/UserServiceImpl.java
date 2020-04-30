package com.mindtree.bohoroomsapp.service.serviceimplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.bohoroomsapp.entity.Checkout;
import com.mindtree.bohoroomsapp.entity.Hotel;
import com.mindtree.bohoroomsapp.entity.Rating;
import com.mindtree.bohoroomsapp.entity.Room;
import com.mindtree.bohoroomsapp.entity.User;
import com.mindtree.bohoroomsapp.exception.service.BohoRoomsAppServiceException;
import com.mindtree.bohoroomsapp.exception.service.custom.RatingLimitExceedException;
import com.mindtree.bohoroomsapp.exception.service.custom.RoomIsAlreadyBookedException;
import com.mindtree.bohoroomsapp.exception.service.custom.RoomNotFoundException;
import com.mindtree.bohoroomsapp.exception.service.custom.UserIsNotFoundException;
import com.mindtree.bohoroomsapp.repository.CheckoutRepository;
import com.mindtree.bohoroomsapp.repository.HotelRepository;
import com.mindtree.bohoroomsapp.repository.RatingRepository;
import com.mindtree.bohoroomsapp.repository.RoomRepository;
import com.mindtree.bohoroomsapp.repository.UserRepository;
import com.mindtree.bohoroomsapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private CheckoutRepository checkoutRepository;

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public User insertUser(User user, Set<Room> rooms4) throws BohoRoomsAppServiceException {

		User user1 = new User(user.getUserId(), user.getName());
		System.out.println(user1.getRooms());
		int id = 0;
		for (Room r : rooms4) {
			id = r.getId();
			if (id != 0) {
				Room room = roomRepository.findById(id).get();
				if (room.getUser() == null) {

					room.setUser(user1);
					System.out.println(room.toString());
					user1.setRooms(room);
					roomRepository.save(room);
					user1.setUserId(room.getUser().getUserId());
					System.out.println("dfg");
				} else {
					userRepository.save(user1);
					System.out.println(room.toString() + "!11");
					throw new RoomIsAlreadyBookedException("Room already booked");
				}
			}
		}
		User u = userRepository.save(user1);
		System.out.println(u.getRooms() + "qwqw");
		return u;
	}

	@Override
	public Map<Integer, User> getAllUers(int hotelId) {
		// Hotel h=hotelRepo.findById(hotelId).get();
		Map<Integer, User> map = new HashMap<Integer, User>();
		List<Room> rooms = roomRepository.findAll();
		// List<User> users = new ArrayList<User>();
		for (Room room : rooms) {
			if (room.getHotel().getHotelId() == hotelId) {
				User use = room.getUser();
				// users.add(use);
				map.put(use.getUserId(), use);
			}

		}

		return map;
	}

	@Override
	public Map<Integer, Hotel> getAllHotels(double booking) {
		List<Hotel> hotel = hotelRepository.findAll();
		Map<Integer, Hotel> map = new TreeMap<Integer, Hotel>();
		List<Room> rooms = roomRepository.findAll();
		for (Hotel h : hotel) {
			int sum = 0;
			for (Room r : rooms) {
				if (r.getHotel().getHotelId() == h.getHotelId())
					sum += r.getCost();
			}
			if (sum > booking) {
				map.put(h.getHotelId(), h);
			}
		}
		return map;
	}

	@Override
	public Map<Hotel, User> getAllUsers(int id) throws BohoRoomsAppServiceException {
		Hotel h = hotelRepository.findById(id).get();
		if (h != null) {
			List<Room> rooms = roomRepository.findAll();
			Map<Hotel, User> allUsers = new HashMap<Hotel, User>();
			for (Room r : rooms) {
				if (r.getHotel() == h) {
					if (r.getUser() != null)
						allUsers.put(h, r.getUser());
				}
			}
			return allUsers;
		} else
			throw new RoomNotFoundException("no such room");
	}

	@Override
	public User checkout(int user_id, int room_id, double rating) throws BohoRoomsAppServiceException {
		System.out.println(rating);
		if (!(rating < 6.0)) {
			throw new RatingLimitExceedException("Rating should be less or equal to 5");
		}
		User user = userRepository.findById(user_id).get();
		if (user != null) {
			Room room = roomRepository.findById(room_id).get();
			if (room != null) {
				room.setUser(null);
				Hotel h = room.getHotel();
				Checkout checkout1 = new Checkout(0, h, user);
				checkoutRepository.save(checkout1);
				Rating r = new Rating(0, rating, h);
				ratingRepository.save(r);
				roomRepository.save(room);
				user.getRooms().remove(room);
				return user;
			} else
				throw new RoomNotFoundException("room does not exists");
		} else
			throw new UserIsNotFoundException("no such user");
	}

	@Override
	public User checkin(int user_id, int room_id) throws BohoRoomsAppServiceException {
		User user = userRepository.findById(user_id).get();
		if (user != null) {
			Room room = roomRepository.findById(room_id).get();
			if (room != null) {
				if (room.getUser() == null) {
					room.setUser(user);
					roomRepository.save(room);
					user.setRooms(room);
					return user;
				} else
					throw new RoomIsAlreadyBookedException("room is already booked");
			} else
				throw new RoomNotFoundException("room does not exists");
		} else
			throw new UserIsNotFoundException("no such user");
	}

	@Override
	public List<User> bookedUsers() {
		List<User> users = new ArrayList<User>();
		List<Room> rooms = roomRepository.findAll();
		List<User> users1 = userRepository.findAll();
		for (User u : users1) {
			Room r1 = rooms.stream().filter(r -> r.getUser() == u).map(r -> r).collect(Collectors.toList()).get(0);
			users.add(r1.getUser());

		}
		return users;
	}

	@Override
	public Map<Integer, User> getCheckedOutUsers() {
		List<Checkout> checkout1 = checkoutRepository.findAll();
		Map<Integer, User> users = new HashMap<Integer, User>();
		for (Checkout ch : checkout1) {
			System.out.println(ch.toString() + "hhhhhhh");
			users.put(ch.getId(), ch.getUser());
		}
		// users.forEach(u->System.out.println(u));
		return users;
	}

}
