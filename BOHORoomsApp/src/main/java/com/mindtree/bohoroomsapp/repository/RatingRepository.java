package com.mindtree.bohoroomsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.bohoroomsapp.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
