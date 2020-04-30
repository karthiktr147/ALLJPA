package com.mindtree.bohoroomsapp.service;

import com.mindtree.bohoroomsapp.entity.Room;

@FunctionalInterface
public interface RoomService {
	
	public Room bookRoom(int id, int userId);
}
