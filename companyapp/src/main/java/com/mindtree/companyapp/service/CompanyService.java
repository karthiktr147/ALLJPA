package com.mindtree.companyapp.service;

import java.util.List;

import com.mindtree.companyapp.dto.CompanyRevenue;
import com.mindtree.companyapp.entity.Company;
import com.mindtree.companyapp.exception.service.CompanyAppServiceException;

public interface CompanyService {

	public List<Company> returnCompanyList() throws CompanyAppServiceException ;

	public Company saveCompany(Company company) throws CompanyAppServiceException ; 
	
	public CompanyRevenue getCompanyRevenue(String companyName) throws CompanyAppServiceException;
}
