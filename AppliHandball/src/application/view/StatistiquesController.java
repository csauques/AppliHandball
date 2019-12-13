package application.view;

import application.model.Equipe;
import application.model.Equipe.Ligue;
import application.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class StatistiquesController {
	
	private static ObservableList<String> ligue = FXCollections.observableArrayList();
	
    private static ObservableList<String> equipePro = FXCollections.observableArrayList();
    private static ObservableList<String> equipeLidl = FXCollections.observableArrayList();
    
    @FXML
    ComboBox<String> comboBoxLigueEq = new ComboBox<String>();
    
    @FXML
    ComboBox<String> comboBoxEquipeEq = new ComboBox<String>();
    
    @FXML
    ComboBox<String> comboBoxLigueMat = new ComboBox<String>();
    
    @FXML
    ComboBox<String> comboBoxEquipe1Mat = new ComboBox<String>();
    
    @FXML
    ComboBox<String> comboBoxEquipe2Mat = new ComboBox<String>();
    
    @FXML
    HBox choixjouEq;
    
    @FXML
    HBox choixjouMat;
    
    @FXML
    GridPane infoEq;
    
    
    @FXML
	private void initialize() {
    	
    	Equipe pa = new Equipe("Pau", Ligue.PRO);
    	Equipe to = new Equipe("Toulouse", Ligue.PRO);
    	Equipe bo = new Equipe("Bordeaux", Ligue.STAR);
    	Equipe cl = new Equipe("Clermont", Ligue.STAR);
    	
    	pa.addJoueur(new Person("Deschamps", "Didier", "Ent", true));
    	pa.addJoueur(new Person("Hans", "Muster", "1", true));
    	
    	to.addJoueur(new Person("Alain", "Terieur", "2", true));
    	to.addJoueur(new Person("Alex", "Terieur", "3", true));
    	
    	bo.addJoueur(new Person("Dinart", "Didier", "Ent", true));
    	bo.addJoueur(new Person("Emile", "Piedeudan", "1", true));
    	
    	cl.addJoueur(new Person("Djamel", "Audos", "3", true));
    	cl.addJoueur(new Person("Luc", "Arne", "4", true));
    	
    	ligue.add("Proligue");
    	ligue.add("Lidl Starligue");
    	
    	equipePro.add(pa.getName());
    	equipePro.add(to.getName());
    	equipeLidl.add(bo.getName());
    	equipeLidl.add(cl.getName());
    	
    	comboBoxLigueEq.setItems(ligue);
    	
    	comboBoxLigueMat.setItems(ligue);

	 }
    
    @FXML
    private void choixLigueEq() {
    	if(comboBoxLigueEq.getValue() == "Proligue") {
    		choixjouEq.setDisable(false);
    		comboBoxEquipeEq.setItems(equipePro);
    	}else if(comboBoxLigueEq.getValue() == "Lidl Starligue") {
    		choixjouEq.setDisable(false);
    		comboBoxEquipeEq.setItems(equipeLidl);
    		
    	}
    	
    }
    
    @FXML
    private void choixLigueMat() {
    	if(comboBoxLigueMat.getValue() == "Proligue") {
    		choixjouMat.setDisable(false);
    		comboBoxEquipe1Mat.setItems(equipePro);
    		comboBoxEquipe2Mat.setItems(equipePro);
    	}else if(comboBoxLigueEq.getValue() == "Lidl Starligue") {
    		choixjouMat.setDisable(false);
    		comboBoxEquipe1Mat.setItems(equipeLidl);
    		comboBoxEquipe2Mat.setItems(equipeLidl);
    		
    	}
    	
    }
    
    @FXML
    private void choixEquipeEq() {
    	for (int i=0; i < equipePro.size(); i++ ) {
    		if(comboBoxEquipeEq.getValue() == equipePro.get(i)) {
        		infoEq.setDisable(false);
        		
        		
    		}
    	}
    	for (int i=0; i < equipeLidl.size(); i++ ) {
    		if(comboBoxEquipeEq.getValue() == equipeLidl.get(i)) {
        		infoEq.setDisable(false);
        		
    		}
    	}
    	
    	
    }
    
    
    
    

}
