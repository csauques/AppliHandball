package application.view;

import java.io.IOException;

import application.controller.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RootLayoutController {
	
	public void ouvrirAide() throws IOException {
		//Main.ouvrirAide();
		Runtime rt = Runtime.getRuntime();
		Process processus = rt.exec("firefox ".concat(System.getProperty("user.dir") + "/" + "src/application/model/page_aide/index.html"));
	}
	
	@FXML
	public void close() {
		Main.stopApp();
	}
	
	@FXML
	public void ouvrirMatch(){
		Main.changeScene("../view/RealTimev1.fxml", 1280, 800);
	}
	
	@FXML
	public void ovrirStats() {
		Main.changeScene("../view/Statistiques.fxml", 1280, 800);
	}
}
