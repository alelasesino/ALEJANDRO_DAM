package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 * 
 * @author Alejandro P�rez �lvarez
 *
 * 07/02/2019  5:43PM
 *
 */
public class ConexionDB {

	public static final String LOCAL_URL = "http://localhost/CursoPHP/"; 
	private HttpURLConnection conexion;
	
	public ConexionDB(URL url) throws IOException {
		
		conexion = (HttpURLConnection) url.openConnection();
		
	}
	
	/**
	 * M�todo que obtene el JSON con los datos devolvidos por el servidor en la consulta
	 * a la base de datos
	 * @return JSONObject con los datos del servidor
	 * @throws Error en la lectura de la conexi�n
	 */
	public JSONObject getJSON() throws Exception {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
	
		return new JSONObject(reader.readLine().toString());
		
	}
	
	/**
	 * M�todo que nos permite pasar datos en formato JSON al servidor
	 * @param JSON con los datos que se desean pasar al servidor para realizar la consulta
	 * @throws Error en la respuesta con el servidor
	 */
	public void postMethod(JSONObject json) throws Exception {
		
		conexion.setDoInput(true);
		conexion.setDoOutput(true);
		conexion.setUseCaches(false);
		conexion.setRequestProperty("Content-Type", "application/json");
		conexion.setRequestProperty("Accept", "application/json");
		conexion.connect();
		
		OutputStream output = conexion.getOutputStream();
		
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
		writer.write(json.toString());
		writer.flush();
		writer.close();
		
		System.out.println(getRespuestaServer());
		
		conexion.disconnect();
		
	} 
	
	/**
	 * M�todo que devuelve el estado de la consulta
	 * @return El estado de la consulta realizada
	 * @throws Error en la respuesta con el servidor
	 */
	private String getRespuestaServer() throws Exception {
		
		if(conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			
			JSONObject json = new JSONObject(reader.readLine().toString());
		
			if(json.get("estado").toString().equals("1")) 
				return "LA OPERACI�N SE REALIZ� CORRECTAMENTE";
			else
				return "NO SE PUDO REALIZAR LA OPERACI�N";
			
		} else return "HA OCURRIDO UN ERROR EN LA CONEXI�N CON EL SERVIDOR";
		
	}
	
	public void closeConnection() {
		
		if(conexion != null) conexion.disconnect();
		
	}
	
}
