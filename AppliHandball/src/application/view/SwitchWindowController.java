package application.view;


import application.model.Person;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class SwitchWindowController {
	
	
	@FXML
	private TableView<Person> tabRemp;
	
	@FXML
	private Button btnValider;
	
	@FXML
	private Button btnAnnuler;
	
    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;
    
    
	 public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	   }
	 
	 @FXML
	  private void initialize() {
	  }
	 
	 public void setPerson(Person person,ObservableList<Person> Remplacants ) {
	        this.person = person;
	        //remplir le tableau
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
	            dialogStage.close();
	    }
	 
}
