package com.mindtree.kalingacompany.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.kalingacompany.entity.BookingPortal;

@Repository
public interface BookingPortalRepository extends JpaRepository<BookingPortal, Long> {

	Optional<BookingPortal> findByPortalName(String portalName);

	

}
