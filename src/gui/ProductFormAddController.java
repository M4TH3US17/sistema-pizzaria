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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.entities.Product;
import model.service.ProductService;

public class ProductFormAddController implements Initializable {

	private Product entity;
	private ProductService service;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtType;
	@FXML
	private TextField txtPrice;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;
	
	@FXML
	public void onBtnSaveAction(ActionEvent event) {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();
			service.save(entity);
			Utils.currentStage(event).close();
		} catch(DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
	}

	@FXML
	public void onBtnCancelAction() {
	}

	private Product getFormData() {
		Product p = new Product();
		p.setName(txtName.getText());
		p.setPrice(Double.parseDouble(txtPrice.getText()));
		p.setType(txtType.getText());
		return p;
	}

	public void setProduct(Product entity) {
		this.entity = entity;
	}
	public void setProductService(ProductService service) {
		this.service = service;
	}
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldMaxLength(txtType, 60);
		Constraints.setTextFieldMaxLength(txtName, 60);
		Constraints.setTextFieldDouble(txtPrice);
	}
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtName.setText(entity.getName());
		txtPrice.setText(String.valueOf(entity.getPrice()));
		txtType.setText(entity.getType());
	}
}