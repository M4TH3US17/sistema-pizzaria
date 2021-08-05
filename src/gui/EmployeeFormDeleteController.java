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
import model.service.EmployeeService;

public class EmployeeFormDeleteController implements Initializable {

	private EmployeeService service;
	private Employee entity;
	
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
			service.deleteById(entity.getId());
			Utils.currentStage(event).close();
		} catch(DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
	}

	@FXML
	public void onBtnCancelAction() {
		System.out.println("Cancel");
	}
	
	private Employee getFormData() {
		Employee emp = new Employee();
		emp.setId(Utils.tryParseToInt(txtId.getText()));
		return emp;
	}

	public void setEmployeeService(EmployeeService service) {
		this.service = service;
	}

	public void setEmployee(Employee entity) {
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
		txtId.setText(String.valueOf(entity.getId()));
	}
}
