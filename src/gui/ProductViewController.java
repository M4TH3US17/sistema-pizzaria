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
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Employee;
import model.entities.Product;
import model.service.EmployeeService;
import model.service.ProductService;

public class ProductViewController implements Initializable {

	private ProductService product;
	@FXML
	private TableView<Product> tblViewProduct;
	@FXML
	private TableColumn<Product, Integer> tblColumnId;
	@FXML
	private TableColumn<Product, String> tblColumnName;
	@FXML
	private TableColumn<Product, Double> tblColumnPrice;
	@FXML
	private TableColumn<Product, String> tblColumnType;
	@FXML
	private Button btnClose;
	@FXML
	private Button btnUpdatePrice;
	@FXML
	private Button btnNew;
	@FXML
	private Button btnDelete;
	
	private ObservableList<Product> obsList;

	@FXML
	public void onUpdatePriceAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Product obj = new Product();
		createDialogFormUpdate(obj, parentStage,"/gui/ProductFormUpdate.fxml");
	}
	@FXML
	public void onBtnNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Product obj = new Product();
		createDialogForm(obj, parentStage,"/gui/ProductFormAdd.fxml");	
	}
	@FXML
	public void onBtnDeleteAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Product obj = new Product();
		createDialogFormDelete(obj, parentStage,"/gui/ProductFormDelete.fxml");
	}
	@FXML
	public void onBtnCloseAction() {
		MainViewController mv = new MainViewController();
		mv.loadView("/gui/EmployeeView.fxml", (EmployeeViewController controller) -> {
			controller.setEmp(new EmployeeService());
			controller.updateTableView();
		});
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rs) {
		initializeNode();
	}
		
	private void initializeNode() {
		tblColumnId.setCellValueFactory(new PropertyValueFactory<>("ID"));
		tblColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tblColumnPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
		tblColumnType.setCellValueFactory(new PropertyValueFactory<>("Type"));
		Stage stage = (Stage) Main.getSceneMain().getWindow();
		tblViewProduct.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() {
		if(product == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Product> list = product.findAll();
		obsList = FXCollections.observableArrayList(list);
		tblViewProduct.setItems(obsList);
	}
	
	public void setProduct(ProductService product) {
		this.product = product;
	}
	
	protected void createDialogForm(Product obj, Stage parentStage, String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			ProductFormAddController controller = loader.getController();
			controller.setProduct(obj);
			controller.setProductService(new ProductService());
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Adicionar Produto");
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
	
	private void createDialogFormUpdate(Product obj, Stage parentStage, String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			ProductFormUpdateController controller = loader.getController();
			controller.setProduct(obj);
			controller.setProductService(new ProductService());
			controller.updateFormData();				
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Atualizar Produto");
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
	
	private void createDialogFormDelete(Product obj, Stage parentStage, String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			ProductFormDeleteController controller = loader.getController();
			controller.setProduct(obj);
			controller.setProductService(new ProductService());
			controller.updateFormData();				
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Deletar Produto");
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
}
