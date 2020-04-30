package com.mindtree.watchstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.watchstore.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

	Optional<Store> findByName(String name);

}
