package application.model;

import java.util.Optional;

import application.controller.Main;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Person {
	
	private StringProperty _firstName;
	private StringProperty _lastName;
	private StringProperty _number;
	private int _nbExcl;
	private int _nbYellow;
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
			dialogC.setTitle("voulez-vous donner un carton jaune à ce joueur ?");
			dialogC.setHeaderText(null);
			dialogC.setContentText("souhaitez-vous réellement donner un carton au joueur numéro ".concat(this.getNumber()).concat(" ?"));
			Optional<ButtonType> answer= dialogC.showAndWait();
			if(answer.get() == ButtonType.OK){
				_nbYellow++;
				}
		}else {
			//afficher une fenetre disant que l'utilisateur a deja reçu un avertissement 
			// Nothing selected.
	           Alert alert = new Alert(AlertType.WARNING);
	           alert.initOwner(Main.getPrimaryStage());
	           alert.setTitle("carton jaune deja reçu");
	           alert.setHeaderText("ce joueur a deja reçu un carton jaune");
	           alert.setContentText("impossible d'en ajouter un autre");

	           alert.showAndWait();
	       }
		}
	
	public void addExcl() {
		
		//eventuellement, ajouter une fenetre de dialogue demandant a l'utilisateur de confirmer qu'il veut lui donner le carton
		Alert dialogC= new Alert(AlertType.CONFIRMATION);
		dialogC.setTitle("voulez-vous donner un carton jaune à ce joueur ?");
		dialogC.setHeaderText(null);
		dialogC.setContentText("souhaitez-vous réellement exclure pendant 2 minutes le joueur ".concat(this.getNumber().concat(" ?")));
		Optional<ButtonType> answer= dialogC.showAndWait();
		if(answer.get() == ButtonType.OK){
			_nbExcl++;
			//TODO g�rer les timers et autre 
		}
		if( _nbExcl == 3) {
			//avertir l'utilisateur par une fenetre que le joueur est maintenant éjecté du terrain
			Alert alert = new Alert(AlertType.WARNING);
	           alert.initOwner(Main.getPrimaryStage());
	           alert.setTitle("joueur exclu");
	           alert.setHeaderText("ce joueur en est a a 3 eme exclusion");
	           alert.setContentText("il est donc disqualifi�. vous pourez l'�changer avec un autre joueur dans 2 minutes");

	           alert.showAndWait();
	           isAvaliable = false;
		}
	}
	
	public void addRed() {
		//demander a l'utilisateur si il accepte VRAIMENT de donner un carton rouge 
		_nbRed ++;
		this.isAvaliable = false;
	}
	
	public void setIsAvaliable(boolean isAvaliable) {
		this.isAvaliable = isAvaliable;
	}
	
	public void setIsPlaying(boolean val) {
		isPlaying = val;
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
