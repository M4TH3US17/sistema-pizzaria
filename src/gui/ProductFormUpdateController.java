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
import model.entities.Product;
import model.service.ProductService;

public class ProductFormUpdateController implements Initializable {

	private Product entity;
	private ProductService service;
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtProduct;
	@FXML
	private TextField txtPrice;
	@FXML
	private TextField txtType;
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
			service.update(entity);
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
		try {
			p.setID(Utils.tryParseToInt(txtId.getText()));
			p.setName(txtProduct.getText());
			p.setPrice(Double.parseDouble(txtPrice.getText()));
			p.setType(txtType.getText());
		} catch(NumberFormatException e) {

		}
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
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldDouble(txtPrice);
		Constraints.setTextFieldMaxLength(txtType, 40);
		Constraints.setTextFieldMaxLength(txtProduct, 50);
	}
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(txtId.getText());
		txtProduct.setText(entity.getName());
		txtPrice.setText(String.valueOf(entity.getPrice()));
		txtType.setText(entity.getType());
	}
}
