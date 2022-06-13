package com.system.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.models.Employee;
import com.system.services.EmployeeService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employees/{id}/bonus")
	public ResponseEntity<Object> getBonus(@PathVariable("id") long id) {
		Optional<Employee> employee = employeeService.GetOne(id);
		if (employee.isPresent()) {
			return new ResponseEntity<>(employeeService.GetBonusResponse(employee.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		List<Employee> employees = employeeService.AllEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getOneEmployee(@PathVariable("id") long id){
		Optional<Employee> employee = employeeService.GetOne(id);
		if (employee.isPresent()) {
			return new ResponseEntity<>(employee.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee_json){
		try {
			Employee createdEmployee = employeeService.Save(employee_json);
			return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee_json){
		Optional<Employee> employee = employeeService.GetOne(id);
		if (employee.isPresent()) {
			Employee employee_updated = employee.get();
			employee_updated.setName(employee_json.getName());
			employee_updated.setWage(employee_json.getWage());
			return new ResponseEntity<>(employeeService.Save(employee_updated), HttpStatus.OK);
		}	
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id){
		try {
			employeeService.Delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
