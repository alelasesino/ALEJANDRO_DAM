package application.tabs.inventario;

import javafx.scene.control.TextField;

public class PrecioTextValidation extends TextField{

	public PrecioTextValidation() {
		
	}
	
	@Override
	public void replaceText(int start, int end, String text) {
		
		if(text.matches("[0-9.,]") || text.isEmpty()) {
			
			super.replaceText(start, end, text);			
			
		}
		
	}
	
}
