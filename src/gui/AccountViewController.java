package gui;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AccountViewController {

	@FXML
	private TextField txtEmail;
	@FXML
	private PasswordField password;
	@FXML
	private Button btnLogin;
	
	@FXML
	public void login() {
		try {
			String email = "gerente@gmail.com";
			String senha = "gerente123";
			ManagerViewController mv = new ManagerViewController();
			if(txtEmail.getText().equalsIgnoreCase(email) && 
					password.getText().equalsIgnoreCase(senha)) {
				mv.onBtnFuncionariosAction();
			} else {
				Alerts.showAlert("Usuário inválido",
						null, "Os campos de e-mail e senha estão incorretos"
								+ " ou vazios. Digite novamente."
						, AlertType.ERROR);
			}
		} catch(NullPointerException e) {
		}
	}
}

