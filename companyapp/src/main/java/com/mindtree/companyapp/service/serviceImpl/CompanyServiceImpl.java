package com.mindtree.companyapp.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.companyapp.dto.CompanyRevenue;
import com.mindtree.companyapp.entity.Company;
import com.mindtree.companyapp.exception.service.CompanyAppServiceException;
import com.mindtree.companyapp.exception.service.custom.ClientNotFoundException;
import com.mindtree.companyapp.exception.service.custom.CompanyAlreadyExistsException;
import com.mindtree.companyapp.exception.service.custom.CompanyNotFoundException;
import com.mindtree.companyapp.repository.CompanyRepository;
import com.mindtree.companyapp.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepositoryObject;

	@Override
	public Company saveCompany(Company company) throws CompanyAppServiceException {

		if (companyRepositoryObject.findBycompanyName(company.getCompanyName()).isPresent())
			throw new CompanyAlreadyExistsException("Company Already Exists.");

		companyRepositoryObject.save(company);
		return companyRepositoryObject.findBycompanyName(company.getCompanyName()).get();

	}

	@Override
	public List<Company> returnCompanyList() throws CompanyAppServiceException {

		companyRepositoryObject.findAll().stream().findAny()
				.orElseThrow(() -> new CompanyNotFoundException("Company is Not Found."));
		return companyRepositoryObject.findAll();
	}
	
	@Override
	public CompanyRevenue getCompanyRevenue(String companyName) throws CompanyAppServiceException {

		Company company = companyRepositoryObject.findByCompanyName(companyName)
				.orElseThrow(() -> new CompanyNotFoundException("Company Is Not Found"));

		company.getClientList().stream().findAny()
				.orElseThrow(() -> new ClientNotFoundException("Client Is Not Found"));

		double revenue = company.getClientList().stream().map(c -> c.getClientBudget()).reduce(0.0, (a, b) -> a + b);

		return new CompanyRevenue(company.getCompanyId(), company.getCompanyName(), revenue);

	}
}
