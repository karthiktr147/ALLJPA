package com.mindtree.companyapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "companyId")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Company_Id")
	private int companyId;

	@Column(name = "Company_Name")
	private String companyName;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	private List<Client> clientList;

	public Company() {
		// TODO Auto-generated constructor stub
	}

	public Company(String companyName, List<Client> clientList) {
		this.companyName = companyName;
		this.clientList = clientList;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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

}
