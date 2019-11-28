package application.view;


import application.controller.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartMenuController {
	@FXML
	private Button exitButton;
	
	@FXML 
	private Button reatTimeButton;
	
	@FXML
	private Button statsButton;
	
	
	@FXML
	public void  handleRealTime() {
		Main.changeScene("../view/realTime.fxml", 1000, 800);
	}
	
	@FXML
	public void handleStats() {
		Main.changeScene("../view/Stats.fxml", 1000, 800);
	}
	
	public void exit() {
	}
}
