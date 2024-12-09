package com.imaginnovate.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.services.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/tax-deduction")
public class TaxCalculationController {

	private final EmployeeService employeeService;
	
	@Autowired
	public TaxCalculationController(EmployeeService employeeService) {
		this.employeeService=employeeService;
	}
	
	@Operation(summary = " Get employee ", description = " get tax details of an employee by id ")
    @ApiResponse(responseCode = "200", description = "Employee successfully created")
	
	@GetMapping("/{employeeId}")
	public ResponseEntity<Map<String,Object>> getTaxDetails(@PathVariable String employeeId) {
		System.out.println(" Employee ID is :: "+employeeId);
		Map<String, Object> taxDetails = employeeService.calculateTax(employeeId);
		return new ResponseEntity<Map<String,Object>> (taxDetails,HttpStatus.OK);
		
	}
}
