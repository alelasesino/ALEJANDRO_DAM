package application.cardItem;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import application.ControlVentanaPrincipal;
import application.tabs.pedidos.Producto;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ControlCardItem implements Initializable, EventHandler<Event> {

	public static ControlCardItem srcControl;

	public static int idProductSelected;
	
	public Producto producto;
	
	@FXML
	public Label lblPrecio, lblProducto;

	@FXML
	public ImageView imgProducto;

	@FXML
	private JFXButton btProducto;

	@FXML
	public AnchorPane panePrecio;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		srcControl = this;

		btProducto.setOnMouseClicked(this);

	}

	@Override
	public void handle(Event e) {
		
		idProductSelected = producto.getIdProducto();
		
		ControlVentanaPrincipal.srcControl.imageControl.setImgName(producto.getImgName());
		
		if (ControlVentanaPrincipal.srcControl.lblSeleccion.isVisible())
			setHeaderVisible(true);
		
		ControlVentanaPrincipal.srcControl.imgProducto.setImage(imgProducto.getImage());
		
		if(!lblProducto.getText().equals("NUEVO")) {
			
			ControlVentanaPrincipal.srcControl.textNombre.setText(lblProducto.getText());
			ControlVentanaPrincipal.srcControl.textPrecio.setText(lblPrecio.getText());
			
			ControlVentanaPrincipal.srcControl.btAdd.setText("ACTUALIZAR");
			ControlVentanaPrincipal.srcControl.btEliminar.setVisible(true);
			ControlVentanaPrincipal.srcControl.btContainer.setTranslateY(-2);
			
		} else {
			
			ControlVentanaPrincipal.srcControl.textNombre.setText("");
			ControlVentanaPrincipal.srcControl.textNombre.setPromptText("NUEVO PRODUCTO");
			
			ControlVentanaPrincipal.srcControl.textPrecio.setText("");
			ControlVentanaPrincipal.srcControl.textPrecio.setPromptText("0,00 €");
			
			ControlVentanaPrincipal.srcControl.btAdd.setText("AÑADIR");
			ControlVentanaPrincipal.srcControl.btEliminar.setVisible(false);
			ControlVentanaPrincipal.srcControl.btContainer.setTranslateY(16);
			
		}
		
	}

	/**
	 * Muestra el header de producto no seleccionado o el producto seleccionado
	 * @param visible
	 */
	public static void setHeaderVisible(boolean visible) {

		ControlVentanaPrincipal.srcControl.lblSeleccion.setVisible(!visible);
		ControlVentanaPrincipal.srcControl.panelProducto.setVisible(visible);
		ControlVentanaPrincipal.srcControl.imgProducto.setVisible(visible);
		ControlVentanaPrincipal.srcControl.lblCategoria.setVisible(visible);
		ControlVentanaPrincipal.srcControl.textNombre.setVisible(visible);
		ControlVentanaPrincipal.srcControl.textPrecio.setVisible(visible);
		ControlVentanaPrincipal.srcControl.btAdd.setVisible(visible);
		ControlVentanaPrincipal.srcControl.btEliminar.setVisible(visible);

	}

}
