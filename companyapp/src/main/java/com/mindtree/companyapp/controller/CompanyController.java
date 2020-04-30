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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.companyapp.dto.CompanyDTO;
import com.mindtree.companyapp.dto.CompanyRevenue;
import com.mindtree.companyapp.dto.ResponseBody;
import com.mindtree.companyapp.entity.Company;
import com.mindtree.companyapp.exception.CompanyAppException;
import com.mindtree.companyapp.service.CompanyService;

@RestController
public class CompanyController {

	@Autowired
	private CompanyService companyServiceObject;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/company")
	public ResponseEntity<?> addCompany(@Valid @RequestBody CompanyDTO company) throws CompanyAppException {

		return new ResponseEntity<ResponseBody<CompanyDTO>>(new ResponseBody<CompanyDTO>(modelMapper
				.map(companyServiceObject.saveCompany(modelMapper.map(company, Company.class)), CompanyDTO.class), null,
				"Company Added Sucessfully", true), HttpStatus.OK);

	}

	@GetMapping("/company")
	public ResponseEntity<?> getAllCompany() throws CompanyAppException {

		return new ResponseEntity<ResponseBody<List<CompanyDTO>>>(new ResponseBody<List<CompanyDTO>>(
				modelMapper.map(companyServiceObject.returnCompanyList(), new TypeToken<List<CompanyDTO>>() {
				}.getType()), null, "Companys Data Found", true), HttpStatus.OK);

	}

	@GetMapping("/company/{companyName}")
	public ResponseEntity<?> getCompanyRevenue(@PathVariable String companyName) throws CompanyAppException {

		return new ResponseEntity<ResponseBody<@Valid CompanyRevenue>>(
				new ResponseBody<@Valid CompanyRevenue>(companyServiceObject.getCompanyRevenue(companyName), null,
						"Company Revenue is Found", true),
				HttpStatus.OK);
	}

}
