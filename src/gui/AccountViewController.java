package gui;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.dao.DaoFactory;
import model.dao.EmployeeDao;
import model.entities.Employee;

public class AccountViewController {

	@FXML
	private TextField txtEmail;
	@FXML
	private PasswordField password;
	@FXML
	private Button btnLogin;
	
	private EmployeeDao employeeDao = DaoFactory.createEmployeeDao();

	@FXML
	public void login() {
		try {
			Employee emp = employeeDao.findByAccount(txtEmail.getText(), password.getText());
			ManagerViewController mv = new ManagerViewController();
			if(emp.getCargo().equalsIgnoreCase("gerente")) {
				mv.onBtnFuncionariosAction();
			} else if (emp.getCargo().equalsIgnoreCase("atendente")) {
               
			}
		} catch(NullPointerException e) {
            Alerts.showAlert("Preencha Corretamente", null,
            		"Os campos de e-mail e senha estão incorretos ou vazios. Digite novamente.",
            		AlertType.ERROR);
		}
	}
}

