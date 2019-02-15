package application;

import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import application.tabs.pedidos.Producto;

public class CRUD {
	
	private Producto producto;
	
	public CRUD(Producto producto, EnumPHP enumPHP) {
		
		this.producto = producto;
		
		try {
			
			ConexionDB conexion = new ConexionDB(new URL(ConexionDB.LOCAL_URL + enumPHP.getPHP()));
			conexion.postMethod(createJSON());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
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
