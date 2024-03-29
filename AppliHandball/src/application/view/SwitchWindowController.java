package application.view;


import application.controller.Main;
import application.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SwitchWindowController {
	
	
	@FXML
	private TableView<Person> tabRemp;
	
	@FXML
	private Button btnValider;
	
	@FXML
	private Button btnAnnuler;
	
	
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TableColumn<Person, String> numberColumn;
    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;
    
    public SwitchWindowController() {}
	 public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	   }
	 
	 @FXML
	  private void initialize() {
		 
		 firstNameColumn.setCellValueFactory(
	                cellData -> cellData.getValue().lastNameProperty());
	     lastNameColumn.setCellValueFactory(
	                cellData -> cellData.getValue().firstNameProperty());
	     numberColumn.setCellValueFactory(
	                cellData -> cellData.getValue().numberProperty());
	  }
	 
	 public void setPerson(Person person,ObservableList<Person> Remplacants) {
		 
	      this.person = person;
	      tabRemp.setItems(Remplacants);
	      
	  }
	 
	 public boolean isOkClicked() {
	        return okClicked;
	  }
	 
	  @FXML
	  private void handleCancel() {
	        dialogStage.close();
	  }
	  
	  @FXML
	  private void handleOk() {
		  	okClicked = true;
		  	Person selectedPerson = tabRemp.getSelectionModel().getSelectedItem();
		       if (selectedPerson != null) {
		    	   if(selectedPerson.isAvaliable()) {
			    	   selectedPerson.setIsPlaying(true);
		    	   }else {
		    		   //cette personne est disqualifiée et ets donc inutilisable
		    		   // Nothing selected.
			           Alert alert = new Alert(AlertType.WARNING);
			           alert.initOwner(Main.getPrimaryStage());
			           alert.setTitle("Joueur indisponible");
			           alert.setHeaderText("Ce joueur n'est pas disponible");
			           alert.setContentText("Merci de choisir un autre joueur pour remplacer.");

			           alert.showAndWait();
		    	   }

		       } else {
		           // Nothing selected.
		           Alert alert = new Alert(AlertType.WARNING);
		           alert.initOwner(Main.getPrimaryStage());
		           alert.setTitle("Aucun joueur selectionne");
		           alert.setHeaderText("Personne n'est selectionne");
		           alert.setContentText("Veuillez choisir une personne.");

		           alert.showAndWait();
		       }
		       dialogStage.close();
		   }

	 
}
