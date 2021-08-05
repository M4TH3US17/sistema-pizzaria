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

public class EmployeeFormUpdateController implements Initializable {

	private EmployeeService service;
	private Employee entity;
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtOffice;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtSalary;
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
		System.out.println("Cancel");
	}
	
	public void setEmployee(Employee entity) {
		this.entity = entity;
	}
	
	public void setEmployeeService(EmployeeService service) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 60);
		Constraints.setTextFieldMaxLength(txtOffice, 60);
		Constraints.setTextFieldDouble(txtSalary);
	}
	private Employee getFormData() {
		Employee emp = new Employee();
		try {
			emp.setId(Utils.tryParseToInt(txtId.getText()));
			emp.setName(txtName.getText());
			emp.setCargo(txtOffice.getText());
			emp.setSalary(Double.parseDouble(txtSalary.getText()));
		} catch(NumberFormatException e) {
		}
		return emp;

	}

	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
		txtOffice.setText(entity.getCargo());
		txtSalary.setText(String.valueOf(entity.getSalary()));
	}
	
}
