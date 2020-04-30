package com.mindtree.companyapp.service;

import java.util.List;

import com.mindtree.companyapp.entity.Client;
import com.mindtree.companyapp.exception.service.CompanyAppServiceException;

public interface ClientService {
	public Client saveClient(int id, Client clientObject) throws CompanyAppServiceException ;

	public List<Client> getAllClientData() throws CompanyAppServiceException;

	public List<Client> getClientsByCompanyIdData(int companyId) throws CompanyAppServiceException;
 
	public Client updateClient(int clientId, double clientBudget) throws CompanyAppServiceException;  

	
}
