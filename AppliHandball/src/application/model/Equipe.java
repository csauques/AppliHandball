package application.model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class Equipe {
	
	public enum Ligue {
		PRO ("Proligue"),
		STAR("Lidl Starligue");
		
		private String name = "";
		   
		 //Constructeur
		 Ligue(String name){
		    this.name = name;
		 }

		  
	}
	
	private StringProperty _nameEq;
	private List<Person> _menbresEq  = new ArrayList<Person>();
	private Ligue _lig;
	
	
	public Equipe(String name, Ligue li) {
		this._nameEq = new SimpleStringProperty(name);
		this._lig = li;
	}
	
	public String getName() {
		return _nameEq.get();
	}
	
	public StringProperty firstNameProperty() {
        return _nameEq;
    }
	
	public void addJoueur(Person j) {
		_menbresEq.add(j);
	}

}
