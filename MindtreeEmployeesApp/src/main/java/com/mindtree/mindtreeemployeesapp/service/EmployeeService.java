package com.mindtree.mindtreeemployeesapp.service;

import java.util.List;

import com.mindtree.mindtreeemployeesapp.dto.EmployeeInputDTO;
import com.mindtree.mindtreeemployeesapp.entity.Employee;

public interface EmployeeService {

	List<Employee> saveEmployees(List<EmployeeInputDTO> employeeInput);

	List<Employee> findEmployeeWithAParticularDesignation();

	long findEmployeeCountAsEnginner();

	Double sumOfEmployee();

}
