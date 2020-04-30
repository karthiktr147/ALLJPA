package com.mindtree.bohoroomsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.bohoroomsapp.entity.Checkout;

public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {

}
