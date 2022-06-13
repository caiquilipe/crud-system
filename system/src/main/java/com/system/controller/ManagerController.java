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

import com.system.models.Manager;
import com.system.services.ManagerService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ManagerController {	
	@Autowired
	ManagerService managerService;
	
	
	@GetMapping("/managers/{id}/bonus")
	public ResponseEntity<Object> getBonus(@PathVariable("id") long id) {
		Optional<Manager> manager = managerService.GetOne(id);
		if (manager.isPresent()) {
			return new ResponseEntity<>(managerService.GetBonusResponse(manager.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/managers")
	public ResponseEntity<List<Manager>> getAllManagers(){
		List<Manager> managers = managerService.AllManagers();
		return new ResponseEntity<>(managers, HttpStatus.OK);
	}
	
	@GetMapping("/managers/{id}")
	public ResponseEntity<Manager> getOneManager(@PathVariable("id") long id){
		Optional<Manager> manager = managerService.GetOne(id);
		if (manager.isPresent()) {
			return new ResponseEntity<>(manager.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/managers")
	public ResponseEntity<Manager> createManager(@RequestBody Manager manager_json){
		try {
			Manager createdManager = managerService.Save(manager_json);
			return new ResponseEntity<>(createdManager, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/managers/{id}")
	public ResponseEntity<Manager> updateManager(@PathVariable("id") long id, @RequestBody Manager manager_json){
		Optional<Manager> manager = managerService.GetOne(id);
		if (manager.isPresent()) {
			Manager manager_updated = manager.get();
			manager_updated.setName(manager_json.getName());
			manager_updated.setWage(manager_json.getWage());
			manager_updated.setUsername(manager_json.getUsername());
			manager_updated.setPassword(manager_json.getPassword());
			return new ResponseEntity<>(managerService.Save(manager_updated), HttpStatus.OK);
		}	
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/managers/{id}")
	public ResponseEntity<HttpStatus> deleteManagers(@PathVariable("id") long id){
		try {
			managerService.Delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
