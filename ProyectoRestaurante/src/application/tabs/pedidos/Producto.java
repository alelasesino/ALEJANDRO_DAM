package application.tabs.pedidos;

import java.text.DecimalFormat;

public class Producto {

	private String categoria;
	private String nombre;
	private double precio;
	private String imgName;
	
	public Producto(String categoria, String nombre, double precio, String imgName) {
		
		this.categoria = categoria;
		this.nombre = nombre;
		this.precio = precio;
		this.imgName = imgName;
		
	}
	
	public String toString() {
		return "Categoria: " + categoria + " Nombre: " + nombre + " " + " Precio:" + precio + " ImgName: " + imgName;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPrecio() {
		return new DecimalFormat("##,##0.00 �").format(precio);
	}

	public String getImgName() {
		return imgName;
	}
	
}
