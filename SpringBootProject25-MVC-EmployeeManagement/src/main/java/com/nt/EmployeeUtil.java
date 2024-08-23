package com.nt;

import org.springframework.stereotype.Component;

import com.nt.entity.Employee;

@Component
public class EmployeeUtil {
	
	public void calculateSalaryBenifits(Employee emp) {
		double sal = emp.getEmpSalary();
		
		emp.setEmpHRA(sal * (12.0/100));
		emp.setEmpTA(sal * (4.0/100));
	}
}
