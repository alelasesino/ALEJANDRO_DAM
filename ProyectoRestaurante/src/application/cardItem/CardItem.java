package application.cardItem;

import java.io.IOException;

import application.tabs.pedidos.Producto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;

/**
 * @author Alejandro Pérez Álvarez
 *
 */
public class CardItem {
	
	private Producto producto;
	private Image img;
	private Parent tarjeta;
	
	public CardItem() {
		
		try { tarjeta = FXMLLoader.load(getClass().getResource("/fxml/CardItem.fxml")); } catch (IOException e) {}
		
		//addCardItem();
		//setValuesCard();
		
	}
	
	public CardItem(Producto producto) {
		this();
		
		this.producto = producto;
		
	}
	
	/*private void addCardItem(){
			
		Inventario.addCard(this);

	}*/
	
	private void setValuesCard() {
		
		//ControlCardItem.srcControl.lblProducto.setText(nombre);
		
	}

	public Image getImg() {
		return img;
	}
	
	public Parent getTarjeta() {
		return tarjeta;
	}
	
}
