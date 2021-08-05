package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.ProductDao;
import model.entities.Product;

public class ProductDaoJDBC implements ProductDao {

	private Connection conn;

	public ProductDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void update(Product obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE products SET name = ?"
					+ ", price = ?, type = ? WHERE ID = ?;");
			st.setString(1, obj.getName());
			st.setDouble(2, obj.getPrice());
			st.setString(3, obj.getType());
			st.setInt(4, obj.getID());
			st.execute();
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public void insert(Product obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO products (name, price, "
					+ "type) VALUES (?, ?, ?);");
			st.setString(1, obj.getName());
			st.setDouble(2, obj.getPrice());
			st.setString(3, obj.getType());
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
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
		st = conn.prepareStatement("DELETE FROM products WHERE ID = ?;");
		st.setInt(1, id);
		st.execute();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public Product findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM products;");
			rs = st.executeQuery();
			List<Product> list = new ArrayList<>();
			while(rs.next()) {
				Product product = instantiateProduct(rs);
				list.add(product);
			}
			return list;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Product instantiateProduct(ResultSet rs) throws SQLException {
		Product p = new Product();
		p.setID(rs.getInt("ID"));
		p.setName(rs.getString("name"));
		p.setPrice(rs.getDouble("price"));
		p.setType(rs.getString("type"));
		return p;
	}

}
