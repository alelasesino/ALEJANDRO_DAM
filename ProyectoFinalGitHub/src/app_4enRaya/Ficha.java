package app_4enRaya;

import java.io.File;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Clase que coloca y realiza la animacion de las fichas del tablero
 * @author Alejandro Pérez Álvarez
 *
 */
public class Ficha {

    private final Image FICHA_AMARILLA_ESTATICA = new Image(new File("material/ficha_AmarillaF.png").toURI().toString());
    private final Image FICHA_AMARILLA = new Image(new File("material/ficha_Amarilla.png").toURI().toString());
    
    private final Image FICHA_ROJA_ESTATICA = new Image(new File("material/ficha_RojaF.png").toURI().toString());
    private final Image FICHA_ROJA = new Image(new File("material/ficha_Roja.png").toURI().toString());
	
    public static final int[] ARRAY_EJE_X = {14, 99, 186, 271, 358, 444, 530}; //EJES PARA FICHAS ESTATICAS
    public static final int[] ARRAY_EJE_Y= {8, 95, 181, 269, 355, 442};
    
    private final int[] COORD_EJE_X = {159, 244, 331, 418, 504, 589, 675}; //EJES PARA FICHA ANIMACION
	private final int[] COORD_EJE_Y = {89, 175, 261, 348, 435, 521}; 
    
    public GraphicsContext graphics = Controlador.src_Control.panelCanvas.getGraphicsContext2D();
    
    private FadeTransition fade = new FadeTransition(Duration.millis(250), Controlador.src_Control.ficha_Animacion);//200
    private TranslateTransition animacion;
    
    private int X, Y;
	
	/**
	 * Crea una ficha con animacion
	 * @param X - Coordenada horizontal del tablero
	 * @param Y - Coordenada vertical del tablero
	 * @param turno - Turno del jugador, true(amarillo), false(rojo)
	 */
	public void setFicha(int X, int Y, boolean turno) {
		
		this.X = X;
		this.Y = Y;

		if(turno) {
			
			animacionFicha(FICHA_AMARILLA, FICHA_AMARILLA_ESTATICA);
			
		} else {
			
			animacionFicha(FICHA_ROJA, FICHA_ROJA_ESTATICA);
			
		}
		
	}
	
	/**
	 * Elimina todas las fichas del tablero con una animacion
	 */
	public void clearFichas() {

		Partida.animacion_Fade(Controlador.src_Control.panelCanvas, 1000, 2, 1, 0);
		graphics.clearRect(0, 0, 617, 522);
		
	}
	
	
	/**
	 * Crea la animacion de la ficha cayendo
	 * @param imagenAnimacion - Imagen de la ficha de la animacion
	 * @param imagenEstatica - Imagen de la ficha estatica del tablero
	 */
	private void animacionFicha(Image imagenAnimacion, Image imagenEstatica) {

		Controlador.src_Control.ficha_Animacion.setImage(imagenAnimacion);
		Controlador.src_Control.ficha_Animacion.setTranslateX(COORD_EJE_X[X]);
		Controlador.src_Control.ficha_Animacion.setTranslateY(0);
		
		Tablero.setDisableBtColumnas(true);
		
		new Thread(new Runnable() { //HILO EN SEGUNDO PLANO

			public void run() {
				
				fade.setFromValue(0);
				fade.setToValue(1);
				fade.play();
				
				try {Thread.sleep(80);} catch (InterruptedException ex) {} //ESPERA A QUE TERMINE LA ANIMACION
				
				Controlador.src_Control.ficha_Animacion.setVisible(true);
				
				animacion = new TranslateTransition(Duration.millis(700), Controlador.src_Control.ficha_Animacion); 
				animacion.setToY(COORD_EJE_Y[Y]);
				animacion.play();
				
				try {Thread.sleep(710);} catch (InterruptedException ex) {} //ESPERA A QUE TERMINE LA ANIMACION

				Controlador.src_Control.ficha_Animacion.setVisible(false); //DESAPARECE LA FICHA DE LA ANIMACION
				
				setImageFichaEstatica(imagenEstatica);
				
			}
		}).start();
		
	}

	/**
	 * Dibuja la ficha estatica en el tablero
	 * @param img - Imagen de la ficha estatica en el tablero
	 */
	private void setImageFichaEstatica(Image img) {
		
		graphics.drawImage(img, ARRAY_EJE_X[X], ARRAY_EJE_Y[Y]);
		
	}
	
}
