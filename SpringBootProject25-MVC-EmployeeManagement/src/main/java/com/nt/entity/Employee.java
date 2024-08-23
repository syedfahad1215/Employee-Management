package com.nt.entity;


import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer empId;
	
	private String empName;
	private String empGender;
	@DateTimeFormat(pattern="dd-mm-yyyy")
	@Temporal(TemporalType.DATE)
	private String empDateOfJoining;
	private Double empSalary;
	private Double empHRA;
	private Double empTA;
	private String empDepartment;
	private String empAddress;

}
