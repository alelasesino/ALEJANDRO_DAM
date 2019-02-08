package application;

import application.cardItem.CardItem;
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
			
			CardItem asd =  new CardItem("asd");
			CardItem aasasd =new CardItem("asd");
			CardItem assasd =new CardItem("asd");
			CardItem asasd =new CardItem("asd");
			CardItem assd =new CardItem("asd");
			new CardItem("asd");
			new CardItem("asd");
			new CardItem("asd");
			
			//System.gc();
			
			
			
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
