package com.mindtree.companyapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.companyapp.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	Optional<Company> findBycompanyName(String companyName);

	Optional<Company> findByCompanyName(String companyName); 

}
