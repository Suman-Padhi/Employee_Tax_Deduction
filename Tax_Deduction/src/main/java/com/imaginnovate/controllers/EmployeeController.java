package com.imaginnovate.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.employee.Employee;
import com.imaginnovate.services.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	private final EmployeeService employeeService ;
	
	//Construction injection
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService=employeeService;
	}
	
	 @Operation(summary = " Add employee ", description = " Add a new Employee ")
     @ApiResponse(responseCode = "201", description = "Employee successfully created")
	 
	@PostMapping(value="/addEmployee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee saveEmployee = employeeService.saveEmployee(employee);
		System.out.println(" employee saved "+saveEmployee);
		return new ResponseEntity<Employee>(saveEmployee,HttpStatus.CREATED);
		
	}
}
