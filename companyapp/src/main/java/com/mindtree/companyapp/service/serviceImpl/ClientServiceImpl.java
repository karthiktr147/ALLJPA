package com.mindtree.companyapp.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.companyapp.entity.Client;
import com.mindtree.companyapp.entity.Company;
import com.mindtree.companyapp.exception.service.CompanyAppServiceException;
import com.mindtree.companyapp.exception.service.custom.ClientAlreadyExistsInCompanyException;
import com.mindtree.companyapp.exception.service.custom.ClientNotFoundException;
import com.mindtree.companyapp.exception.service.custom.CompanyNotFoundException;
import com.mindtree.companyapp.repository.ClientRepository;
import com.mindtree.companyapp.repository.CompanyRepository;
import com.mindtree.companyapp.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepositoryObject;

	@Autowired
	private CompanyRepository companyRepositoryObject;

	public Client saveClient(int companyId, Client client) throws CompanyAppServiceException {

		Company company = companyRepositoryObject.findById(companyId)
				.orElseThrow(() -> new CompanyNotFoundException("Company not found"));

		for (Client client1 : company.getClientList()) {
			if (client1.getClientName().equalsIgnoreCase(client.getClientName()))
				throw new ClientAlreadyExistsInCompanyException("Client Already Exists Exception");
		}
		//company.getClientList()
		company.getClientList().add(client);
		client.setCompany(company);
		clientRepositoryObject.save(client);

		return client;

	}

	@Override
	public List<Client> getAllClientData() throws CompanyAppServiceException {

		clientRepositoryObject.findAll().stream().findAny()
				.orElseThrow(() -> new ClientNotFoundException("Client Not Found"));

		return clientRepositoryObject.findAll();
	}

	@Override
	public List<Client> getClientsByCompanyIdData(int id) throws CompanyAppServiceException {

		Company companyData = companyRepositoryObject.findById(id)
				.orElseThrow(() -> new CompanyNotFoundException("Company not Found"));

		companyData.getClientList().stream().findAny()
				.orElseThrow(() -> new ClientNotFoundException("Client Not Found"));

		return companyData.getClientList();
	}

	@Override
	public Client updateClient(int id, double clientBudget) throws CompanyAppServiceException {
		Client client = clientRepositoryObject.findById(id)
				.orElseThrow(() -> new ClientNotFoundException("client not found"));

		client.setClientBudget(clientBudget);
		clientRepositoryObject.save(client);
		return clientRepositoryObject.findById(id).get();
	}

}
