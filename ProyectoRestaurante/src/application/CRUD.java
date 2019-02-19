package application;

import java.io.IOException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import application.tabs.pedidos.Producto;

/**
 * Esta clase se encarga de insertar, actualizar y eliminar productos de la base de datos
 * @author Alejandro Pérez Álvarez
 *
 */
public class CRUD {
	
	private Producto producto;
	
	public CRUD(Producto producto, EnumPHP enumPHP) {
		
		this.producto = producto;
		
		try {
			
			ConexionDB conexion = new ConexionDB(new URL(ConexionDB.LOCAL_URL + enumPHP.getPHP()));
			conexion.postMethod(createJSON());
			
		}catch (Exception e) {
			
			if(e instanceof IOException) 
				System.err.println("ERROR EN LA CONEXIÓN CON LA URL"); 
			else 
				System.out.println("ERROR EN EL MÉTODO POST");
			
		}
		
	}
	
	/**
	 * Crea el JSON con toda la informacion de la operacion al servidor
	 * @return
	 * @throws JSONException
	 */
	private JSONObject createJSON() throws JSONException {
		
		JSONObject json = new JSONObject();
		
		json.put("id_producto", producto.getIdProducto());
		json.put("categoria", producto.getCategoria());
		json.put("nombre", producto.getNombre());
		json.put("precio", producto.getPrecioDouble());
		json.put("imagen", producto.getImgName());
		
		return json;
		
	}
	
}
