package model.service;

import java.util.List;

import db.DbException;
import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;

public class ProductService {

	private static ProductDao dao = DaoFactory.createProductDao();
	
	public static List<Product> findAll() {
		return dao.findAll();
	}
	
	public static void deleteById(Integer id) {
		try {
			dao.deleteById(id);
		} catch(DbException e) {
			Alerts.showAlert("Erro ao deletar objeto.", null, 
					null, AlertType.ERROR);
		} 
	}
	
	public void save(Product obj) {
		  dao.insert(obj);
	}

	public void update(Product obj) {
		if(obj.getID() != null && obj.getName() != null && obj.getPrice() != null
				&& obj.getType() != null) {
			dao.update(obj);
		} else {
			Alerts.showAlert("Dados Inválidos.", null, "Preencha o formulário corretamente. "
					+ "Os dados informados estão inválidos.", 
					AlertType.ERROR);
		}		
	}
}
