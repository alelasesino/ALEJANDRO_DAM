package application;

import application.tabs.inventario.Categoria;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	public static Scene escena;
	
	public Categoria c;
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/VentanaPrincipal.fxml"));
						
			new Thread(new Runnable() {
				
				@Override
				public void run() {

						c = new Categoria("comida");
	
				}
			}).start();
			
			escena = new Scene(root);
			escena.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.setScene(escena);
			//primaryStage.setMaximized(true);
			primaryStage.setResizable(false);
			primaryStage.show();
			
			c.addAllCategoryCards();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
