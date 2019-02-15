package application.tabs.pedidos;

import java.net.URL;
import java.text.DecimalFormat;

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
		return "ID Producto: " + idProducto + "Categoria: " + categoria + " Nombre: " + nombre + " " + " Precio:" + precio + " ImgName: " + imgName;
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
	
	public static void insertProductoDB(Producto producto) throws Exception {
		
		ConexionDB conexion = new ConexionDB(new URL(ConexionDB.LOCAL_URL + EnumPHP.INSERT_PRODUCT.getPHP()));
		
	}
	
}
