package com.mindtree.companyapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "clientId")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Client_id")
	private int clientId;

	@Column(name = "Client_Name")
	private String clientName;

	@Column(name = "Client_Budget")
	private double clientBudget;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	public Company company;

	public Client() {
		// TODO Auto-generated constructor stub
	}

	public Client(String clientName, double clientBudget, Company company) {
		this.clientName = clientName;
		this.clientBudget = clientBudget;
		this.company = company;
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
