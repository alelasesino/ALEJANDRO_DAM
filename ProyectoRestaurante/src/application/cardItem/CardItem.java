package application.cardItem;

import java.io.IOException;
import application.tabs.inventario.Inventario;
import application.tabs.pedidos.Producto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Alejandro Pérez Álvarez
 *
 */
public class CardItem {
	
    private Label lblPrecio;
    private Label lblProducto;
    private ImageView imgProducto;
	
	private Producto producto;
	private Image img;
	private Parent tarjeta;

	public CardItem(Producto producto, Image img) {

		this.producto = producto;
		this.img = img;
		
		try {tarjeta = FXMLLoader.load(getClass().getResource("/fxml/CardItem.fxml")); } catch (IOException e) {}
		
		lblPrecio = ControlCardItem.srcControl.lblPrecio;
		lblProducto = ControlCardItem.srcControl.lblProducto;
		imgProducto = ControlCardItem.srcControl.imgProducto;
		
	}
	
	public void addCardItem(){
	  
		Inventario.addCard(this);
		setValuesCard();
	 
	}
	 

	public String toString() {
		return "CATEGORIA: " + producto.getCategoria() + " NOMBRE: " + producto.getNombre() + " PRECIO: "
				+ producto.getPrecio() + " IMAGEN: " + producto.getImgName();
	}

	public void setValuesCard() {
		
		lblPrecio.setText(producto.getPrecio() + " €");
		if(!producto.getCategoria().equalsIgnoreCase("refresco")) lblProducto.setText(producto.getNombre()); else lblProducto.setVisible(false);
		imgProducto.setImage(img);

	}

	public Producto getProducto() {
		return producto;
	}
	
	public Image getImg() {
		return img;
	}

	public Parent getTarjeta() {
		return tarjeta;
	}

}
