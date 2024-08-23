package com.nt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nt.entity.Employee;
import com.nt.exception.EmployeeNotFoundException;

public interface IEmployeeService {
	Integer saveEmployee(Employee emp);
	
	void updateEmployee(Employee emp);
	
	void deleteEmployee(Integer id) throws EmployeeNotFoundException;
	
	Employee getEmployee(Integer id) throws EmployeeNotFoundException; 
	
	List<Employee> getAllEmployee();
	
	Page<Employee> getAllEmployee(Pageable p);

}
