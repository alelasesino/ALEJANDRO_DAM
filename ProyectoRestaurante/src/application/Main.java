package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	public static Scene escena;
	public static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/VentanaPrincipal.fxml"));
			
			escena = new Scene(root);
			escena.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.setScene(escena);
			//primaryStage.setMaximized(true);
			primaryStage.setResizable(false);
			primaryStage.show();
			
			setPrimaryStage(primaryStage);
			
		} catch(IOException e) {
			System.err.println("NO SE ENCONTRO EL ARCHIVO VentanaPrincipal.fxml");
		}
		
	}
	
	private static void setPrimaryStage(Stage s) {
		primaryStage = s;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
