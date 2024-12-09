package com.imaginnovate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imaginnovate.employee.Employee;

@Repository
public interface EmployeeDetailsRepo extends JpaRepository<Employee, String> {

}
