package application.cardItem;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ControlCardItem implements Initializable{

	public static ControlCardItem srcControl;
	
    @FXML
    public Label lblProducto;

    @FXML
    public ImageView imgProducto;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		srcControl = this;
		
	}
	
}
