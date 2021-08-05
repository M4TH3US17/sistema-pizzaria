package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.service.EmployeeService;
import model.service.ProductService;

public class ManagerViewController implements Initializable {

	@FXML
	private Button btnFuncionarios;
	@FXML
	private Button btnProdutos;
	
	private MainViewController mv = new MainViewController();
	
	@FXML
	public void onBtnFuncionariosAction() {
		mv.loadView("/gui/EmployeeView.fxml", (EmployeeViewController controller) -> {
			controller.setEmp(new EmployeeService());
			controller.updateTableView();
			});
	}
	@FXML
	public void onBtnProdutosAction() {
		mv.loadView("/gui/ProductView.fxml", (ProductViewController controller) -> {
			controller.setProduct(new ProductService());
			controller.updateTableView();});
		}
	
	@Override
	public void initialize(URL uri, ResourceBundle rs) {
	}
}
