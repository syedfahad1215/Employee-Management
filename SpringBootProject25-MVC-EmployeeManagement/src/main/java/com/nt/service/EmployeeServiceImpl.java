package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nt.EmployeeUtil;
import com.nt.entity.Employee;
import com.nt.exception.EmployeeNotFoundException;
import com.nt.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private EmployeeUtil util;
	
	
	@Override
	public Integer saveEmployee(Employee emp) 
	{
		util.calculateSalaryBenifits(emp);
	
		
		return repository.save(emp).getEmpId();
	}

	@Override
	public void updateEmployee(Employee emp)
	{
		util.calculateSalaryBenifits(emp);
		repository.save(emp);
	}

	@Override
	public void deleteEmployee(Integer id) throws EmployeeNotFoundException 
	{
		/*
		 * 
		 	Optional<Employee> opt = repository.findById(id);
		 	
		 	if(opt.isPresent()) {
		 		repository.delete(opt.get());
		 	}
		 	else {
		 		throw new EmployeeNotFoundException("Employee '"+id+"' NOt Found!!");
		 	}
		*/
		
		
		/*
		 * 
		repository.delete(repository.findById(id)
				.orElseThrow( () -> new EmployeeNotFoundException("Employee '"+id+"' NOt Found!!"))
				);
				
		*/		
		
		repository.delete(this.getEmployee(id));
		
		
		
	}

	@Override
	public Employee getEmployee(Integer id) throws EmployeeNotFoundException
	{
		return repository.findById(id)
					.orElseThrow(()-> new EmployeeNotFoundException("Employee '"+id+"' NOt Found!!"));
	}

	@Override
	public List<Employee> getAllEmployee() 
	{
		List<Employee> list = repository.findAll();
		
		return list;
	}

	public Page<Employee> getAllEmployee(Pageable pageable) 
	{
		Page<Employee> pages = repository.findAll(pageable);
		
		return pages;
	}

}
