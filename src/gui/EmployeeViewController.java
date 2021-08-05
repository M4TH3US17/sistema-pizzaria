package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Employee;
import model.service.EmployeeService;
import model.service.ProductService;

public class EmployeeViewController implements Initializable {

	private EmployeeService emp;
	@FXML
	private TableView<Employee> tblViewEmployee;
	@FXML
	private TableColumn<Employee, Integer> tblId;
	@FXML
	private TableColumn<Employee, String> tblName;
	@FXML
	private TableColumn<Employee, Double> tblSalary;
	@FXML
	private TableColumn<Employee, String> tblCargo;
	@FXML
	private Button btnUpdateSalary;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnClose;
	
	private ObservableList<Employee> obsList;

	public void setEmp(EmployeeService emp) {
		this.emp = emp;
	}
	
	@FXML
	public void onBtnUpdateSalaryAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Employee obj = new Employee();
		createDialogFormUpdate(obj, parentStage,"/gui/EmployeeFormUpdate.fxml");	
	}
	@FXML
	public void onBtnDeleteAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Employee obj = new Employee();
		createDialogFormDelete(obj, parentStage,"/gui/EmployeeFormDelete.fxml");
	}
	
	@FXML
	public void onBtnAddAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Employee obj = new Employee();
		createDialogForm(obj, parentStage, "/gui/EmployeeFormAdd.fxml");	
	}
	
	@FXML
	public void onBtnCloseAction() {
		MainViewController mv = new MainViewController();
		mv.loadView("/gui/ProductView.fxml", (ProductViewController controller) -> {
			controller.setProduct(new ProductService());
			controller.updateTableView();
		});
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNode();
	}

    public void initializeNode() {
		tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tblSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
		tblCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
		Stage stage = (Stage) Main.getSceneMain().getWindow();
		tblViewEmployee.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() {
		if(emp == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Employee> list = emp.findAll();
		obsList = FXCollections.observableArrayList(list);
		tblViewEmployee.setItems(obsList);
	}
	
	private void createDialogForm(Employee obj, Stage parentStage, String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			EmployeeFormAddController controller = loader.getController();
			controller.setEmployee(obj);
			controller.setEmployeeService(new EmployeeService());
			controller.updateFormData();				
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Adicionar Funcionário");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
		} catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", 
					e.getMessage(), AlertType.ERROR);
		}
	}

	private void createDialogFormUpdate(Employee obj, Stage parentStage, String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			EmployeeFormUpdateController controller = loader.getController();
			controller.setEmployee(obj);
			controller.setEmployeeService(new EmployeeService());
			controller.updateFormData();				
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Atualizar Funcionário");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
		} catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view update", 
					e.getMessage(), AlertType.ERROR);
		}
	}
	
	private void createDialogFormDelete(Employee obj, Stage parentStage, String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			EmployeeFormDeleteController controller = loader.getController();
			controller.setEmployee(obj);
			controller.setEmployeeService(new EmployeeService());
			controller.updateFormData();				
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Deletar Funcionário");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
		} catch(IOException e) {
			e.printStackTrace();
			Alerts.showAlert("IO Exception", "Error loading view delete", 
					e.getMessage(), AlertType.ERROR);
		}
	}
}
