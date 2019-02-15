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
		
		if (ControlVentanaPrincipal.srcControl.lblSeleccion.isVisible())
			setVisibleAll(true);
		
		ControlVentanaPrincipal.srcControl.imgProducto.setImage(imgProducto.getImage());
		ControlVentanaPrincipal.srcControl.textNombre.setText(lblProducto.getText());
		ControlVentanaPrincipal.srcControl.textPrecio.setText(lblPrecio.getText());

		if (lblProducto.getText().equals("NUEVO")) {
			
			ControlVentanaPrincipal.srcControl.btAdd.setText("AÑADIR");
			ControlVentanaPrincipal.srcControl.btEliminar.setVisible(false);
			ControlVentanaPrincipal.srcControl.btContainer.setTranslateY(16);
			
		} else {
			
			ControlVentanaPrincipal.srcControl.btAdd.setText("ACTUALIZAR");
			ControlVentanaPrincipal.srcControl.btEliminar.setVisible(true);
			ControlVentanaPrincipal.srcControl.btContainer.setTranslateY(-2);
		
		}
		
	}

	public static void setVisibleAll(boolean visible) {

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
