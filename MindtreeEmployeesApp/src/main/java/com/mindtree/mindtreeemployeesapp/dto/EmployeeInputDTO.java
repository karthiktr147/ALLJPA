package com.mindtree.mindtreeemployeesapp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class EmployeeInputDTO {

	private int employeeID;

	@NotBlank(message = "Employee Name Can not be Empty")
	private String employeeName;

	@Min(value = 21, message = "Employee Age Must Be Above 21")
	private int employeeAge;

	@NotBlank(message = "Employee competency Can not be Empty")
	private String competency;

	@Min(value = 1000, message = "Employee Salary Must Above 1000")
	@Max(value = 100000, message = "Employee Salary Must Be Below 100000")
	private double salary;

	public EmployeeInputDTO() {
		// TODO Auto-generated constructor stub
	}

	public EmployeeInputDTO(int employeeID, @NotBlank(message = "Employee Name Can not be Empty") String employeeName,
			@Min(value = 21, message = "Employee Age Must Be Above 21") int employeeAge,
			@NotBlank(message = "Employee competency Can not be Empty") String competency,
			@Min(value = 1000, message = "Employee Salary Must Above 1000") @Max(value = 100000, message = "Employee Salary Must Be Below 100000") double salary) {
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.employeeAge = employeeAge;
		this.competency = competency;
		this.salary = salary;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public int getEmployeeAge() {
		return employeeAge;
	}

	public void setEmployeeAge(int employeeAge) {
		this.employeeAge = employeeAge;
	}

	public String getCompetency() {
		return competency;
	}

	public void setCompetency(String competency) {
		this.competency = competency;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
