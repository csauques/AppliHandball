package application.view;

import java.io.IOException;

import application.controller.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RootLayoutController {
	
	public void ouvrirAide() {
		Main.ouvrirAide();
	}
}
