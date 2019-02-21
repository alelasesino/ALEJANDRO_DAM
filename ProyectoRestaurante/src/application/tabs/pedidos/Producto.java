package application.tabs.pedidos;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import application.ConexionDB;
import application.EnumPHP;

public class Producto {

	public static ArrayList<Producto> listaProductos;
	
	private int idProducto;
	private String categoria;
	private String nombre;
	private double precio;
	private String imgName;
	
	public Producto(int idProducto, String categoria, String nombre, double precio, String imgName) {
		
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
		
		this.idProducto = idProducto;
		this.categoria = categoria;
		this.nombre = nombre;
		this.precio = precio;
		this.imgName = imgName;
		
	}
	
	public String toString() {
		return "ID Producto: " + idProducto + " Categoria: " + categoria + " Nombre: " + nombre + " " + " Precio:" + precio + " ImgName: " + imgName;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPrecio() {
		return new DecimalFormat("##,##0.00 €").format(precio);
	}
	
	public double getPrecioDouble() {
		return precio;
	}

	public String getImgName() {
		return imgName;
	}
	
	public int getIdProducto() {
		return idProducto;
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
	 * Este método actualiza todos los datos de los productos con la
	 * de la base de datos
	 */
	public void refreshProductos() {
		
		listaProductos.clear();
		
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
		
	}
	
}
