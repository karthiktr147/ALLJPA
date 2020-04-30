package com.mindtree.companyapp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.mindtree.companyapp.entity.Company;

public class ClientDTO {

	private int clientId;

	@NotBlank(message = "Company Name Can Not be Empty")
	private String clientName;

	@Min(value = 1000, message = "Client Budget Should not be less than 1000 ")
	private double clientBudget;

	public Company company;

	public ClientDTO() {
		// TODO Auto-generated constructor stub
	}

	public ClientDTO(int clientId, String clientName, double clientBudget, Company company) {
		this.clientId = clientId;
		this.clientName = clientName;
		this.clientBudget = clientBudget;
		this.company = company;
	}

	public ClientDTO(int clientId, String clientName, double clientBudget) {
		this.clientId = clientId;
		this.clientName = clientName;
		this.clientBudget = clientBudget;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public double getClientBudget() {
		return clientBudget;
	}

	public void setClientBudget(double clientBudget) {
		this.clientBudget = clientBudget;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

}
