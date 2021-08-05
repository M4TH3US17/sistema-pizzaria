package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entities.Employee;
import model.entities.Product;
import model.service.EmployeeService;
import model.service.ProductService;

public class ProductFormDeleteController implements Initializable {

	private ProductService service;
	private Product entity;
	
	@FXML
	private TextField txtId;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnCancel;
	
	@FXML
	public void onBtnDeleteAction(ActionEvent event) {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();
			service.deleteById(entity.getID());
			Utils.currentStage(event).close();
		} catch(DbException e) {
			Alerts.showAlert("Erro ao deletar objeto", null, e.getMessage(), AlertType.ERROR);
		} catch (NullPointerException e) {
			Alerts.showAlert("Erro: campo nulo.", null, 
					"Campo Id não pode ficar vazio.", AlertType.ERROR);
		}
	}

	@FXML
	public void onBtnCancelAction() {
		System.out.println("Cancel");
	}
	
	private Product getFormData() {
		Product p = new Product();
		p.setID(Utils.tryParseToInt(txtId.getText()));
		return p;
	}

	public void setProductService(ProductService service) {
		this.service = service;
	}

	public void setProduct(Product entity) {
		this.entity = entity;
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
	}
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getID()));
	}
	
}
