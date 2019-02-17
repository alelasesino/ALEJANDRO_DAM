package application.tabs.inventario;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import application.ConexionDB;
import application.EnumPHP;
import application.cardItem.CardItem;
import application.tabs.pedidos.Producto;
import javafx.scene.image.Image;

/**
 * @author Alejandro P�rez �lvarez
 *
 */
public class Categoria {

	public static ArrayList<Producto> arrayProductos;
	
	private ArrayList<CardItem> categoryCards = new ArrayList<>();
	
	private String categoria;
	
	public Categoria(String categoria) {
		
		if(arrayProductos == null)
			try {
				initArrayCards();
			} catch (Exception e) {
				System.err.println("ERROR EN LA CONEXION CON LA BASE DE DATOS");
			}
		
		this.categoria = categoria.toUpperCase();
		
		fillListProductsCategory();
		
	}
	
	/**
	 * Este m�todo a�ade todas las tarjetas de una categoria al inventario
	 */
	public void addAllCategoryCards() {
		
		for(CardItem c : categoryCards) c.addCardItem();
		
	}
	
	/**
	 * Inicializa el arrayList de tarjetas con los datos de los productos de la base de datos
	 * @throws Exception
	 */
	private static void initArrayCards() throws Exception {
		
		if(arrayProductos == null) arrayProductos = new ArrayList<>();
		
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
	private static void setJSONtoProducto(JSONObject[] jsonObjects) throws Exception {
		
		for(JSONObject j : jsonObjects) {
			
			Producto p = new Producto(j.getInt("ID_PRODUCTO"), j.getString("CATEGORIA").trim(), j.getString("NOMBRE"), j.getDouble("PRECIO"), j.getString("IMAGEN").trim());
			
			arrayProductos.add(p);
			//System.out.println(p.toString());
			
		}
		
	}
	
	/**
	 * Este m�todo rellena el arrayList de las tajetas de las categorias con los
	 * datos del arrayList de todos los productos
	 */
	private void fillListProductsCategory() {
		
		for(Producto p : arrayProductos) {
			
			if(categoria.equals(p.getCategoria())) {
				
				try {
					categoryCards.add(new CardItem(p, getImageProduct(p.getImgName(), categoria)));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} 
			
		}
		
		try {
			categoryCards.add(new CardItem(new Producto(-1, "NUEVO", "NUEVO", 0, "addItem.png"), getImageProduct("addItem.png", "")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Este m�todo obtiene una imagen de los productos segun su
	 * categoria y nombre de imagen
	 * @param imgName Nombre de la imagen
	 * @param cat Categoria a la que pertenece la imagen
	 * @return Devuelve un objecto Image con la imagen
	 * @throws IOException En caso de que no se encuentre la imagen
	 */
	public Image getImageProduct(String imgName, String cat) throws IOException {
		
		if(imgName.equalsIgnoreCase("noImage.png")) cat = "";
		
		//File f = new File("src" + File.separator + "img" + File.separator + cat.toLowerCase() + File.separator + imgName);
		File f = new File("src" + File.separator + "img" + File.separator + cat.toLowerCase() + File.separator + imgName);
		
		Image i = new Image(f.toURI().toString());
		
		if(i.isError()) 
			return new Image(new File("src" + File.separator + "img" + File.separator + "noImage.png").toURI().toString());
		
		return i;
		
	}
	
	/**
	 * Este m�todo actualiza todos los datos de los productos con la
	 * de la base de datos
	 */
	public void refreshProductos() {
		
		arrayProductos.clear();
		categoryCards.clear();
		
		try { initArrayCards(); } catch (Exception e) {}
		
		fillListProductsCategory();
		
	}

}
