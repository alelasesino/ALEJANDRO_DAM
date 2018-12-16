package app_4enRaya;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Clase que controla todo lo relacionado con la partida
 * @author Alejandro Pérez Álvarez
 *
 */
public class Partida implements EventHandler<ActionEvent>{
	
	private Tablero tablero = new Tablero();
	private Ficha ficha = new Ficha();
	
	private boolean turno_Juego = false;
	
	/**
	 * Establece el turno del juego
	 * @param turno - Turno ha establecer, true(amarillo), false(rojo)
	 */
	public void setTurno_Juego(boolean turno) {
		turno_Juego = turno;
	}
	
	/**
	 * Obtiene el turno de juego actual
	 * @return - Devuelve el turno actual, true(amarillo), false(rojo)
	 */
	public boolean getTurno_Juego() {
		return turno_Juego;
	}
	
	/* 
	 * Evento al hacer click a los botones de las columnas del tablero
	 */
	public void handle(ActionEvent e) {
		
		byte numColumna = Byte.parseByte(String.valueOf(e.getSource()).substring(20, 21));

		establecer_ficha_tablero(numColumna, turno_Juego);
		
		if(ControlMultijugador.multijugador) {
			
			try {
				
				Socket socket = new Socket(ControlMultijugador.src_Control.txtIPDestino.getText(), Integer.parseInt(ControlMultijugador.src_Control.txtOutput.getText()));
				
				DataOutputStream flujo_salida = new DataOutputStream(socket.getOutputStream());
				flujo_salida.writeInt(numColumna);
				flujo_salida.close();
				socket.close();
				
			} catch (NumberFormatException | IOException e1) {
			
				System.out.println("ERROR AL ENVIAR EL PAQUETE: " + e1.getMessage());
				
			}
			
		} else {
			
			if(Controlador.src_Control.btMultijugador.isVisible()) {
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						
						Partida.animacion_Fade(Controlador.src_Control.btMultijugador, 500, 1, 1, 0);
						Controlador.src_Control.btMultijugador.setVisible(false);
						
					}
				}).start();
				
			}
			
		}
		
	}

	/**
	 * Manda a generar la ficha en el tablero
	 * @param numColumna - Numero de la columna que se hizo click
	 * @param turno - Turno del jugador que hizo click
	 */
	public void establecer_ficha_tablero(byte numColumna, boolean turno) {
		
		tablero.setTurno(turno);
		tablero.setColumnaPulsada(numColumna);
		tablero.setPosicionComprobar(numColumna, tablero.getArrayColumnas(numColumna));
		
		setLblUnderline(turno);
		
		ficha.setFicha(numColumna, tablero.getArrayColumnas(numColumna), turno);
		
		if(!ControlMultijugador.multijugador) turno_Juego = !turno_Juego;
		
		checkEndGame();
		
	}

	/**
	 * Subraya las etiquetas de los jugador segun el turno
	 * @param turno - True(amarillo), False(Rojo)
	 */
	public void setLblUnderline(boolean turno) {
		
		Controlador.src_Control.lblJugador1.setUnderline(turno);
		Controlador.src_Control.lblJugador2.setUnderline(!turno);
		
	}
	
	/**
	 * Comprueba si el juego termina por empate o por victoria
	 */
	public void checkEndGame() {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {Thread.sleep(930);} catch (InterruptedException ex) {} //ESPERA A QUE TERMINE LA ANIMACION
				
				tablero.checkWin();
				
				if(!ControlMultijugador.multijugador) Tablero.setDisableBtColumnas(false);
				
				boolean isEmpate = tablero.isEmpate();
				
				if(tablero.isWin() || isEmpate) {
					
					Tablero.setDisableBtColumnas(true);
					
					if(isEmpate) tablero.setTextWithFade(Controlador.src_Control.lblTitulo, "Han quedado Empate"); 
					
					tablero.reiniciar_Juego();
					
					if(!ControlMultijugador.multijugador) turno_Juego = !turno_Juego;
					
					try {Thread.sleep(2200);} catch (InterruptedException ex) {}
					
					ficha.clearFichas();
					
				}
				
			}
			
		}).start();
		
	}
	
	/**
	 * Método que genera una animacion de desvanecer 
	 * @param nodo - Objecto ha realizar la animacion
	 * @param tiempo - Tiempo en milisegundos de la animacion
	 * @param num_Repeticiones - Numero de repeticiones de la animacion
	 * @param valueFrom - Valor de inicio de la animacion
	 * @param valueTo - Valor final de la animacion
	 */
	public static void animacion_Fade(Node nodo, int tiempo, int num_Repeticiones, int valueFrom, int valueTo) {
		
		FadeTransition fade = new FadeTransition(Duration.millis(tiempo), nodo);
		fade.setFromValue(valueFrom);
		fade.setToValue(valueTo);
		fade.setAutoReverse(true);
		fade.setCycleCount(num_Repeticiones);
		fade.play();
		
		try {Thread.sleep(tiempo);} catch (InterruptedException e) {}
		
	}
	
}
