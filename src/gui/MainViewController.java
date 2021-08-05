package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {
		
	@FXML
	private String imagePizza = "pizza.jpeg";
	@FXML
	private MenuItem menuItemLogin;
	@FXML
	private MenuItem menuItemAutor;
	@FXML 
	private ImageView imageView;
		
	@FXML
	public void onMenuItemAutorAction() {
		loadView("/gui/AboutView.fxml", x -> {});
	}
	@FXML
	public void onMenuItemSistemaAction() {
		loadView("/gui/AccountView.fxml", x -> {});
	}

	protected synchronized<T> void loadView(String aboluteName, Consumer<T> initializeAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(aboluteName));
			VBox newVBox = loader.load();
			Scene mainScene = Main.getSceneMain();
			VBox vBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			Node mainMenu = vBox.getChildren().get(0);
			vBox.getChildren().clear();
			vBox.getChildren().add(mainMenu);
			vBox.getChildren().addAll(newVBox.getChildren());

		    T controller = loader.getController();
		    initializeAction.accept(controller);
		} catch(IOException e) {
			Alerts.showAlert("Erro no Sistema", null, 
					"Olá, desculpe, mas estamos com problemas."
					+ " Em breve solucionaremos.", AlertType.ERROR);
		}
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	}
}
