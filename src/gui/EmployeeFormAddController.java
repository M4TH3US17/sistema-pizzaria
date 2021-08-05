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

public class EmployeeFormAddController implements Initializable {

	private Employee entity;
	private EmployeeService service;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField password;
	@FXML
	private TextField txtSalary;
	@FXML
	private TextField txtOffice;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;
	
	public void setEmployee(Employee entity) {
		this.entity = entity;
	}
	public void setEmployeeService(EmployeeService service) {
		this.service = service;
	}

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
			Alerts.showAlert("Erro ao salvar funcionário", 
					null, e.getMessage(), AlertType.ERROR);
		}
	}

	private Employee getFormData() {
		Employee emp = new Employee();
		emp.setName(txtName.getText());
		emp.getAccount().setEmail(txtEmail.getText());
		emp.getAccount().setPassword(password.getText());
		emp.setCargo(txtOffice.getText());
		emp.setSalary(Double.parseDouble(txtSalary.getText()));
		return emp;
	}
	@FXML
	public void onBtnCancelAction() {
		System.out.println("Cancel");
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldMaxLength(txtEmail, 60);
		Constraints.setTextFieldMaxLength(txtName, 60);
		Constraints.setTextFieldMaxLength(password, 60);
	}
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtName.setText(entity.getName());
		txtEmail.setText(entity.getAccount().getEmail());
		password.setText(entity.getAccount().getPassword());
		txtOffice.setText(entity.getCargo());
		txtSalary.setText(String.valueOf(entity.getSalary()));
	}
}
