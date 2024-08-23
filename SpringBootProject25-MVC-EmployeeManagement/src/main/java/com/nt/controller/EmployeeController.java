package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.entity.Employee;
import com.nt.exception.EmployeeNotFoundException;
import com.nt.service.IEmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private IEmployeeService service;
	
	/*
	 *  1. Show Employee Registration Page
	 *  This method is used to display registration Page
	 *  When user enter /employee/register on browser
	 *  
	 */
	
	@GetMapping("/register")
	public String showRegisterPage(
			Model model,
			@RequestParam(value="message", required=false) String message
			) 
	{
		
		model.addAttribute("message", message);
		return "EmployeeRegister";
	}
	
	
	// 2. On Submit Save Employee Object into DB
	
	@PostMapping("/save")
	public String saveEmployee(
			@ModelAttribute Employee employee,
			RedirectAttributes attributes
			) 
	{
		
		Integer no = service.saveEmployee(employee);
		String msg = new StringBuffer()
				.append("Employee ")
				.append(no)
				.append(" Created").toString();
		
		attributes.addAttribute("message", msg);
		return "redirect:register";
	}
	
	
	/*
	 *  3. Display All rows of Employee
	 
	   @GetMapping("/allEmployees")
	public String findAllEmployees(
			Model model,
			@RequestParam(value="message", required=false) String message
			) 
	{
		List<Employee> list = service.getAllEmployee();
		//System.out.println(message+" From AllEmployees ");
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "EmployeesTable";
	}
	 
	 */
	
	@GetMapping("/allEmployees")
	public String findAllEmployees(
			Model model,
			@PageableDefault(page=0, size=5) Pageable pageable,
			@RequestParam(value="message", required=false) String message
			) 
	{
		Page<Employee> page = service.getAllEmployee(pageable);
		//System.out.println(message+" From AllEmployees ");
		model.addAttribute("list", page.getContent());
		model.addAttribute("page", page);
		model.addAttribute("message", message);
		return "EmployeesTable";
	}
	
	
	
	/*
	 *  4. Delete based on Employee id
	 */
	@GetMapping("/delete")
	public String deleteEmployee(
			@RequestParam Integer id,
			RedirectAttributes attributes
			) 
	{
		String msg = null;
		try {
			service.deleteEmployee(id);	
			msg = "Employee '"+id+"' Deleted";
			
		}catch(EmployeeNotFoundException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		
		attributes.addAttribute("message", msg);
		
		return "redirect:allEmployees";
	}
	
	
	
	// 5. On click Edit show the employee data in Edit Form
	@GetMapping("/edit")
	public String showEdit(
			@RequestParam("id") Integer id,
			RedirectAttributes attributes,
			Model model) {
		String page = null;
		try {
			Employee employee = service.getEmployee(id);
			page = "EmployeeEdit";
			model.addAttribute("employee", employee);
		} catch (EmployeeNotFoundException e) {
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:allEmployees";
			e.printStackTrace();
		}
		return page;
	}
	
	
	
	
	// 6. Update Form Data And Submit 
	@PostMapping("/update")
	public String updateEmployee(
			@ModelAttribute Employee employee,
			Model model,
			RedirectAttributes attributes
			) {
		
		service.updateEmployee(employee);
		
		String message = "Employee '"+employee.getEmpId()+"' Update";
		//System.out.println(message);
		attributes.addAttribute("message", message);
		return "redirect:allEmployees";
	}

}
