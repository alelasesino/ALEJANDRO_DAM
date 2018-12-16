package app_4enRaya;

import javafx.animation.FadeTransition;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Clase que controla todo lo relacionado con el tablero
 * @author Alejandro Pérez Álvarez
 *
 */
public class Tablero {

	private int X, Y;

	private boolean turno;
	private int turno_value; // CONTIENE EL VALOR DEL TURNO CONTRARIO

	private int[][] tablero_Juego = new int[7][6];
	private int[][] coord_Posicion = new int[7][2]; // ARRAY CONTIENE EL EJE X E Y DE EL ULTIMO BOTON DE CADA

	private int[] array_Columnas = new int[7]; // ARRAY CONTIENE EL NÚMERO DE FICHAS DE CADA COLUMNA

	private boolean isWin;
	
	public Tablero() {

		ini_array_Columnas();

	}

	/**
	 * Inicializa el array de las columnas a 6
	 */
	private void ini_array_Columnas() {

		for (int i = 0; i < 7; i++) array_Columnas[i] = 6; // INICIALIZA TODOS LOS ELEMENTOS DEL ARRAY A 6

	}
	
	
	/**
	 * Método de prueba que muestra el tablero en la consola
	 */
	public void showTablero() {

		for (int i = 0; i < 6; i++) {

			for (int j = 0; j < 7; j++) {

				System.out.print(tablero_Juego[j][i] + " ");

			}

			System.out.println();

		}

		System.out.println();

	}

	/**
	 * Resta al array de las columnas 1 en la posicion indicada
	 * @param numColumna - Numero de la columna ha restar
	 */
	public void setColumnaPulsada(byte numColumna) {

		array_Columnas[numColumna] -= 1;

	}

	/**
	 * Método que obtiene el numero de fichas sin poner en una columna
	 * @param numColumna - Numero de la columna del tablero
	 * @return - Devuelve el numero de fichas sin poner en una columna
	 */
	public int getArrayColumnas(byte numColumna) {

		return array_Columnas[numColumna];

	}

	/**
	 * Método que habilita o deshabilita los botones de las columnas del tablero
	 * @param estado - False(Habilita los botones), True(Deshabilita los botones)
	 */
	public static void setDisableBtColumnas(boolean estado) {

		for (int i = 0; i < 7; i++) Controlador.btColumnas[i].setDisable(estado);
		
	}

	/**
	 * Método que visibiliza o invisibiliza todos los botones de las columnas del tablero
	 * @param estado - False(Invisible), True(Visible)
	 */
	public static void setVisibleBtColumnas(boolean estado) {

		for (int i = 0; i < 7; i++) Controlador.btColumnas[i].setVisible(estado);

	}


	/**
	 * Establece la posicion de la ficha que se desea comprobar
	 * @param X - Eje horizontal a comprobar
	 * @param Y - Eje vertical a comprobar
	 */
	public void setPosicionComprobar(int X, int Y) {

		this.X = X;
		this.Y = Y;
		setFicha();
		
		//showTablero();
		
		if(Y == 0)Controlador.btColumnas[X].setVisible(false);

	}

	/**
	 * Establece en el array del tablero la posicion de la ficha
	 */
	private void setFicha() {

		if (turno) {

			tablero_Juego[X][Y] = 2;
			turno_value = 1;

		} else {

			tablero_Juego[X][Y] = 1;
			turno_value = 2;

		}

	}
	
	
	/**
	 * Establece el turno del tablero
	 * @param turno - True(Amarillo), False(Rojo)
	 */
	public void setTurno(boolean turno) {

		this.turno = turno;
		setTurnoValue();

	}

	/**
	 * Establece el valor numero del turno, 1 (Amarillo), 2 (Rojo)
	 */
	private void setTurnoValue() {

		if (turno) turno_value = 1; else turno_value = 2;

	}
	
	
	/**
	 * Método que comprueba si existe victoria
	 */
	public void checkWin() {

		iniCoodPosicion();

		int eje_Horizontal = 1 + comprobarIzquierda() + comprobarDerecha();
		int eje_Vertical = 1 + comprobarAbajo();
		int eje_Diagonal_Derecho = 1 + comprobarArriba_Derecha() + comprobarAbajo_Izquierda();
		int eje_Diagonal_Izquierdo = 1 + comprobarArriba_Izquierda() + comprobarAbajo_Derecha();

		if (eje_Horizontal >= 4) {

			if (coord_Posicion[1][0] == -1) {

				coord_Posicion[1][0] = X;
				coord_Posicion[1][1] = Y;

			} else if (coord_Posicion[2][0] == -1) {

				coord_Posicion[2][0] = X;
				coord_Posicion[2][1] = Y;

			}

			setLineaWin(coord_Posicion[2][0], coord_Posicion[2][1], coord_Posicion[1][0], coord_Posicion[1][1]);

		} else if (eje_Vertical >= 4) {

			setLineaWin(X, Y, coord_Posicion[0][0], coord_Posicion[0][1]);

		} else if (eje_Diagonal_Derecho >= 4) {

			if (coord_Posicion[3][0] == -1) {

				coord_Posicion[3][0] = X;
				coord_Posicion[3][1] = Y;

			} else if (coord_Posicion[6][0] == -1) {

				coord_Posicion[6][0] = X;
				coord_Posicion[6][1] = Y;

			}

			setLineaWin(coord_Posicion[6][0], coord_Posicion[6][1], coord_Posicion[3][0], coord_Posicion[3][1]);

		} else if (eje_Diagonal_Izquierdo >= 4) {

			if (coord_Posicion[4][0] == -1) {

				coord_Posicion[4][0] = X;
				coord_Posicion[4][1] = Y;

			} else if (coord_Posicion[5][0] == -1) {

				coord_Posicion[5][0] = X;
				coord_Posicion[5][1] = Y;

			}

			setLineaWin(coord_Posicion[5][0], coord_Posicion[5][1], coord_Posicion[4][0], coord_Posicion[4][1]);

		}

	}

	/**
	 * Inicializa el array de posicion a -1
	 */
	private void iniCoodPosicion() {

		for (int i = 0; i < 7; i++) {

			for (int j = 0; j < 2; j++) {

				coord_Posicion[i][j] = -1;

			}

		}

	}

	/**
	 * Método que comprueba hacia abajo desde la ficha colocada
	 * @return - Devuelve el numero de fichas consecutivas de un turno
	 */
	private int comprobarAbajo() {

		int contador = 0;

		for (int y = Y + 1; y < 6; y++) {

			if (tablero_Juego[X][y] != 0 && tablero_Juego[X][y] != turno_value) {

				contador++;

				coord_Posicion[0][0] = X;
				coord_Posicion[0][1] = y;

			} else break;

		}

		return contador;

	}

	/**
	 * Método que comprueba hacia la derecha desde la ficha colocada
	 * @return - Devuelve el numero de fichas consecutivas de un turno
	 */
	private int comprobarDerecha() {

		int contador = 0;

		for (int x = X; x < 6; x++) {

			if (tablero_Juego[x + 1][Y] != 0 && tablero_Juego[x + 1][Y] != turno_value) {

				contador++;

				if (contador < 4) {

					coord_Posicion[1][0] = x + 1;
					coord_Posicion[1][1] = Y;

				}

			} else break;

		}

		return contador;

	}

	/**
	 * Método que comprueba hacia la izquierda desde la ficha colocada
	 * @return - Devuelve el numero de fichas consecutivas de un turno
	 */
	private int comprobarIzquierda() {

		int contador = 0;

		for (int x = X; x > 0; x--) {

			if (tablero_Juego[x - 1][Y] != 0 && tablero_Juego[x - 1][Y] != turno_value) {

				contador++;

				if (contador < 4) {

					coord_Posicion[2][0] = x - 1;
					coord_Posicion[2][1] = Y;

				}

			} else break;

		}

		return contador;

	}

	/**
	 * Método que comprueba hacia la diagonal arriba y derecha desde la ficha colocada
	 * @return - Devuelve el numero de fichas consecutivas de un turno
	 */
	private int comprobarArriba_Derecha() {

		int contador = 0;

		for (int x = X, y = Y; y > 0 && x < 6; x++, y--) {

			if (tablero_Juego[x + 1][y - 1] != 0 && tablero_Juego[x + 1][y - 1] != turno_value) {

				contador++;

				if (contador < 4) {

					coord_Posicion[3][0] = x + 1;
					coord_Posicion[3][1] = y - 1;

				}

			} else break;

		}

		return contador;

	}

	/**
	 * Método que comprueba hacia la diagonal arriba y izquierda desde la ficha colocada
	 * @return - Devuelve el numero de fichas consecutivas de un turno
	 */
	private int comprobarArriba_Izquierda() {

		int contador = 0;

		for (int x = X, y = Y; x > 0 && y > 0; x--, y--) {

			if (tablero_Juego[x - 1][y - 1] != 0 && tablero_Juego[x - 1][y - 1] != turno_value) {

				contador++;

				if (contador < 4) {

					coord_Posicion[4][0] = x - 1;
					coord_Posicion[4][1] = y - 1;

				}

			} else break;

		}

		return contador;

	}

	/**
	 * Método que comprueba hacia la diagonal abajo y derecha desde la ficha colocada
	 * @return - Devuelve el numero de fichas consecutivas de un turno
	 */
	private int comprobarAbajo_Derecha() {

		int contador = 0;

		for (int x = X, y = Y; y < 5 && x < 6; x++, y++) {

			if (tablero_Juego[x + 1][y + 1] != 0 && tablero_Juego[x + 1][y + 1] != turno_value) {

				contador++;

				if (contador < 4) {

					coord_Posicion[5][0] = x + 1;
					coord_Posicion[5][1] = y + 1;

				}

			} else break;

		}

		return contador;

	}

	/**
	 * Método que comprueba hacia la diagonal abajo y izquierda desde la ficha colocada
	 * @return - Devuelve el numero de fichas consecutivas de un turno
	 */
	private int comprobarAbajo_Izquierda() {

		int contador = 0;

		for (int x = X, y = Y; y < 5 && x > 0; x--, y++) {

			if (tablero_Juego[x - 1][y + 1] != 0 && tablero_Juego[x - 1][y + 1] != turno_value) {

				contador++;

				if (contador < 4) {

					coord_Posicion[6][0] = x - 1;
					coord_Posicion[6][1] = y + 1;

				}

			} else break;

		}

		return contador;

	}

	
	/**
	 * Método que cambia el texto de una campo texto con una animación de desvanecer
	 * @param txt - Objecto de campo texto
	 * @param texto - Cadena del texto
	 */
	public void setTextWithFade(Text txt, String texto) {
		
		Partida.animacion_Fade(txt, 500, 2, 1, 0);
		txt.setText(texto);
		
	}
	
	/**
	 * Establece la linea de la victoria final entre dos puntos
	 * @param X1 - Eje horizontal punto inicio
	 * @param Y1 - Eje vertical punto inicio
	 * @param X2 - Eje horizontal punto fin
	 * @param Y2 - Eje vertical punto fin
	 */
	private void setLineaWin(int X1, int Y1, int X2, int Y2) {	
		
		isWin = true;
		
		FadeTransition fade = new FadeTransition(Duration.millis(600), Controlador.src_Control.line_Winner);
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.setAutoReverse(true);
		fade.setCycleCount(0);
		fade.play();
		
		Controlador.src_Control.line_Winner.setStartX(Ficha.ARRAY_EJE_X[X1] - 321);
		Controlador.src_Control.line_Winner.setStartY(Ficha.ARRAY_EJE_Y[Y1] - 179);
		
		Controlador.src_Control.line_Winner.setEndX(Ficha.ARRAY_EJE_X[X2] - 321);
		Controlador.src_Control.line_Winner.setEndY(Ficha.ARRAY_EJE_Y[Y2] - 174);
		
		Controlador.src_Control.line_Winner.setVisible(true);
		
		if(turno) {
			
			setTextWithFade(Controlador.src_Control.lblTitulo, "¡Ha ganado el Jugador 2!");
			
			setTextWithFade(Controlador.src_Control.lblPuntos2, String.valueOf(Integer.parseInt(Controlador.src_Control.lblPuntos2.getText()) +1));
			
		} else {
			
			setTextWithFade(Controlador.src_Control.lblTitulo, "¡Ha ganado el Jugador 1!");
			
			setTextWithFade(Controlador.src_Control.lblPuntos1, String.valueOf(Integer.parseInt(Controlador.src_Control.lblPuntos1.getText()) +1));
			
		}
		
	}
	
	
	/**
	 * Obtiene si existe ganador
	 * @return - Devuelve true si existe ganador o falso si no lo existe
	 */
	public boolean isWin() {
		
		return isWin;
		
	}
	
	/**
	 * Obtiene si ha ocurrido un empate
	 * @return - Devuelve true si existe empate o falso si no lo existe
	 */
	public boolean isEmpate() {
		
		int contador = 0;
		
		for(int i = 0; i<7; i++) {
			
			if(array_Columnas[i] == 0) contador++;
			
		}
		
		if(contador == 7) return true; else return false;
		
	}
	
	/**
	 * Método que reinicia el juego
	 */
	public void reiniciar_Juego() {
		
		new Thread(new Runnable() {
			
			public void run() {
				
				try {Thread.sleep(2000);} catch (InterruptedException e) {}		

				isWin = false;
				
				tablero_Juego = new int[7][6];
				
				Partida.animacion_Fade(Controlador.src_Control.lblTitulo, 500, 2, 1, 0);
				Controlador.src_Control.lblTitulo.setText("Cuatro en Raya");
				
				ini_array_Columnas();
				if(!ControlMultijugador.multijugador) {
					
					setDisableBtColumnas(false);
					Controlador.partida.setLblUnderline(!turno);
					
				} else {
					
					setDisableBtColumnas(Controlador.partida.getTurno_Juego());
					Controlador.partida.setLblUnderline(true);
					
				}
				
				setVisibleBtColumnas(true);
				
				Partida.animacion_Fade(Controlador.src_Control.line_Winner, 400, 1, 1, 0);
				Controlador.src_Control.line_Winner.setVisible(false);
				
			}
			
		}).start();
			
	}
	
}
