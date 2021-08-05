package model.dao;

import java.util.List;

import model.entities.Employee;

public interface EmployeeDao {

	void insert(Employee emp2);
	void update(Employee emp);
	void deleteById(Integer id);
	List<Employee> findAll();
	
}
