package application.tabs.inventario;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import application.ConexionDB;
import application.EnumCategory;
import application.EnumPHP;
import application.cardItem.CardItem;
import application.tabs.pedidos.Producto;
import javafx.scene.image.Image;

/**
 * @author Alejandro Pérez Álvarez
 *
 */
public class Categoria {
	
	private ArrayList<CardItem> categoryCards = new ArrayList<>();
	
	private String categoria;
	
	public Categoria(EnumCategory categoria) {
		
		
		
		this.categoria = categoria.name();
		
		fillListProductsCategory();
		
	}
	
	/**
	 * Este método añade todas las tarjetas de una categoria al inventario
	 */
	public void addAllCategoryCards() {
		
		for(CardItem c : categoryCards) c.addCardItem();
		
	}
	
	/**
	 * Este método rellena el arrayList de las tajetas de las categorias con los
	 * datos del arrayList de todos los productos
	 */
	private void fillListProductsCategory() {
		
		try {
		
			for(Producto p : arrayProductos) {
				
				if(categoria.equals(p.getCategoria())) {
					
					categoryCards.add(new CardItem(p, getImageProduct(p.getImgName(), categoria)));
					
				} 
				
			}
			
			categoryCards.add(new CardItem(new Producto(-1, "NUEVO", "NUEVO", 0, "addItem.png"), getImageProduct("addItem.png", "")));
		
		} catch (IOException e) {
			System.out.println("NO SE ENCONTRÓ LA IMAGEN DEL PRODUCTO");
		}
		
	}
	
	/**
	 * Este método obtiene una imagen de los productos segun su
	 * categoria y nombre de imagen
	 * @param imgName Nombre de la imagen
	 * @param cat Categoria a la que pertenece la imagen
	 * @return Devuelve un objecto Image con la imagen
	 * @throws IOException En caso de que no se encuentre la imagen
	 */
	public Image getImageProduct(String imgName, String cat) throws IOException {
		
		if(imgName.equalsIgnoreCase("noImage.png")) cat = "";
		
		File f = new File("src" + File.separator + "img" + File.separator + cat.toLowerCase() + File.separator + imgName);
		
		Image i = new Image(f.toURI().toString());
		
		if(i.isError()) 
			return new Image(new File("src" + File.separator + "img" + File.separator + "noImage.png").toURI().toString());
		
		return i;
		
	}
	
	/**
	 * Este método actualiza todos los datos de los productos con la
	 * de la base de datos
	 */
	public void refreshProductos() {
		
		arrayProductos.clear();
		categoryCards.clear();
		
		try { 
			
			initArrayCards(); 
		
		} catch (Exception e) {
			
			String err = "";
			
			if(e instanceof JSONException) err = "SE PRODUJO UN ERROR CON EL ARCHIVO JSON";
			
			if(e instanceof MalformedURLException) err = "ERROR EN EL FORMATO DE LA URL";
			
			if(e instanceof IOException) err = "ERROR EN LA CONEXION CON LA URL";
			
			System.err.println(err);
			
			JOptionPane.showMessageDialog(null, err, "SE PRODUJO UN ERROR", JOptionPane.ERROR_MESSAGE);
			
		}
		
		fillListProductsCategory();
		
	}

}
