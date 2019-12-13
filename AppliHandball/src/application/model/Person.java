package application.model;

import java.util.List;
import java.util.Optional;

import application.controller.Main;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;

public class Person {
	
	private StringProperty _firstName;
	private StringProperty _lastName;
	private StringProperty _number;
	private Image _img;
	private int _nbExcl;
	private int _nbYellow;
	private boolean rapport;
	private int _nbRed;
	
	private boolean isAvaliable = true;
	private boolean isPlaying = false;
	
	
	
	public Person(String firstName, String lastName, String number, boolean isP) {
		this._firstName = new SimpleStringProperty(firstName);
		this._lastName = new SimpleStringProperty(lastName);
		this._number = new SimpleStringProperty(number);
		this._nbExcl = 0;
		this._nbRed = 0;
		this._nbYellow= 0;
		this.isPlaying = isP;
		this.rapport = false;
		//this._img = new Image("Carton_jaune.png");
	}
	
	//getters
	public String getNumber() {
		return _number.get();
	}
	
	public String getFirstName() {
		return _firstName.get();
	}
	
	public String getLastName() {
		return _lastName.get();
	}
	
	public int getNbExcl() {
		return _nbExcl;
	}
	
	public int getYellow() {
		return _nbYellow;
	}
	
	public int getRed() {
		return _nbRed;
	}
	
	public Image getImg(){
		return _img;
	}
	
	public boolean isAvaliable() {
		return isAvaliable;
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	//setters
	public void addYellow() {
		if(_nbYellow == 0) {
			//eventuellement, ajouter une fenetre de dialogue demandant a l'utilisateur de confirmer qu'il veut lui donner le carton
			Alert dialogC= new Alert(AlertType.CONFIRMATION);
			dialogC.setTitle("Voulez-vous donner un carton jaune a ce joueur ?");
			dialogC.setHeaderText(null);
			dialogC.setContentText("Souhaitez-vous reellement donner un carton au joueur numero ".concat(this.getNumber()).concat(" ?"));
			Optional<ButtonType> answer= dialogC.showAndWait();
			if(answer.get() == ButtonType.OK){
				_nbYellow++;
				}
		}else {
			//afficher une fenetre disant que l'utilisateur a deja rea�u un avertissement 
			// Nothing selected.
	           Alert alert = new Alert(AlertType.WARNING);
	           alert.initOwner(Main.getPrimaryStage());
	           alert.setTitle("Carton jaune deja recu");
	           alert.setHeaderText("Ce joueur a deja recu un carton jaune");
	           alert.setContentText("Impossible d'en ajouter un autre");

	           alert.showAndWait();
	       }
		}
	
	public void addExcl() {
		
		//eventuellement, ajouter une fenetre de dialogue demandant a l'utilisateur de confirmer qu'il veut lui donner le carton
		Alert dialogC= new Alert(AlertType.CONFIRMATION);
		dialogC.setTitle("Voulez-vous donner un carton jaune a ce joueur ?");
		dialogC.setHeaderText(null);
		dialogC.setContentText("Souhaitez-vous reellement exclure pendant 2 minutes le joueur ".concat(this.getNumber().concat(" ?")));
		Optional<ButtonType> answer= dialogC.showAndWait();
		if(answer.get() == ButtonType.OK){
			_nbExcl++;
			//TODO g�rer les timers et autre 
		}
		if( _nbExcl == 3) {
			//avertir l'utilisateur par une fenetre que le joueur est maintenant ejecte du terrain
			Alert alert = new Alert(AlertType.WARNING);
	           alert.initOwner(Main.getPrimaryStage());
	           alert.setTitle("Joueur exclu");
	           alert.setHeaderText("Ce joueur en est a sa 3 eme exclusion");
	           alert.setContentText("Il est donc disqualifie. \n Vous pourez l'echanger avec un autre joueur \n dans 2 minutes");

	           alert.showAndWait();
	           isAvaliable = false;
	           _nbRed++;
		}
	}
	
	
	public void addRed() {
		
		//eventuellement, ajouter une fenetre de dialogue demandant a l'utilisateur de confirmer qu'il veut lui donner le carton
		Alert dialogC= new Alert(AlertType.CONFIRMATION);
		dialogC.setTitle("Voulez-vous donner un carton rouge  a ce joueur ?");
		dialogC.setHeaderText(null);
		dialogC.setContentText("Souhaitez-vous reellement donner un carton rouge au joueur  ".concat(this.getNumber().concat(" ?")));
		Optional<ButtonType> answer= dialogC.showAndWait();
		if(answer.get() == ButtonType.OK){
			_nbRed ++;
			isAvaliable = false;
			//TODO g�rer les timers et autre 
		}
	}
	
public void addBlue() {
		
		//eventuellement, ajouter une fenetre de dialogue demandant a l'utilisateur de confirmer qu'il veut lui donner le carton
		Alert dialogC= new Alert(AlertType.CONFIRMATION);
		dialogC.setTitle("Voulez-vous donner un carton bleu a ce joueur ?");
		dialogC.setHeaderText(null);
		dialogC.setContentText("Souhaitez-vous reellement donner un carton rouge joindre un rapport avec pour le joueur  ".concat(this.getNumber().concat(" ?")));
		Optional<ButtonType> answer= dialogC.showAndWait();
		if(answer.get() == ButtonType.OK){
			if(_nbRed == 0) {
				_nbRed++;
			}
			rapport = true;
			isAvaliable = false;
			//TODO g�rer les timers et autre 
		}
	}
	
	public void setIsAvaliable(boolean isAvaliable) {
		this.isAvaliable = isAvaliable;
	}
	
	public void setIsPlaying(boolean val) {
		isPlaying = val;
	}
	
	public boolean hasReport() {
		return rapport;
	}
	
	public StringProperty firstNameProperty() {
        return _firstName;
    }
	
	public StringProperty lastNameProperty() {
        return _lastName;
    }
	public StringProperty numberProperty() {
        return _number;
    }
	

	
	
	
	
}
