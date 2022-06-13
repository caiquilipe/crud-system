package com.system.models;

import javax.persistence.*;

import com.system.interfaces.IEmployee;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Employee implements IEmployee{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "wage")
	private double wage;
	
	public Employee() {
	}
	public Employee(String name, double wage) {
		this.name = name;
		this.wage = wage;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getWage() {
		return wage;
	}
	public void setWage(double wage) {
		this.wage = wage;
	}
	
	@Override
	public double calculateBonus() {
		return this.wage * 0.1;
	}
}
