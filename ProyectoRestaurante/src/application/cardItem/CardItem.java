package application.cardItem;

import java.io.IOException;
import application.tabs.inventario.Inventario;
import application.tabs.pedidos.Producto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * @author Alejandro P�rez �lvarez
 *
 */
public class CardItem {
	
    private Label lblPrecio;
    private AnchorPane panePrecio;
    private Label lblProducto;
    private ImageView imgProducto;
	
	private Producto producto;
	private Image img;
	private Parent tarjeta;

	public CardItem(Producto producto, Image img) {

		this.producto = producto;
		this.img = img;
		
		try {tarjeta = FXMLLoader.load(getClass().getResource("/fxml/CardItem.fxml")); } catch (IOException e) {}
		
		ControlCardItem.srcControl.producto = producto;
		
		lblPrecio = ControlCardItem.srcControl.lblPrecio;
		panePrecio = ControlCardItem.srcControl.panePrecio;
		lblProducto = ControlCardItem.srcControl.lblProducto;
		imgProducto = ControlCardItem.srcControl.imgProducto;
		
	}
	
	public void addCardItem(){
	  
		Inventario.addCard(this);
		
		setValuesCard();
		addCardItemVisible();
	 
	}
	 

	public String toString() {
		return "CATEGORIA: " + producto.getCategoria() + " NOMBRE: " + producto.getNombre() + " PRECIO: "
				+ producto.getPrecio() + " IMAGEN: " + producto.getImgName();
	}

	public void setValuesCard() {
		
		lblPrecio.setText(producto.getPrecio());
		lblProducto.setText(producto.getNombre());
		lblProducto.setVisible(!producto.getCategoria().equalsIgnoreCase("refresco"));
		imgProducto.setImage(img);

	}

	private void addCardItemVisible() {
		
		if(isAddCard()){
			
			lblProducto.setVisible(false);
			lblPrecio.setVisible(false);
			panePrecio.setVisible(false);
			
		}
		
	}
	
	private boolean isAddCard() {
		return lblProducto.getText().equals("NUEVO");
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
