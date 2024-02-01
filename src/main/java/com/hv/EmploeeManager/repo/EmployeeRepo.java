package com.hv.EmploeeManager.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hv.EmploeeManager.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{

}
