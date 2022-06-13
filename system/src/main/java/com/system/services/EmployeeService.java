package com.system.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.interfaces.EmployeeRepository;
import com.system.models.Employee;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;
	
	public List<Employee> AllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		employeeRepository.findAll().forEach(employees::add);
		return employees;
	}
	
	public String GetBonusResponse(Employee employee) {
		return "{\"id\":"+employee.getId()+",\"bonus\":"+employee.calculateBonus()+"}";
	}
	public Optional<Employee> GetOne(long id) {
		return employeeRepository.findById(id);
	}
	
	public Employee Save(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public void Delete(long id) {
		employeeRepository.deleteById(id);
	}
}
