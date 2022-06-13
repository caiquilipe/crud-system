package com.system.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.interfaces.ManagerRepository;
import com.system.models.Manager;

@Service
public class ManagerService {
	@Autowired
	ManagerRepository manageRepository;
	
	public List<Manager> AllManagers() {
		List<Manager> managers = new ArrayList<Manager>();
		manageRepository.findAll().forEach(managers::add);
		return managers;
	}
	
	public String GetBonusResponse(Manager manager) {
		return "{\"id\":"+manager.getId()+",\"bonus\":"+manager.calculateBonus()+"}";
	}
	public Optional<Manager> GetOne(long id) {
		return manageRepository.findById(id);
	}
	
	public Manager Save(Manager manager) {
		return manageRepository.save(manager);
	}
	
	public void Delete(long id) {
		manageRepository.deleteById(id);
	}
}
