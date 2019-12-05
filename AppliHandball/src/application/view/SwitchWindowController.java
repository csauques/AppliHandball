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
		  System.out.println("salut t'a bien cliqué");
		  	okClicked = true;
		  	Person selectedPerson = tabRemp.getSelectionModel().getSelectedItem();
		       if (selectedPerson != null) {
		    	   selectedPerson.setIsPlaying(true);
		       } else {
		           // Nothing selected.
		           Alert alert = new Alert(AlertType.WARNING);
		           alert.initOwner(Main.getPrimaryStage());
		           alert.setTitle("No Selection");
		           alert.setHeaderText("No Person Selected");
		           alert.setContentText("Please select a person in the table.");

		           alert.showAndWait();
		       }
		       dialogStage.close();
		   }

	 
}
