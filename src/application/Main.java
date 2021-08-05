package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Scene sceneMain;
	
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			stage.setTitle("Pizzaria - Matheus Dalvino");
			ScrollPane scrollPane = loader.load();
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
		    sceneMain = new Scene(scrollPane);
			stage.setScene(sceneMain);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Scene getSceneMain() {
		return sceneMain;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
