package com.mindtree.bohoroomsapp.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mindtree.bohoroomsapp.entity.Hotel;
import com.mindtree.bohoroomsapp.entity.Room;
import com.mindtree.bohoroomsapp.entity.User;
import com.mindtree.bohoroomsapp.exception.service.BohoRoomsAppServiceException;

public interface UserService {

	public User insertUser(User user, Set<Room> usersroom) throws BohoRoomsAppServiceException;

	public Map<Integer, User> getAllUers(int hotelId);

	public Map<Integer, Hotel> getAllHotels(double booking);

	public Map<Hotel, User> getAllUsers(int id) throws BohoRoomsAppServiceException;

	public User checkin(int user_id, int room_id) throws BohoRoomsAppServiceException;

	public User checkout(int user_id, int room_id, double rating) throws BohoRoomsAppServiceException;

	public List<User> bookedUsers();

	public Map<Integer, User> getCheckedOutUsers();

}
