package application.controller;


import application.model.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	
	private static Stage primaryStage;
    private static BorderPane rootLayout;
    private static Scene scene;
    //trouver un moyen de stocker tous les joueurs dans une structur de donnée (liste peut etre ??)
    
    private static ObservableList<Person> personEquip1 = FXCollections.observableArrayList();
    private static ObservableList<Person> personEquip2 = FXCollections.observableArrayList();
    
    public Main() {
        // Add some sample data
    	personEquip1.add(new Person("Hans", "Muster", "1", true));
    	personEquip1.add(new Person("Alain", "Terieur", "2", true));
    	personEquip1.add(new Person("Alex", "Terieur", "3", true));
    	personEquip1.add(new Person("Alain", "Proviste", "4", true));
    	personEquip1.add(new Person("Jean", "Darmerie", "5", true));
    	personEquip1.add(new Person("Hans", "Inusite", "6", false));
    	personEquip1.add(new Person("Marc", "Assin", "7", false));
    	personEquip1.add(new Person("Ric", "Ola", "8", false));
    	
    	personEquip2.add(new Person("Emile", "Piedeudan", "1", true));
    	personEquip2.add(new Person("Guy", "Tarrebasse", "2", true));
    	personEquip2.add(new Person("Djamel", "Audos", "3", true));
    	personEquip2.add(new Person("Luc", "Arne", "4", true));
    	personEquip2.add(new Person("Charles", "Atan", "5", true));
    	personEquip2.add(new Person("Gerard", "Manvussa", "6", false));
    	personEquip2.add(new Person("Paul", "Lauchon", "7", false));
    	personEquip2.add(new Person("Vincent", "Thimaitre", "8", false));
    }
    
    public static  ObservableList<Person> getPersonData(int nb) {
    	if(nb == 1) {
    		return personEquip1;
    	}else {
    		return personEquip2;
    	}

    }
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AppliHandball");

        initRootLayout();
        showMenu();
	}
	
	public void initRootLayout() {
        try {
            // chargement du rootLayout qui sert de base à toute l'application
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            // //affichage du rootLayout
            scene = new Scene(rootLayout, 500, 500);
            //rootLayout.setPrefHeight(500);
            //rootLayout.setPrefWidth(500);
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
	
	public static void changeScene(String scenePath, double lon,double lar) {
		try {
			//recuperation et affichage de la scene voulue cependant il va falloir gérer les tailles des fenetres 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(scenePath));
            AnchorPane menu = (AnchorPane) loader.load();
            rootLayout.setPrefHeight(lar);
            rootLayout.setPrefWidth(lon);
            primaryStage.setMaximized(true);
            primaryStage.setScene(scene);
            rootLayout.setCenter(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void stopApp() {
		//trouver un moyen de stopper l'application
	}
}
