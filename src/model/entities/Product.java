package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private Double price;
	private String type;
	private Integer quantity;
	private Integer ID;

	public Product() {
	}
	
	public Product(String name, Double price, String type) {
		this.name = name;
		this.price = price;
		this.type = type;
	}
	
	public Product(Integer id) {
		this.ID = id;
	}

	public Product(Integer id, String name, Double price, String type) {
		this.name = name;
		this.price = price;
		this.type = type;
		this.ID = id;
	}
	
	public Product(Integer ID, String name, Double price, String type, Integer quantity) {
		this.name = name;
		this.price = price;
		this.type = type;
		this.quantity = quantity;
		this.ID = ID;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(ID, other.ID);
	}
}
