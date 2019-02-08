package app_4enRaya;
	
import java.io.File;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


/**
 * Clase principal donde se crea la ventana principal
 * @author Alejandro Pérez Álvarez
 *
 */
public class Main extends Application {

	private static double X, Y;
	
	private boolean desactivarBotones;
	
	public static Stage ventanaPrincipal;
	
	/* 
	 * Comienza la creacion de la ventana principal
	 */
	public void start(Stage primaryStage) {
		
		try {
			
			ventanaPrincipal = primaryStage;
			primaryStage = null;
			
			ventanaPrincipal.getIcons().add(new Image(new File("material/tablero_icono.png").toURI().toString()));

			Parent root = FXMLLoader.load(getClass().getResource("designPrincipal.fxml"));
			ventanaPrincipal.initStyle(StageStyle.TRANSPARENT);
			
			Scene scene = new Scene(root);
			scene.setFill(Color.TRANSPARENT);
			
			ventanaPrincipal.focusedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					
					if(ventanaPrincipal.isFocused() && ControlMultijugador.multijugador && !desactivarBotones) {
						
						Tablero.setDisableBtColumnas(Controlador.partida.getTurno_Juego());
						Controlador.src_Control.btMultijugador.setVisible(false);
						desactivarBotones = true;
						
					}
					
				}
			});
			
			ventanaPrincipal.setScene(scene);
			ventanaPrincipal.show();
			
			root.setOnMousePressed(new EventHandler<MouseEvent>() {
				
				public void handle(MouseEvent e) {
					
					X = e.getSceneX();
					Y = e.getSceneY();
					
				}
				
			});
			
			root.setOnMouseDragged(new EventHandler<MouseEvent>() {

				public void handle(MouseEvent e) {
					
					ventanaPrincipal.setX(e.getScreenX() - X);
					ventanaPrincipal.setY(e.getScreenY() - Y);
					
				}
				
			});
			
		} catch(Exception e) {}
	}
	
	/**
	 * Metodo principal
	 * @param args - Argumentos de arranque
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
