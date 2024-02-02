package com.hv.EmploeeManager.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hv.EmploeeManager.exception.UserNotFoundException;
import com.hv.EmploeeManager.model.Employee;
import com.hv.EmploeeManager.repo.EmployeeRepo;

@Service
public class EmployeeService {

	private final EmployeeRepo employeeRepo;

	@Autowired
	public EmployeeService(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	// Create a new Employee
	public Employee addEmployee(Employee employee) {
		employee.setEmployeeCode(UUID.randomUUID().toString());
		return employeeRepo.save(employee);
	}

	// get list of all employees
	public List<Employee> findAllEmployees() {
		return employeeRepo.findAll();
	}

	// get employee by id
	public Employee findEmployeeById(Long id) {
//		employeeRepo.findById(id);
		return employeeRepo.findEmployeeById(id)
				.orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
	}

	// Update Employee
	public Employee updateEmployee(Long id, Employee employee) {
		Employee existingEmployee = employeeRepo.findById(id).orElse(null);

		if (existingEmployee != null) {

			// Update the user object with the new data
			if (employee.getEmail() != null) {
				existingEmployee.setEmail(employee.getEmail());
			}
			if (employee.getName() != null) {
				existingEmployee.setName(employee.getName());
			}
			if (employee.getJobTitle() != null) {
				existingEmployee.setJobTitle(employee.getJobTitle());
			}
			if (employee.getPhone() != null) {
				existingEmployee.setPhone(employee.getPhone());
			}
			if (employee.getImageUrl() != null) {
				existingEmployee.setImageUrl(employee.getImageUrl());
			}

			return employeeRepo.save(existingEmployee);
		} else {
			return null;
		}

	}

	// Delete Employee by empId
	public void deleteEmployee(Long id) {
//		employeeRepo.deleteEmployeeById(id);
		employeeRepo.deleteById(id);
	}

}
