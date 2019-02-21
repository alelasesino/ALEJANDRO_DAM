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
	
	public static ArrayList<Producto> listaProductos;
	
	private ArrayList<CardItem> categoryCards = new ArrayList<>();
	
	private String categoria;
	
	public Categoria(EnumCategory categoria) {
		
		if(listaProductos == null)
			try {
					
				initListaProductos();	
				
			} catch (Exception e) {
				
				String err = "";
				
				if(e instanceof JSONException) err = "SE PRODUJO UN ERROR CON EL ARCHIVO JSON";
				
				if(e instanceof MalformedURLException) err = "ERROR EN EL FORMATO DE LA URL";
				
				if(e instanceof IOException) err = "ERROR EN LA CONEXION CON LA URL";
				
				System.err.println(err);
				
				JOptionPane.showMessageDialog(null, err, "SE PRODUJO UN ERROR", JOptionPane.ERROR_MESSAGE);
				
			}
		
		this.categoria = categoria.name();
		
		fillListProductsCategory();
		
	}
	
	/**
	 * Inicializa el arrayList de productos con los datos de los productos de la base de datos
	 * @throws Exception
	 */
	private static void initListaProductos() throws  Exception  {
		
		if(listaProductos == null) listaProductos = new ArrayList<>();
		
		ConexionDB conexion = new ConexionDB(new URL(ConexionDB.LOCAL_URL + EnumPHP.GET_ALL_PRODUCTS.getPHP()));
		
		JSONArray json_array = new JSONArray(conexion.getJSON().get("productos").toString());
		
		setJSONtoProducto(getArrayObjects(json_array));
		
		conexion.closeConnection();
		
	}
	
	/**
	 * @param ArrayObject JSON con todos los atributos de todos los productos 
	 * @return Devuelve un array JSONObject que contiene los atributos de cada producto
	 * @throws JSONException
	 */
	private static JSONObject[] getArrayObjects(JSONArray json_array) throws JSONException {
		
		JSONObject[] jsonObjects = new JSONObject[json_array.length()];	
		
		for(int i = 0; i<json_array.length(); i++) jsonObjects[i] = new JSONObject(json_array.get(i).toString());
		
		return jsonObjects;
		
	}
	
	/** 
	 * @param Array JSONObject que tiene los atributos de cada producto
	 * @throws JSONException
	 */
	private static void setJSONtoProducto(JSONObject[] jsonObjects) throws JSONException  {
		
		for(JSONObject j : jsonObjects) {
			
			Producto p = new Producto(j.getInt("ID_PRODUCTO"), j.getString("CATEGORIA").trim(), j.getString("NOMBRE"), j.getDouble("PRECIO"), j.getString("IMAGEN").trim());
			
			listaProductos.add(p);
			//System.out.println(p.toString());
			
		}
		
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
		
			for(Producto p : listaProductos) {
				
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
		
		listaProductos.clear();
		categoryCards.clear();
		
		try { 
			
			initListaProductos(); 
		
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
