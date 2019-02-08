package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTabPane;
import application.tabs.inventario.Inventario;
import application.tabs.pedidos.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ControlVentanaPrincipal implements Initializable {

	public static ControlVentanaPrincipal srcControl;

    @FXML
    public GridPane gridInventario;
    
    @FXML
    private JFXTabPane tabPrincipal;
    
    @FXML
    private Tab tabInventario;
    
    @FXML
    public JFXProgressBar item_progressbar;
    
    @FXML
    private JFXButton cat_Refrescos;

    @FXML
    public ScrollPane Scroll_Inventario;
    
    @FXML
    public AnchorPane Pane_scroll_Inventario;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		srcControl = this;
		
		tabPrincipal.setCache(true);
		tabPrincipal.setCacheHint(CacheHint.SPEED);
		
		tabInventario.setOnSelectionChanged(event -> { 
			
			if(tabInventario.isSelected()) Inventario.eventTab();
			
		});
		
		cat_Refrescos.setOnMouseClicked(event -> Inventario.clearInventario());
		
		//cargarItems = new CargarItems();
		
		//item_progressbar.progressProperty().bind(cargarItems.progressProperty());
		
		/*TableColumn<Producto, String> columNombre = new TableColumn<Producto, String>("nombre");
		columNombre.setMinWidth(100);
		columNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

		TableColumn<Producto, Integer> columCantidad = new TableColumn<>("cantidad");
		columCantidad.setMinWidth(100);
		columCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

		TableColumn<Producto, Double> columPrecio = new TableColumn<>("precio");
		columPrecio.setMinWidth(100);
		columPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

		
		tablaPrueba.setItems(getDatosProductos());*/
		
	}

	public ObservableList<Producto> getDatosProductos() {

		ObservableList<Producto> lista = FXCollections.observableArrayList();

		/*lista.add(new Producto("LECHE", 5, 6.42));
		lista.add(new Producto("COCA COLA", 3, 4.56));
		lista.add(new Producto("PIPAS", 10, 1.23));
		lista.add(new Producto("SPRITE", 7, 3.56));
		lista.add(new Producto("AGUA", 2, 7.12));*/

		return lista;

	}
	
}
