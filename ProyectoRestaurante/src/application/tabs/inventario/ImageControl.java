package application.tabs.inventario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import application.EnumCategory;
import application.Main;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Esta clase se encargara de copiar la imagen de la url que se le pase
 * en la direccion de las imagenes de la aplicacion
 * 
 * @author Alejandro Pérez Álvarez
 *
 */
public class ImageControl {
	
	private FileChooser fileChooser = new FileChooser();
	private String imgName;
	private File file;
	private String categoria;
	
	public ImageControl(EnumCategory categoria) {
		
		this.imgName = "noImage.png";
		this.categoria = categoria.name();
		
		addFilters();
		
	}
	
	/**
	 * Método que se encarga de aplicar los filtros de archivos al explorador de archivos
	 */
	private void addFilters() {
		
		fileChooser.getExtensionFilters().add(new ExtensionFilter("IMAGEN", "*.png"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("IMAGEN", "*.jpg"));
		
	}
	
	/**
	 * Método que copia la imagen de origen a la carpeta de recusos de la aplicacion
	 * @throws Exception
	 */
	public void copyImgToSource() throws Exception {
		
		if(file != null) writeBytesFiles(getBytesFiles());
		
	}
	
	/**
	 * Método que muestra el explorador de archivos
	 */
	public void setRequestFile() {
		file = fileChooser.showOpenDialog(Main.primaryStage);
	}

	/**
	 * Método que obtiene los bytes de la imagen seleccionada
	 * @return Devuelve un array de enteros con los bytes del archivo
	 * @throws Exception
	 */
	private int[] getBytesFiles() throws Exception {
		
		FileInputStream input = new FileInputStream(file);
		
		BufferedInputStream buffer = new BufferedInputStream(input);
		
		int readByte;
		
		int[] fileByte = new int[(int) input.getChannel().size()];
		
		int i = 0;
		do{
			
			readByte = buffer.read();
			if(readByte != -1) fileByte[i++] = readByte;
			
		} while(readByte != -1);
	
		buffer.close();
		input.close();
		
		return fileByte;
		
	}
	
	/**
	 * Método que escribe y por tanto crea la imagen en la carpeta de recursos de la aplicación
	 * @param bytes Bytes de la imagen que se desea escribir
	 * @throws Exception
	 */
	private void writeBytesFiles(int[] bytes) throws Exception{
		
		FileOutputStream out = new FileOutputStream("src/img/" + categoria.toLowerCase() + "/" + file.getName());
		
		BufferedOutputStream buffer = new BufferedOutputStream(out);
		
		for(int a : bytes) buffer.write(a);
		
		buffer.close();
		out.close();
		
	}
	
	/**
	 * Obtiene la imagen del archivo de la clase
	 * @return Devuelve un objecto Image con la imagen del archivo
	 * @throws FileNotFoundException En caso de no encontrar el archivo
	 */
	public Image getImagen() throws FileNotFoundException {
		
		if(file == null) return null;
		
		imgName = file.getName();
		
		FileInputStream input = new FileInputStream(file);
		
		return new Image(input);
		
	}
	
	/**
	 * Método que elimina una imagen de la carpeta de recursos de la aplicación
	 * @return Devuelve true si se pudo eliminar el archivo, false si no se pudo eliminar el archivo
	 */
	public boolean removeFile() {
		
		return new File("src" + File.separator + "img"  + File.separator + categoria.toLowerCase() + File.separator + imgName).delete();
		
	}
	
	public File getFile() {
		return file;
	}
	
	public String getImgName() {
		
		if(imgName.equalsIgnoreCase("addItem.png")) return "noImage.png";
		
		return imgName;
		
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
}
