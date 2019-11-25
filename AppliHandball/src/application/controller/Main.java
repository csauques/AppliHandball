package application.controller;


import application.model.Person;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	
	private static Stage primaryStage;
    private static BorderPane rootLayout;
    //trouver un moyen de stocker tous les joueurs dans une structur de donnée (liste peut etre ??)
    
    
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AppliHandball");

        initRootLayout();
        showMenu();
	}
	
	public void initRootLayout() {
        try {
            // chargeùent du rootLayout qui sert de base à toute l'application
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            // //affichage du rootLayout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showMenu() {
        try {
            // chargement du meu de demarage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/StartMenu.fxml"));
            AnchorPane menu = (AnchorPane) loader.load();
           //afichage du menu demarrage au milieu du rootLAyout
            rootLayout.setCenter(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static Stage getPrimaryStage() {
        return primaryStage;
    }

	public static void main(String[] args) {
		launch(args);
	}
	
	public static void changeScene(String scenePath) {
		try {
			//recuperation et affichage de la scene voulue cependant il va falloir gérer les tailles des fenetres 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(scenePath));
            AnchorPane menu = (AnchorPane) loader.load();
            rootLayout.setCenter(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void stopApp() {
		//trouver un moyen de stopper l'application
	}
}
