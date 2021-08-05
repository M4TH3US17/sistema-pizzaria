package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.EmployeeDao;
import model.entities.Employee;

public class EmployeeDaoJDBC implements EmployeeDao {

	private Connection conn;
	
	public EmployeeDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Employee obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO employees (name, "
					+ "salary, office) VALUES (?, ?, ?);");
			st.setString(1, obj.getName());
			st.setDouble(2, obj.getSalary());
			st.setString(3, obj.getCargo());
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Employee obj) throws NullPointerException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE employees SET "
					+ " name = ?, salary = ?, "
					+ "office = ? WHERE ID = ?;");
			st.setString(1, obj.getName());
			st.setDouble(2, obj.getSalary());
			st.setString(3, obj.getCargo());
			st.setInt(4, obj.getId());
			st.execute();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM employees WHERE ID = ?;");
			st.setInt(1, id);
			st.execute();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Employee> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM employees;");
			rs = st.executeQuery();
			List<Employee> emp1 = new ArrayList<>();
			while(rs.next()) {
				Employee emp = instantiateEmployee(rs);
				emp1.add(emp);
			}
			return emp1;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Employee instantiateEmployee(ResultSet rs) throws SQLException {
		Employee emp = new Employee();
		emp.setId(rs.getInt("ID"));
		emp.setName(rs.getString("name"));
		emp.setSalary(rs.getDouble("salary"));
		emp.setCargo(rs.getString("office"));
		return emp;
	}
}
