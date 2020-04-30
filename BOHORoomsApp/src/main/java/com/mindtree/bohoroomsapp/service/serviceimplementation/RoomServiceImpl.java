package com.mindtree.bohoroomsapp.service.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.bohoroomsapp.entity.Room;
import com.mindtree.bohoroomsapp.entity.User;
import com.mindtree.bohoroomsapp.repository.RoomRepository;
import com.mindtree.bohoroomsapp.repository.UserRepository;
import com.mindtree.bohoroomsapp.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private UserRepository userRepository;

	public Room bookRoom(int id, int userId) {
		Room r = roomRepository.findById(id).get();
		System.out.println(r.toString());
		User use = userRepository.findById(userId).get();
		r.setUser(use);
		use.setRooms(r);
		roomRepository.save(r);
		return r;
	}

}
