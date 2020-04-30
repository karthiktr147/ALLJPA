package com.example.educationsystemapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.educationsystemapp.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

	Optional<University> findByUniversityName(String universityName);

}
