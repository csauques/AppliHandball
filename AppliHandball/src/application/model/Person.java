package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
			_nbYellow++;
		}else {
			//afficher une fenetre disant que l'utilisateur a deja reçu un avertissement 
		}
	}
	
	public void addExcl() {
		
		//demander a l'utilisateur de confirmer si il veut vraiment donner une exclusion
		_nbExcl++;
		isAvaliable = false;
		if(_nbYellow == 1 && _nbExcl == 2) {
			//avertir l'utilisateur par une fenetre que le joueur est maintenant éjecté du terrain
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