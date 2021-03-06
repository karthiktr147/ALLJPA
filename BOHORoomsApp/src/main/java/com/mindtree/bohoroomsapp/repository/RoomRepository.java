package com.mindtree.bohoroomsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.bohoroomsapp.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

}
