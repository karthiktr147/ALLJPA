package com.mindtree.companyapp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CompanyRevenue {

	private int companyId;
	
	@NotBlank(message = "Company Name Can not be empty")
	private String companyName;
	
	@NotNull(message = "Company Revenue Can not Be Null" )
	@Min(value = 1000, message = "Company Revenue should not be less than 1000" )
	private double companyRevenue;
	
	public CompanyRevenue() {
		// TODO Auto-generated constructor stub
	}
	public CompanyRevenue(int companyId, String companyName, double companyRevenue) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyRevenue = companyRevenue;
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
	public double getCompanyRevenue() {
		return companyRevenue;
	}
	public void setCompanyRevenue(double companyRevenue) {
		this.companyRevenue = companyRevenue;
	}

	
}
