package application.view;

import application.controller.Main;
import application.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;

public class RealTimeController {
	
	private ObservableList<Person> joueurRemp1 = FXCollections.observableArrayList();
	private ObservableList<Person> joueurRemp2 = FXCollections.observableArrayList();
	
	private ObservableList<Person> equipe1= FXCollections.observableArrayList();
	private ObservableList<Person> equipe2 = FXCollections.observableArrayList();
	
	@FXML
    private TableView<Person> personTable1;
	@FXML
    private TableView<Person> personTable2;
    @FXML
    private TableColumn<Person, String> firstNameColumn1;
    @FXML
    private TableColumn<Person, String> lastNameColumn1;
    @FXML
    private TableColumn<Person, String> numberColumn1;
    @FXML
    private TableColumn<Person, String> firstNameColumn2;
    @FXML
    private TableColumn<Person, String> lastNameColumn2;
    @FXML
    private TableColumn<Person, String> numberColumn2;

    @FXML
    private Label numberLabel2;
    @FXML
    private Label nbExclLabel2;
    @FXML
    private Label nbRedLabel2;
    @FXML
    private Label nbYellowLabel2;
    

    @FXML
    private Label numberLabel1;
    @FXML
    private Label nbExclLabel1;
    @FXML
    private Label nbRedLabel1;
    @FXML
    private Label nbYellowLabel1;
    
    
    public RealTimeController() {
    }
    
    @FXML
    private void initialize() {
    	
    	// Initialize the person table with the two columns.
        firstNameColumn1.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());
        lastNameColumn1.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        numberColumn1.setCellValueFactory(
                cellData -> cellData.getValue().numberProperty());
        
        firstNameColumn2.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());
        lastNameColumn2.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        numberColumn2.setCellValueFactory(
                cellData -> cellData.getValue().numberProperty());
        
        setMainApp();

        //Clear person details.
        showPersonDetails(null, 0);

        //Listen for selection changes and show the person details when changed.
        personTable1.getSelectionModel().selectedItemProperty().addListener(
              (observable, oldValue, newValue) -> showPersonDetails(newValue, 1));
        
        personTable2.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue, 2));
    }
    
    public void setMainApp() {
    	
        // Add observable list data to the table
    	
    	equipe1 = Main.getPersonData(1);
    	equipe2 = Main.getPersonData(2);
    	
    	//récupration des joueurs remplaçant et stockage
    	for(int i = 0 ; i < equipe1.size(); i++) {
    		if(!(equipe1.get(i).isPlaying())) {
    			joueurRemp1.add(equipe1.get(i));
    			//equipe1.remove(i);
    		}
    	}
    	
    	for(int i = 0 ; i < equipe2.size(); i++) {
    		if(!(equipe2.get(i).isPlaying())) {
    			joueurRemp2.add(equipe2.get(i));
    			//equipe2.remove(i);
    		}
    	}
    	
    	
    	//suppression des personnes n'etant pas en jeu 
    	for(int i = 0; i < joueurRemp1.size(); i++) {
    		for(int j = 0; j < equipe1.size(); j++) {
    			if(joueurRemp1.get(i) == equipe1.get(j)) {
    				equipe1.remove(j);
    			}
    		}
    	}
    	for(int i = 0; i < joueurRemp2.size(); i++) {
    		for(int j = 0; j < equipe2.size(); j++) {
    			if(joueurRemp2.get(i) == equipe2.get(j)) {
    				equipe2.remove(j);
    			}
    		}
    	}
        personTable1.setItems(equipe1);
        personTable2.setItems(equipe2);
    }
    
    
    
   private void showPersonDetails(Person person, int nb) {
        if (person != null) {
            if(nb == 1) {
            	numberLabel1.setText(person.getNumber());
            	nbExclLabel1.setText(Integer.toString(person.getNbExcl()));
            	nbRedLabel1.setText(Integer.toString(person.getRed()));
            	nbYellowLabel1.setText(Integer.toString(person.getYellow()));
            }else {
            	numberLabel2.setText(person.getNumber());
            	nbExclLabel2.setText(Integer.toString(person.getNbExcl()));
            	nbRedLabel2.setText(Integer.toString(person.getRed()));
            	nbYellowLabel2.setText(Integer.toString(person.getYellow()));
            }

        } else {
            
           numberLabel1.setText("");
           numberLabel2.setText("");
           nbExclLabel2.setText("");
           nbExclLabel1.setText("");
           nbRedLabel2.setText("");
           nbRedLabel1.setText("");
           nbYellowLabel2.setText("");
           nbYellowLabel1.setText("");
        }
    }
   
   @FXML
   public void remplacer1() {
	   remplacer(personTable1, joueurRemp1, equipe1);
   }
   
   @FXML
   public void remplacer2() {
	   remplacer(personTable2, joueurRemp2, equipe2);
   }
   
   
   
   public void remplacer(TableView<Person> tabPers, ObservableList<Person> tabRemp, ObservableList<Person> tabEq) {
	   Person selectedPerson = tabPers.getSelectionModel().getSelectedItem();
       if (selectedPerson != null) {
           boolean okClicked = Main.showSwitchWindow(selectedPerson, tabRemp);
           if (okClicked) {
               selectedPerson.setIsPlaying(false);
               
               for(int i = 0; i < tabEq.size(); i++) {
            	   if(!(tabEq.get(i).isPlaying())) {
            		   for(int j = 0; j< tabRemp.size(); j++) {
            			   if(tabRemp.get(j).isPlaying()) {
            				   Person temp;
            				   temp = tabRemp.get(j);
            				   tabRemp.remove(j);
            				   tabEq.add(temp);
            				   tabRemp.add(tabEq.get(i));
            				   tabEq.remove(i);
            				   tabPers.setItems(tabEq);
            			   }
            		   }
            	   }
               }
           }

       } else {
           // Nothing selected.
           Alert alert = new Alert(AlertType.WARNING);
           alert.initOwner(Main.getPrimaryStage());
           alert.setTitle("No Selection");
           alert.setHeaderText("No Person Selected");
           alert.setContentText("Please select a person in the table.");

           alert.showAndWait();
       }
   }
   
   
   public void addYellow(TableView<Person> tabPers, int nb) {
	   
	   Person selectedPerson = tabPers.getSelectionModel().getSelectedItem();
	   if(selectedPerson != null) {
		   selectedPerson.addYellow();
		   showPersonDetails(selectedPerson, nb);
	   }else {
		// Nothing selected.
           Alert alert = new Alert(AlertType.WARNING);
           alert.initOwner(Main.getPrimaryStage());
           alert.setTitle("Aucune personne n'a été sélectionnée");
           alert.setHeaderText("Aucune persone n'est sélectionnée");
           alert.setContentText("Merci de bien vouloir sélectionner une persone.");

           alert.showAndWait();
	   }

   }
   
   public void addExcl(TableView<Person> tabPers, int nb) {
	   Person selectedPerson = tabPers.getSelectionModel().getSelectedItem();
	   if(selectedPerson != null) {
		   selectedPerson.addExcl();
		   showPersonDetails(selectedPerson, nb);
	   }else {
		// Nothing selected.
           Alert alert = new Alert(AlertType.WARNING);
           alert.initOwner(Main.getPrimaryStage());
           alert.setTitle("Aucune personne n'a été sélectionnée");
           alert.setHeaderText("Aucune persone n'est sélectionnée");
           alert.setContentText("Merci de bien vouloir sélectionner une persone.");

           alert.showAndWait();
	   }
   }
   
   @FXML 
   public void addYellow1() {
	   addYellow(personTable1, 1);
   }
   
   @FXML 
   public void addYellow2() {
	   addYellow(personTable2, 2);
   }
   
   @FXML
   public void addExcl1() {
	   addExcl(personTable1, 1);
   }
   
   @FXML
   public void addExcl2() {
	   addExcl(personTable2, 2);
   }

}
