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

public class Categoria {

	private static ArrayList<Producto> arrayProductos;
	
	private ArrayList<CardItem> categoryCards = new ArrayList<>();
	
	private String categoria;
	
	public Categoria(String categoria) {
		
		if(arrayProductos == null)
			try {
				initArrayCards();
			} catch (Exception e) {
				System.err.println("ERROR EN LA CONEXION CON LA BASE DE DATOS");
			}
		
		this.categoria = categoria;
		
		fillListProductsCategory();
		
	}
	
	public void addAllCategoryCards() {
		
		for(CardItem c : categoryCards) c.addCardItem();
		
	}
	
	/**
	 * Inicializa el arrayList de tarjetas con los datos de los productos de la base de datos
	 * @throws Exception
	 */
	private static void initArrayCards() throws Exception {
		
		arrayProductos = new ArrayList<>();
		
		ConexionDB conexion = new ConexionDB(new URL(ConexionDB.LOCAL_URL + EnumPHP.GET_ALL_PRODUCTS.getPHP()));
		
		JSONArray json_array = new JSONArray(conexion.getJSON().get("productos").toString());
		
		setJSONtoProducto(getArrayObjects(json_array));
		
		//for(CardItem c : arrayCards) System.out.print(c.toString());
		
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

			Producto p = new Producto(j.getString("CATEGORIA").trim(), j.getString("NOMBRE"), j.getDouble("PRECIO"), j.getString("IMAGEN").trim());
			
			arrayProductos.add(p);
			
		}
		
	}
	
	private void fillListProductsCategory() {
		
		for(Producto p : arrayProductos) {
			
			if(categoria.equals(p.getCategoria())) {
				
				try {
					categoryCards.add(new CardItem(p, getImageProduct(p.getImgName())));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} 
			
		}
		
	}
	
	public Image getImageProduct(String imgName) throws IOException {
		
		File f = new File("src/img/" + categoria.toLowerCase() + "/" + imgName);
		
		return new Image(f.toURI().toString());
		
	}

}
