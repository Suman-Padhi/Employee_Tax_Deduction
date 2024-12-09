package com.imaginnovate.employee;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Employee {

	@Id
	@Column(unique = true)
	@NotNull(message = " Id is require ")
	private String employeeId;
	
	@NotNull(message = " First name is require ")
	private String firstName;
	
	@NotNull(message = " Last name is require")
    private String lastName;
	
	@NotNull(message= " Please provide your gmail ")
    private String email;
	
	@ElementCollection
	@NotNull(message = " Phone number is require ")
	private List<@Pattern(regexp = "\\d{10}", message = "Invalid phone number format") String> phoneNumbers;
    
    @NotNull(message = "Date of joining is required")
    private String doj;
    
    @Positive(message = "Salary must be positive")
    private Double salary;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", doj=" + doj + ", salary=" + salary + "]";
	}
	
}
