package application.model;

public class Person {
	
	private String _firstName;
	private String _lastName;
	private int _number;
	private int _nbExcl;
	private int _nbYellow;
	private int _nbRed;
	
	private boolean isAvaliable = true;
	private boolean isPlaying = false;
	
	
	
	public Person(String firstName, String lastName, int number) {
		this._firstName = firstName;
		this._lastName = lastName;
		this._number = number;
		this._nbExcl = 0;
		this._nbRed = 0;
		this._nbYellow= 0;
	}
	
	//getters
	public int getNumber() {
		return _number;
	}
	
	public String getFirstName() {
		return _firstName;
	}
	
	public String getLastName() {
		return _lastName;
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
	
	
	
	
}
