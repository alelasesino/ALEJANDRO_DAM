package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTabPane;
import application.cardItem.ControlCardItem;
import application.tabs.inventario.CategoryEvent;
import application.tabs.inventario.ImageControl;
import application.tabs.inventario.Inventario;
import application.tabs.pedidos.Producto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

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
    
    @FXML
    public VBox btContainer;

    public ImageControl imageControl;
    
    public Producto producto;
    
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
		imageControl = new ImageControl(c);
		
		imgProducto.setOnMouseClicked(event-> btImgProductoEvent());
		
		btAdd.setOnMouseClicked(event-> btAddEvent());
		
		btEliminar.setOnMouseClicked(event-> btRemoveEvent());
		
		textPrecio.setOnKeyPressed(new keyPressed());
		
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

	/**
	 * Método del evento click de la imagen header del producto seleccionado
	 */
	private void btImgProductoEvent() {
		
		try {
			
			imageControl.setRequestFile();
			
			Image img = imageControl.getImagen();
			
			if(img == null) {
				
				if(btAdd.getText().equals("AÑADIR")) imgProducto.setImage(new Image(new FileInputStream(new File("src/img/noImage.png"))));
				
			} else	imgProducto.setImage(img);
			
		}catch(FileNotFoundException e) {
			
			System.err.println("IMAGEN NO ENCONTRADA");
			
		}
		
	}

	/**
	 * Método del evento click del boton eliminar
	 */
	private void btRemoveEvent() {
		
		class BackendThread extends Thread{
			
			public void run() {
				
				new CRUD(new Producto(ControlCardItem.idProductSelected, "", "", 0, ""), EnumPHP.REMOVE_PRODUCT);
				
				imageControl.removeFile(); //TODO COMENTADO POR SEGURIDAD*/
				CategoryEvent.category.refreshProductos();
				
			}
			
		}
		
		BackendThread backend = new BackendThread();
		backend.start();
		
		try { backend.join(); } catch (InterruptedException e) {}
		
		refreshInventario();
		
	}

	/**
	 * Método del evento click del boton añadir/actualizar
	 */
	private void btAddEvent() {
		
		class BackendThread extends Thread{
			
			public void run() {
				
				producto = new Producto(ControlCardItem.idProductSelected, lblCategoria.getText().substring(0, lblCategoria.getText().length()-1),
						 textNombre.getText().toUpperCase(), 
						 Double.parseDouble(textPrecio.getText().replace(",", ".").replace("€", "")), 
						 imageControl.getImgName());
								
				if(btAdd.getText().equals("ACTUALIZAR")) {
					
					new CRUD(producto, EnumPHP.UPDATE_PRODUCT);
					
				} else {
					
					new CRUD(producto, EnumPHP.INSERT_PRODUCT);
					
				}
				
				try { imageControl.copyImgToSource(); } catch (Exception e) {}
				
				CategoryEvent.category.refreshProductos();
				
			}
			
		}
		
		BackendThread backend = new BackendThread();
		backend.start();
		
		try { backend.join(); } catch (InterruptedException e) {}
		
		refreshInventario();
		
	}

	/**
	 * Este método manda a actualizar los productos del inventario
	 */
	private void refreshInventario() {
		
		Inventario.clearInventario();
		CategoryEvent.category.addAllCategoryCards();
		ControlCardItem.setHeaderVisible(false);
		
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

class keyPressed implements EventHandler<KeyEvent> {
	
	@Override
	public void handle(KeyEvent e) {
		
		boolean hasPoint = ControlVentanaPrincipal.srcControl.textPrecio.getText().contains(".");
		
		if(!(e.getCode() == KeyCode.BACK_SPACE)) {
			
			if(!Character.isDigit(e.getText().charAt(0))) {
				
				ControlVentanaPrincipal.srcControl.textPrecio.setText(ControlVentanaPrincipal.srcControl.textPrecio.getText());
				System.out.println(ControlVentanaPrincipal.srcControl.textPrecio.getText());
				e.consume();
				System.out.println(e.isConsumed());
			}
			
		}
		
	}
	
}
