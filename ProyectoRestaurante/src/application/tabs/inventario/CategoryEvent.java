package application.tabs.inventario;

import application.ControlVentanaPrincipal;
import application.EnumCategory;
import application.cardItem.ControlCardItem;
import javafx.event.Event;
import javafx.event.EventHandler;

public class CategoryEvent implements EventHandler<Event>{

	private String id;
	
	public static Categoria category;
	
	private static String currentCategory = "comida";
	
	public CategoryEvent(EnumCategory cat) {
		this.id = cat.name();
		
	}

	@Override
	public void handle(Event e) {
		
		if(!currentCategory.equals(id)) {
			
			Inventario.clearInventario();
						
			category = new Categoria(Enum.valueOf(EnumCategory.class, id));
			
			category.addAllCategoryCards();
			
			ControlCardItem.setHeaderVisible(false);			
			
			ControlVentanaPrincipal.srcControl.imageControl.setCategoria(id.toLowerCase());
			
		}
		
		currentCategory = id;
		ControlVentanaPrincipal.srcControl.lblCategoria.setText(currentCategory.toUpperCase() + "S");
		
	}

}
