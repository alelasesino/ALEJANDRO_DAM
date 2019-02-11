package application.tabs.inventario;

import javafx.event.Event;
import javafx.event.EventHandler;

public class CategoryEvent implements EventHandler<Event>{

	private String id;
	
	private static String state = "";
	
	public CategoryEvent(String id) {
		this.id = id;
	}

	@Override
	public void handle(Event e) {
		
		if(!state.equals(id)) {
			
			Inventario.clearInventario();
			
			Categoria c = new Categoria(id);
			
			c.addAllCategoryCards();
			
		}
			state = id;
		
	}

}
