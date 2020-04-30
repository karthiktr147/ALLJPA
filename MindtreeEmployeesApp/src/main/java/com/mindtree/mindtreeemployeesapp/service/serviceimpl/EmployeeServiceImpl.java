package com.mindtree.mindtreeemployeesapp.service.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.mindtreeemployeesapp.dto.EmployeeInputDTO;
import com.mindtree.mindtreeemployeesapp.entity.Designation;
import com.mindtree.mindtreeemployeesapp.entity.Employee;
import com.mindtree.mindtreeemployeesapp.repository.EmployeeRepository;
import com.mindtree.mindtreeemployeesapp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> saveEmployees(List<EmployeeInputDTO> employeeInput) {

		return convertEmployeeDtoToEmployee(employeeInput).stream().map(employee -> employeeRepository.save(employee))
				.collect(Collectors.toList());
	}

	private List<Employee> convertEmployeeDtoToEmployee(List<EmployeeInputDTO> employeeInput) {

		return employeeInput.stream().map(employeeDto -> employeeEntityData(employeeDto)).collect(Collectors.toList());
	}

	private Employee employeeEntityData(EmployeeInputDTO employeeDto) {

		Employee employee = new Employee();
		employee.setEmployeeName(employeeDto.getEmployeeName());
		employee.setEmployeeAge(employeeDto.getEmployeeAge());
		employee.setSalary(employeeDto.getSalary());
		employee.setDesignation(findDesignation(employeeDto));

		return employee;
	}

	private Designation findDesignation(EmployeeInputDTO employeeDto) {

		String compentency = employeeDto.getCompetency();

		switch (compentency) {
		case "c1":
			return Designation.ENGINEER;
		case "c2":
			return Designation.SENIOR_ENGINEER;
		case "c3":
			return Designation.MODULE_LEAD;
		case "c4":
			return Designation.MODULE_LEAD;
		case "c5":
			return Designation.ARCHITECT;
		case "c6":
			return Designation.MANAGER;
		default:
			return Designation.ENGINEER;

		}

	}

	@Override
	public Double sumOfEmployee() {

		return employeeRepository.findAll().stream().filter(employee -> employee.getSalary() > 40000)
				.collect(Collectors.toList()).stream().map(e -> e.getSalary()).reduce(0.0, (a, b) -> a + b);
	}

	@Override
	public List<Employee> findEmployeeWithAParticularDesignation() {

		return employeeRepository.findAll().stream().filter(e -> e.getDesignation().equals(Designation.SENIOR_ENGINEER))
				.collect(Collectors.toList());

	}

	@Override
	public long findEmployeeCountAsEnginner() {

		return employeeRepository.findAll().stream().filter(e -> e.getDesignation().equals(Designation.ENGINEER))
				.count();
	}

}
