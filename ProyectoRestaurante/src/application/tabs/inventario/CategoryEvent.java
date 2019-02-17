package application.tabs.inventario;

import application.ControlVentanaPrincipal;
import application.cardItem.ControlCardItem;
import javafx.event.Event;
import javafx.event.EventHandler;

public class CategoryEvent implements EventHandler<Event>{

	private String id;
	
	public static Categoria category;
	
	private static String currentCategory = "comida";
	
	public CategoryEvent(String id) {
		this.id = id;
		
	}

	@Override
	public void handle(Event e) {
		
		if(!currentCategory.equals(id)) {
			
			Inventario.clearInventario();
			
			category = new Categoria(id);
			
			category.addAllCategoryCards();
			
			ControlCardItem.setHeaderVisible(false);			
			
			ControlVentanaPrincipal.srcControl.imageControl.setCategoria(id.toLowerCase());
			
		}
		
		currentCategory = id;
		ControlVentanaPrincipal.srcControl.lblCategoria.setText(currentCategory.toUpperCase() + "S");
		
	}

}
