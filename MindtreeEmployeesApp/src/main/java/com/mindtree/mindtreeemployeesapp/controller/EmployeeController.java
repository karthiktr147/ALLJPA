package com.mindtree.mindtreeemployeesapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.mindtreeemployeesapp.dto.EmployeeInputDTO;
import com.mindtree.mindtreeemployeesapp.dto.ResponseBody;
import com.mindtree.mindtreeemployeesapp.entity.Employee;
import com.mindtree.mindtreeemployeesapp.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employee")
	public ResponseEntity<?> saveEmployeeData(@RequestBody List<EmployeeInputDTO> employeeInput) {

		return new ResponseEntity<ResponseBody<List<Employee>>>(
				new ResponseBody<List<Employee>>(employeeService.saveEmployees(employeeInput), null,
						"Employees Added Sucessfully", true),
				HttpStatus.OK);

	}

	@GetMapping("/employee/sumofsalary")
	public ResponseEntity<?> getSumOfSalary() {
		return new ResponseEntity<ResponseBody<Void>>(new ResponseBody<Void>(null, null,
				"The sum of salary of only those employee having the their salary > 40000 is :=> "
						+ employeeService.sumOfEmployee(),
				true), HttpStatus.OK);
	}

	@GetMapping("/employee/asseniorengineer")
	public ResponseEntity<?> getListEmployee() {
		return new ResponseEntity<ResponseBody<List<Employee>>>(
				new ResponseBody<List<Employee>>(employeeService.findEmployeeWithAParticularDesignation(), null,
						"Empoyee List with designation as 'SENIOR ENGINEER'.", true),
				HttpStatus.OK);
	}

	@GetMapping("/employee/countofengineer")
	public ResponseEntity<?> getEmployeeCountAsEngineer() {
		return new ResponseEntity<ResponseBody<Void>>(
				new ResponseBody<Void>(null, null, "The number of employee with designation as 'ENGINEER' is :=> "
						+ employeeService.findEmployeeCountAsEnginner(), true),
				HttpStatus.OK);
	}

}
