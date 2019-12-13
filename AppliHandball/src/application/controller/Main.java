package application.controller;


import application.model.Person;
import application.view.SwitchWindowController;
import application.view.pageAideController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;

import java.io.IOException;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	
	private static Stage primaryStage;
    private static BorderPane rootLayout;
    private static Scene scene;
    private MenuBar myMenu;
    //trouver un moyen de stocker tous les joueurs dans une structur de donnée (liste peut etre ??)
    
    private static ObservableList<Person> personEquip1 = FXCollections.observableArrayList();
    private static ObservableList<Person> personEquip2 = FXCollections.observableArrayList();
    
    public Main() {
        // Add some sample data
    	personEquip1.add(new Person("Deschamps", "Didier", "Ent", true));
    	personEquip1.add(new Person("Hans", "Muster", "1", true));
    	personEquip1.add(new Person("Alain", "Terieur", "2", true));
    	personEquip1.add(new Person("Alex", "Terieur", "3", true));
    	personEquip1.add(new Person("Alain", "Proviste", "4", true));
    	personEquip1.add(new Person("Jean", "Darmerie", "5", true));
    	personEquip1.add(new Person("Hans", "Inusite", "6", true));
    	personEquip1.add(new Person("Marc", "Assin", "7", true));
    	personEquip1.add(new Person("Ric", "Ola", "8", false));
    	personEquip1.add(new Person("PAVAAAAAARD", "PAVAAAAAAAAAAAAAAAAARD", "9", false));
    	
    	personEquip2.add(new Person("Dinart", "Didier", "Ent", true));
    	personEquip2.add(new Person("Emile", "Piedeudan", "1", true));
    	personEquip2.add(new Person("Guy", "Tarrebasse", "2", true));
    	personEquip2.add(new Person("Djamel", "Audos", "3", true));
    	personEquip2.add(new Person("Luc", "Arne", "4", true));
    	personEquip2.add(new Person("Charles", "Atan", "5", true));
    	personEquip2.add(new Person("Gerard", "Manvussa", "6", true));
    	personEquip2.add(new Person("Paul", "Lauchon", "7", true));
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
        	final String[] viewOptions = new String[] {
        			"tamer"
        	};
            // chargement du rootLayout qui sert de base à toute l'application
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            // //affichage du rootLayout
            scene = new Scene(rootLayout, 500, 500);
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
	
	public static Scene getScene() {
        return scene;
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
            primaryStage.setMinHeight(lar);
            primaryStage.setMinWidth(lon);
            primaryStage.setMaxHeight(lar);
            primaryStage.setMaxWidth(lon);
            //primaryStage.setMaximized(true);
            primaryStage.setScene(scene);
            rootLayout.setCenter(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	 public static boolean showSwitchWindow(Person person, ObservableList<Person> Remplacants) {
	        try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("../view/SwitchWindow.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Changement de joueur");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            SwitchWindowController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setPerson(person, Remplacants);

	            // Show the dialog and wait until the user closes it
	            dialogStage.showAndWait();

	            return controller.isOkClicked();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	 public static void ouvrirAide() {
	      try {
	            // Load the fxml file and create a new stage for the popup dialog.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("../view/pageAide.fxml"));
	            AnchorPane page = (AnchorPane) loader.load();

	            // Create the dialog Stage.
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("aide");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(page);
	            dialogStage.setScene(scene);

	            // Set the person into the controller.
	            pageAideController controller = loader.getController();

	            // Show the dialog and wait until the user closes it
	            dialogStage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	public static void stopApp() {
		primaryStage.close();
	}
}
