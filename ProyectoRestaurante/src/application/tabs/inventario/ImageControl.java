package application.tabs.inventario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import application.Main;
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
	
	public ImageControl() {
	
		addFilters();
		
		try {
			copyImgToSource();
		} catch (Exception e) {
			System.err.println("ARCHIVO NO ENCONTRADO");
		}
		
	}
	
	private void addFilters() {
		
		fileChooser.getExtensionFilters().add(new ExtensionFilter("IMAGEN", "*.png"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("IMAGEN", "*.jpg"));
		
	}
	
	private void copyImgToSource() throws Exception {
		
		File file = getFile();
		
		
		
		if(file != null) {
			
			FileInputStream input =  new FileInputStream(file.getAbsoluteFile());
			
			System.out.println((int) input.getChannel().size());
			int[] img = new int[(int) input.getChannel().size()];
			
			int i = 0;
			while(input.read() != -1) {
				
				img[i] = input.read();
				//System.out.println(img[i]);
				
				//System.out.println();
				
			}
			
			for(int j = 0; j<img.length; j++) {
				System.out.println(img[j]);
			}
			
			
		}
		
		
	}
	
	private File getFile() {
		return fileChooser.showOpenDialog(Main.primaryStage);
		
	}

}
