package com.system.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.models.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long>{

}
