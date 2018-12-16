package app_4enRaya;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.Initializable;

/**
 * Clase que contiene y controla todos los componentes de la ventana principal
 * @author Alejandro Pérez Álvarez
 *
 */
public class Controlador implements Initializable{

	public static Controlador src_Control;
	
	@FXML
	public Text lblTitulo;

	@FXML
	public Text lblPuntos1, lblPuntos2;

	@FXML
	private Button btCerrar, btMinimizar;

	@FXML
	private Button btColumna_0, btColumna_1, btColumna_2, btColumna_3, btColumna_4, btColumna_5, btColumna_6;
	public static Button[] btColumnas;
	
	@FXML
	public ImageView ficha_Animacion;

	@FXML
	public Line line_Winner;

	@FXML
	public Canvas panelCanvas;
	
    @FXML
    public Text lblJugador1, lblJugador2;
	
    @FXML
    public JFXButton btMultijugador;
    
    public Stage ventanaMultijugador = new Stage();
    
    private static double X, Y;
    
    public static Partida partida;
    
    public Controlador() {
    	
    	try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("designMultijugador.fxml"));
			Parent root = loader.load();
			
			Scene scene = new Scene(root);
			scene.setFill(Color.TRANSPARENT);
			
			root.setOnMousePressed(new EventHandler<MouseEvent>() {
				
				public void handle(MouseEvent e) {
					
					X = e.getSceneX();
					Y = e.getSceneY();
					
				}
				
			});
			
			root.setOnMouseDragged(new EventHandler<MouseEvent>() {

				public void handle(MouseEvent e) {
					
					ventanaMultijugador.setX(e.getScreenX() - X);
					ventanaMultijugador.setY(e.getScreenY() - Y);
					
				}
				
			});
			
			ventanaMultijugador.initStyle(StageStyle.TRANSPARENT);
			ventanaMultijugador.setScene(scene);
			ventanaMultijugador.show();
			ventanaMultijugador.hide();
			
			} catch (IOException ex) {
				
				System.err.println("NO SE ENCUENTRA EL ARCHIVO FXML DEL MULTIJUGADOR: " + ex.getMessage());	
				
			}
    		
	}
    
	/* 
	 * Inicializa la ventana principal
	 */
	public void initialize(URL location, ResourceBundle resources) {

		src_Control = this;
		
		Button[] btColumna = {btColumna_0, btColumna_1, btColumna_2, btColumna_3, btColumna_4, btColumna_5, btColumna_6};
		btColumnas = btColumna;
		
		btCerrar.setOnAction(event-> System.exit(1));
		btMinimizar.setOnAction(event-> Main.ventanaPrincipal.setIconified(true));
		
		btMultijugador.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {

				Controlador.src_Control.ventanaMultijugador.show();
				
			}
			
		});

		partida = new Partida();
		
		for(int i = 0; i<7; i++) btColumna[i].setOnAction(partida);
		
	}
	
}
