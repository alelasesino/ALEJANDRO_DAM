package app_4enRaya;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * Clase que contiene y controla todos los componentes de la ventana del multijugador
 * @author Alejandro Pérez Álvarez
 *
 */
public class ControlMultijugador implements Initializable{
	
	public static ControlMultijugador src_Control;
	
    @FXML
    public Button btCerrarMulti;

    @FXML
    public JFXTextField txtIPDestino;
    
    @FXML
    public JFXTextField txtOutput;
    
    @FXML
    public JFXTextField txtInput;
    
    @FXML
    public Text txtIP;
    
    @FXML
    public JFXButton btConectarMulti;
    
    @FXML
    public JFXToggleButton btColorFicha;
    
    public static boolean multijugador;
	
	/**
	 * Inicializa la ventana del multijugador
	 */
	public void initialize(URL location, ResourceBundle resources) {
		
		src_Control = this;
		
		btCerrarMulti.setOnAction(event->Controlador.src_Control.ventanaMultijugador.hide());

		try { txtIP.setText("Su IP es " + InetAddress.getLocalHost().getHostAddress()); } catch (UnknownHostException e) {}

		btColorFicha.setOnAction(event-> Controlador.partida.setTurno_Juego(btColorFicha.isSelected()));
		
		btConectarMulti.setOnAction(event->{

			multijugador = true;
			
			new Thread(new Runnable() {
	
				ServerSocket server;
				
				public void run() {
					
					try {
						
						server = new ServerSocket(Integer.parseInt(ControlMultijugador.src_Control.txtInput.getText()));
						
						while(true) {
							
							Socket socket = server.accept();
							
							DataInputStream flujo_entrada = new DataInputStream(socket.getInputStream());
							Controlador.partida.establecer_ficha_tablero((byte)flujo_entrada.readInt(), !Controlador.partida.getTurno_Juego());
							
							try {Thread.sleep(930);} catch (InterruptedException ex) {}
							
							Tablero.setDisableBtColumnas(false);
							
						}
						
					} catch (NumberFormatException | IOException e) {}
					
				}
			}).start();
			
			Controlador.src_Control.ventanaMultijugador.close();
			
		});
		
	}

}
