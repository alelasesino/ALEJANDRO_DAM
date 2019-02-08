package application.cardItem;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;

/**
 * @author Alejandro Pérez Álvarez
 *
 */
public class CardItem {
	
	private String nombre;
	private double precio;
	private Image img;
	private Parent tarjeta;
	
	public CardItem(String nombre) {
		
		this.nombre = nombre.toUpperCase();
		
		try { tarjeta = FXMLLoader.load(getClass().getResource("/fxml/CardItem.fxml")); } catch (IOException e) {}
		
		//addCardItem();
		setValuesCard();
		
	}
	
	/*private void addCardItem(){
			
		Inventario.addCard(this);

	}*/
	
	private void setValuesCard() {
		
		ControlCardItem.srcControl.lblProducto.setText(nombre);
		
	}

	public String getNombre() {
		return nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public Image getImg() {
		return img;
	}
	
	public Parent getTarjeta() {
		return tarjeta;
	}
	
}
