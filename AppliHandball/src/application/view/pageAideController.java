package application.view;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class pageAideController {
	
	public pageAideController() {};
	
	@FXML
	private WebView WV;
	@FXML
	private void initialize() throws MalformedURLException {
		WebEngine we = WV.getEngine();
		
		File file = new File("../model/page_aide/index.html");
		URL url= file.toURI().toURL();
		 
		// file:/C:/test/a.html
		we.load("http://google.com");
		//we.load(url.toString());
	}
}
