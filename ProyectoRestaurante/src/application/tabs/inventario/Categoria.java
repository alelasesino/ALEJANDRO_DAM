package application.tabs.inventario;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import application.ConexionDB;
import application.EnumPHP;
import application.cardItem.CardItem;
import application.tabs.pedidos.Producto;
import javafx.scene.image.Image;

public class Categoria {

	private String nombre;
	private static ArrayList<CardItem> arrayCards = new ArrayList<>();
	
	public Categoria(String nombre) {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Inicializa el arrayList de tarjetas con los datos de los productos de la base de datos
	 * @throws Exception
	 */
	public static void initArrayCards() throws Exception {
		
		ConexionDB conexion = new ConexionDB(new URL(ConexionDB.LOCAL_URL + EnumPHP.GET_CATEGORIAS.getPHP()));
		
		JSONArray json_array = new JSONArray(conexion.getJSON().get("productos").toString());
		
		setJSONtoProducto(getArrayObjects(json_array));
		
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
	public static void setJSONtoProducto(JSONObject[] jsonObjects) throws Exception {
		
		for(JSONObject j : jsonObjects) {

			Producto p = new Producto(j.getString("CATEGORIA"), j.getString("NOMBRE"), j.getDouble("PRECIO"), j.getString("IMAGEN"));
			
			arrayCards.add(new CardItem(p));
			
		}
		
	}
	
	private String[] getItemName() {
		
		return null;
		
	}
	
	private Image[] getItemImage() {
		
		return null;
		
	}

}
