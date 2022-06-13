package com.system.models;

import javax.persistence.*;

import com.system.interfaces.IEmployee;

@Entity
public class Manager extends Employee implements IEmployee{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	
	public Manager() {
	}
	public Manager(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public double calculateBonus() {
		return this.getWage() * 0.6 + 100;
	}
	
	@Override
	public String toString() {
		return "{id=" + id + " bonus:" + calculateBonus() + "}";
	}
}
