package com.mindtree.companyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.companyapp.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}