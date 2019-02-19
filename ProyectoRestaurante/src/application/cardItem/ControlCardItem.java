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
	
	public static ControlVentanaPrincipal control = ControlVentanaPrincipal.srcControl;

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
		
		control.imageControl.setImgName(producto.getImgName());
		
		if (control.lblSeleccion.isVisible())
			setHeaderVisible(true);
		
		control.imgProducto.setImage(imgProducto.getImage());
		
		setValuesHeader();
		
	}

	private void setValuesHeader() {
		
		if(!lblProducto.getText().equals("NUEVO")) {
			
			control.textNombre.setText(lblProducto.getText());
			control.textPrecio.setText(lblPrecio.getText());
			
			control.btAdd.setText("ACTUALIZAR");
			control.btEliminar.setVisible(true);
			control.btContainer.setTranslateY(-2);
			
		} else {
			
			control.textNombre.setText("");
			control.textNombre.setPromptText("NUEVO PRODUCTO");
			
			control.textPrecio.setText("");
			control.textPrecio.setPromptText("0,00 €");
			
			control.btAdd.setText("AÑADIR");
			control.btEliminar.setVisible(false);
			control.btContainer.setTranslateY(16);
			
		}
		
	}

	/**
	 * Muestra el header de producto no seleccionado o el producto seleccionado
	 * @param visible
	 */
	public static void setHeaderVisible(boolean visible) {
		
		control.lblSeleccion.setVisible(!visible);
		control.panelProducto.setVisible(visible);
		control.imgProducto.setVisible(visible);
		control.lblCategoria.setVisible(visible);
		control.textNombre.setVisible(visible);
		control.textPrecio.setVisible(visible);
		control.btAdd.setVisible(visible);
		control.btEliminar.setVisible(visible);

	}

}
