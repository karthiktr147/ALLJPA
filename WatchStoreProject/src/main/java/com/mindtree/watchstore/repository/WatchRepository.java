package com.mindtree.watchstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.watchstore.entity.Watch;

@Repository
public interface WatchRepository extends JpaRepository<Watch, Long> {

	Optional<Watch> findByName(String name);
}
