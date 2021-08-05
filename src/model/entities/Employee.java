package model.entities;

public class Employee {
	
	private Integer id;
	private String name;
	private String cargo;
	private Double salary;
	
	public Employee() {
	}
	
	public Employee(Integer id) {
		this.id = id;
	}

	public Employee(String name, String cargo, Double salary) {
		this.name = name;
		this.cargo = cargo;
		this.salary = salary;
	}
	
	public Employee(Integer id, String name, String cargo, Double salary) {
		this.id = id;
		this.name = name;
		this.cargo = cargo;
		this.salary = salary;
	}
	
	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
}
