package application;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTabPane;
import application.tabs.inventario.CategoryEvent;
import application.tabs.inventario.ImageControl;
import application.tabs.inventario.Inventario;
import application.tabs.pedidos.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public ScrollPane Scroll_Inventario;
    
    @FXML
    public AnchorPane Pane_scroll_Inventario, panelProducto;
    
    @FXML
    public JFXButton cat_Comidas, cat_Refrescos, cat_Helados, btAdd, btEliminar;
    
    @FXML
    public Label lblCategoria, lblSeleccion;
    
    @FXML
    public ImageView imgProducto;
    
    @FXML
    public TextField textNombre, textPrecio;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		srcControl = this;
		
		tabPrincipal.setCache(true);
		tabPrincipal.setCacheHint(CacheHint.SPEED);
		
		tabInventario.setOnSelectionChanged(event -> { 
			
			if(tabInventario.isSelected()) Inventario.eventTab();
			
		});
		
		cat_Comidas.setOnMouseClicked(new CategoryEvent("comida"));
		cat_Refrescos.setOnMouseClicked(new CategoryEvent("refresco"));
		cat_Helados.setOnMouseClicked(new CategoryEvent("helado"));
		
		EnumCategory c = Enum.valueOf(EnumCategory.class, lblCategoria.getText().substring(0, lblCategoria.getText().length()-1));	
		ImageControl imageControl = new ImageControl(c);
		
		imgProducto.setOnMouseClicked(event-> {
			
			try {
				
				imageControl.setRequestFile();
				
				Image img = imageControl.getImagen();
				
				if( img == null) {
					
					if(btAdd.getText().equals("AÑADIR")) imgProducto.setImage(new Image("noImage.png"));
					
				} else	imgProducto.setImage(img);
				
			}catch(FileNotFoundException e) {
				
				System.err.println("IMAGEN NO ENCONTRADA");
				
			}
			
		});
		
		btAdd.setOnMouseClicked(event->{
			
			Producto producto = new Producto(lblCategoria.getText().substring(0, lblCategoria.getText().length()-1),
											 textNombre.getText().toUpperCase(), 
											 Double.parseDouble(textPrecio.getText().replace(",", ".").replace("€", "")), 
											 imageControl.getFile().getName());
			
			if(btAdd.getText().equals("ACTUALIZAR")) {
				
				new CRUD(producto, EnumPHP.UPDATE_PRODUCT); //TODO CAMBIAR ENUM PHP A ACTUALIZAR.PHP
				
			}else {
				
				new CRUD(producto, EnumPHP.INSERT_PRODUCT);
				
			}
			
		});
		
		btEliminar.setOnMouseClicked(event->{
			
			JOptionPane.showMessageDialog(null, "CUIDADO", "ALERTA", JOptionPane.WARNING_MESSAGE);
			
		});
		
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
