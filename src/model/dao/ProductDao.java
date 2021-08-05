package model.dao;

import java.util.List;

import model.entities.Product;

public interface ProductDao {

	void update(Product ob);
	void insert(Product ob);
	void deleteById(Integer id);
	Product findById(Integer id);
	List<Product> findAll();	
	
}
