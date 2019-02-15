package application.tabs.inventario;

import application.ControlVentanaPrincipal;
import application.cardItem.ControlCardItem;
import javafx.event.Event;
import javafx.event.EventHandler;

public class CategoryEvent implements EventHandler<Event>{

	private String id;
	
	public static Categoria c;
	
	private static String state = "comida";
	
	public CategoryEvent(String id) {
		this.id = id;
	}

	@Override
	public void handle(Event e) {
		
		if(!state.equals(id)) {
			
			Inventario.clearInventario();
			
			c = new Categoria(id);
			
			c.addAllCategoryCards();
			
			ControlCardItem.setVisibleAll(false);			
			
		}
		
		state = id;
		ControlVentanaPrincipal.srcControl.lblCategoria.setText(state.toUpperCase() + "S");
		
	}

}
