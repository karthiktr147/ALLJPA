package com.mindtree.companyapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.companyapp.dto.ClientDTO;
import com.mindtree.companyapp.dto.ResponseBody;
import com.mindtree.companyapp.entity.Client;
import com.mindtree.companyapp.exception.CompanyAppException;
import com.mindtree.companyapp.service.ClientService;

@RestController

public class ClientController {

	@Autowired
	private ClientService clientServiceObject;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/client/{companyId}")
	public ResponseEntity<?> addClient(@PathVariable int companyId, @Valid @RequestBody ClientDTO client)
			throws CompanyAppException {

		return new ResponseEntity<ResponseBody<ClientDTO>>(new ResponseBody<ClientDTO>(modelMapper
				.map(clientServiceObject.saveClient(companyId, modelMapper.map(client, Client.class)), ClientDTO.class),
				null, "Client added Successfully", true), HttpStatus.OK);

	}

	@GetMapping("/client")
	public ResponseEntity<?> getAllClient() throws CompanyAppException {

		return new ResponseEntity<ResponseBody<List<ClientDTO>>>(new ResponseBody<List<ClientDTO>>(
				modelMapper.map(clientServiceObject.getAllClientData(), new TypeToken<List<ClientDTO>>() {
				}.getType()), null, "Clients Data Found", true), HttpStatus.OK);

	}

	@GetMapping("/client/{companyId}")
	public ResponseEntity<?> getClientsByCompanyId(@PathVariable int companyId) throws CompanyAppException {

		return new ResponseEntity<ResponseBody<List<ClientDTO>>>(new ResponseBody<List<ClientDTO>>(modelMapper
				.map(clientServiceObject.getClientsByCompanyIdData(companyId), new TypeToken<List<ClientDTO>>() {
				}.getType()), null, "Clients Data Found", true), HttpStatus.OK);
	}

	@PutMapping("/client/{clientId}/{clientBudget}")
	public ResponseEntity<?> updateAParticularClient(@PathVariable int clientId, @PathVariable double clientBudget)
			throws CompanyAppException {

		return new ResponseEntity<ResponseBody<ClientDTO>>(new ResponseBody<ClientDTO>(
				modelMapper.map(clientServiceObject.updateClient(clientId, clientBudget), ClientDTO.class), null,
				"Client Data updated Sucessfully", true), HttpStatus.OK);

	}

}
