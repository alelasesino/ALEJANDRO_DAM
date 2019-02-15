package application.tabs.inventario;

import java.util.ArrayList;

import application.ControlVentanaPrincipal;
import application.cardItem.CardItem;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

/**
 * @author Alejandro Pérez Álvarez
 *
 */
public class Inventario {
	
	private static int numColumnas = 1;
	private static final int MAX_WIDTH_COLUMN = 110;
	private static final int MAX_HEIGHT_COLUMN = 125;
	
	public static void addCard(CardItem tarjeta) {

		if(numColumnas != numMaxColumnas()) {
			
			addColumna();

		} else {
			
			numColumnas = 1;
			
			addFila();
			
		}
		
		ControlVentanaPrincipal.srcControl.gridInventario.addColumn(numColumnas++, tarjeta.getTarjeta());
		
	}
	
	private static int numMaxColumnas() {
		return 9;
	}
	
	private static void addColumna() {
		
		ColumnConstraints colConst = new ColumnConstraints();
        colConst.setMaxWidth(MAX_WIDTH_COLUMN);
        colConst.setPrefWidth(MAX_WIDTH_COLUMN);
        colConst.setMinWidth(MAX_WIDTH_COLUMN);
        ControlVentanaPrincipal.srcControl.gridInventario.getColumnConstraints().add(colConst);
		
	}
	
	private static void addFila() {
		
		RowConstraints rowConst = new RowConstraints();
		rowConst.setMaxHeight(MAX_HEIGHT_COLUMN);
		rowConst.setPrefHeight(MAX_HEIGHT_COLUMN);
		rowConst.setMinHeight(MAX_HEIGHT_COLUMN);
		ControlVentanaPrincipal.srcControl.gridInventario.getRowConstraints().add(rowConst);
		
	}
	
	public static void eventTab() {
						
		ControlVentanaPrincipal.srcControl.item_progressbar.setVisible(false);
		
	}
	
	public static void clearInventario() {
		
		ControlVentanaPrincipal.srcControl.Scroll_Inventario.setVvalue(0);
		ControlVentanaPrincipal.srcControl.gridInventario.getChildren().clear();

		ControlVentanaPrincipal.srcControl.Pane_scroll_Inventario.setMinHeight(ControlVentanaPrincipal.srcControl.gridInventario.getHeight());
		
		numColumnas = 1;
		
	}
	
	public static void addAllCards(ArrayList<CardItem> cards) {
		
		for(CardItem c : cards) addCard(c);
		
	}
	
}


