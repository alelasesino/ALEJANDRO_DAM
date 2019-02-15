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
 * en la direccion de imagenes de al aplicacion
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
		
		/*try {
			copyImgToSource();
		} catch (Exception e) {
			System.err.println("NO SE PUDO COPIAR LA IMAGEN");
		}*/
		
	}
	
	private void addFilters() {
		
		fileChooser.getExtensionFilters().add(new ExtensionFilter("IMAGEN", "*.png"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("IMAGEN", "*.jpg"));
		
	}
	
	public void copyImgToSource() throws Exception {
		
		if(file != null) {
			
			writeBytesFiles(getBytesFiles());
			
		}
		
	}
	
	public void setRequestFile() {
		file = fileChooser.showOpenDialog(Main.primaryStage);
	}

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
	
	private void writeBytesFiles(int[] bytes) throws Exception{
		
		FileOutputStream out = new FileOutputStream("src/img/" + categoria.toLowerCase() + "/" + file.getName());
		
		BufferedOutputStream buffer = new BufferedOutputStream(out);
		
		for(int a : bytes) buffer.write(a);
		
		buffer.close();
		out.close();
		
	}
	
	public Image getImagen() throws FileNotFoundException {
		
		if(file == null) return null;//new Image("noImage.png");
		
		imgName = file.getName();
		
		FileInputStream input = new FileInputStream(file);
		
		return new Image(input);
		
	}
	
	public File getFile() {
		return file;
	}
	
	public String getImgName() {
		return imgName;
	}

}
