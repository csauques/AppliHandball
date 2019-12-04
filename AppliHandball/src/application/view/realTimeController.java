package application.view;

import application.controller.Main;
import application.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class realTimeController {
	
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
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label numberLabel;
    @FXML
    private Label nbExclLabel;
    @FXML
    private Label nbRedLabel;
    @FXML
    private Label nbYellowLabel;

    
    public realTimeController() {
    }
    
    @FXML
    private void initialize() {
    	// Initialize the person table with the two columns.
        firstNameColumn1.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn1.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());
        numberColumn1.setCellValueFactory(
                cellData -> cellData.getValue().numberProperty());
        
        firstNameColumn2.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn2.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());
        numberColumn2.setCellValueFactory(
                cellData -> cellData.getValue().numberProperty());
        
        setMainApp();

        // Clear person details.
        //showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        //personTable.getSelectionModel().selectedItemProperty().addListener(
        //        (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }
    
    public void setMainApp() {
        // Add observable list data to the table
        personTable1.setItems(Main.getPersonData(1));
        personTable2.setItems(Main.getPersonData(2));
    }
    
   /* private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());

            // TODO: We need a way to convert the birthday into a String!
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }*/

}
