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
import model.entities.Account;
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
			st = conn.prepareStatement("INSERT INTO employees (name, email, "
					+ "password, salary, office) VALUES (?, ?, ?, ?, ?);");
			st.setString(1, obj.getName());
			st.setString(2, obj.getAccount().getEmail());
			st.setString(3, obj.getAccount().getPassword());
			st.setDouble(4, obj.getSalary());
			st.setString(5, obj.getCargo());
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
	public Employee findByAccount(String email, String password) {
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM employees WHERE email = ? AND password = ?;");
			st.setString(1, email);
			st.setString(2, password);
			rs = st.executeQuery();
			if(rs.next()) {
				Account account = instantiateAccount(rs);
				Employee emp = instantiateEmployee(rs);
				emp.setAccount(account);
				return emp;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
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
				Account account = instantiateAccount(rs);
				emp.setAccount(account);
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

	private Account instantiateAccount(ResultSet rs) throws SQLException {
		Account account = new Account();
		account.setEmail(rs.getString("email"));
		account.setPassword(rs.getString("password"));
		return account;
	}
}
