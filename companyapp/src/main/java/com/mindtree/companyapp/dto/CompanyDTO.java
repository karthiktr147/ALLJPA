package com.mindtree.companyapp.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.mindtree.companyapp.entity.Client;

public class CompanyDTO {

	private int companyId;

	@NotBlank(message = "Company Name Can Not be Empty")
	private String companyName;

	private List<Client> clientList;

	public CompanyDTO() {
		// TODO Auto-generated constructor stub
	}

	public CompanyDTO(int companyId, String companyName, List<Client> clientList) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.clientList = clientList;
	}

	public CompanyDTO(int companyId, String companyName) {
		this.companyId = companyId;
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<Client> getClientList() {
		return clientList;
	}

	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	

}
