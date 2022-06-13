package com.system.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.models.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, Long>{

}
