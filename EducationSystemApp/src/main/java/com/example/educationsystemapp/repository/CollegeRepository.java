package com.example.educationsystemapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.educationsystemapp.entity.College;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long>{

	Optional<College> findByCollegeName(String collegeName);

}
