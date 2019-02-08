package application;

import application.tabs.inventario.Categoria;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	public static Scene escena;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/VentanaPrincipal.fxml"));
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					try {
						Categoria.initArrayCards();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}).start();
			
			escena = new Scene(root);
			escena.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.setScene(escena);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
