package com.imaginnovate.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.imaginnovate.employee.Employee;
import com.imaginnovate.exceptions.ResourceNotFoundException;
import com.imaginnovate.repositories.EmployeeDetailsRepo;

@Service
public class EmployeeService {

	private final EmployeeDetailsRepo employeeDetailsRepo;

	// Construction Injection
	public EmployeeService(EmployeeDetailsRepo employeeDetailsRepo) {
		this.employeeDetailsRepo = employeeDetailsRepo;
	}

	// Method to save employee
	public Employee saveEmployee(Employee employees) {
		if (employeeDetailsRepo.existsById(employees.getEmployeeId())) {
			throw new IllegalArgumentException(" Employee Id must be different ");
		}
		Employee employeeSaved = employeeDetailsRepo.save(employees);

		return employeeSaved;
	}

	// Method to Calculate tax aount

	public double calculateTaxAmount(double yearlySalary) {
		double tax = 0;
		if (yearlySalary > 1000000)
			tax += (yearlySalary - 1000000) * 0.2;
		if (yearlySalary > 500000)
			tax += Math.min(yearlySalary, 1000000) - 500000 * 0.1;
		if (yearlySalary > 250000)
			tax += Math.min(yearlySalary, 500000) - 250000 * 0.05;
		return tax;
	}

	// calculate tax of each employees
	public Map<String, Object> calculateTax(String employeeId) {
		
		Employee employee = employeeDetailsRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Id doesn't exist"));
System.out.println("Id from param :: "+employeeId+" Employee Id"+employee.getEmployeeId()+" "+"Employee Name "+employee.getFirstName()+" "+employee.getLastName()+" Salary "+employee.getSalary());
		String dojString = employee.getDoj(); // Assuming this returns a String like "yyyy-MM-dd"
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate doj = LocalDate.parse(dojString, formatter);

		// Calculating the months worked from DOJ to the current date
		long monthsWorked = ChronoUnit.MONTHS.between(doj, LocalDate.now());

		// Calculating the annual salary
		double annualSalary = employee.getSalary() * 12 - monthsWorked + 1;
		double tax = calculateTaxAmount(annualSalary);
		double cessAmount = (annualSalary > 2500000) ? 0.02 * (annualSalary - 2500000) : 0;

		Map<String, Object> taxData = new HashMap<>();

		taxData.put("employeeId", employee.getEmployeeId());
		taxData.put("employeeFirstName", employee.getFirstName());
		taxData.put("lastName", employee.getLastName());
		taxData.put("yearlySalary", annualSalary);
		taxData.put("taxAmount", tax);
		taxData.put("cessAmount", cessAmount);

		return taxData;

	}
}
