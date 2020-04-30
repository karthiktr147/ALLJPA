package com.mindtree.mindtreeemployeesapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int employeeID;

	@Column(name = "Name")
	private String employeeName;

	@Column(name = "Age")
	private int employeeAge;

	@Column(name = "Salary")
	private double salary;

	@Column(name = "Designation")
	@Enumerated(EnumType.STRING)
	private Designation designation;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int employeeID, String employeeName, int employeeAge, double salary, Designation designation) {
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.employeeAge = employeeAge;
		this.salary = salary;
		this.designation = designation;
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

}
