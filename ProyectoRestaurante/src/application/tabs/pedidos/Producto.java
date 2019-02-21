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
	
	private int idProducto;
	private String categoria;
	private String nombre;
	private double precio;
	private String imgName;
	
	public Producto(int idProducto, String categoria, String nombre, double precio, String imgName) {
		
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
	
}
