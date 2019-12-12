package application.view;



import java.awt.MouseInfo;
import java.awt.Point;
import java.util.Optional;

import application.controller.Main;
import application.model.Person;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;

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
    private TableColumn<Person, Image> imageColumn;

    @FXML
    private ImageView red1;
    @FXML
    private ImageView blue1;
    @FXML
    private ImageView yellow1;
    @FXML
    private ImageView excl11;
    @FXML
    private ImageView excl21;
    @FXML
    private ImageView excl31;
    
    
    @FXML
    private ImageView red2;
    @FXML
    private ImageView blue2;
    @FXML
    private ImageView yellow2;
    @FXML
    private ImageView excl12;
    @FXML
    private ImageView excl22;
    @FXML
    private ImageView excl32;
    @FXML
    private Label chronoMinute;
    
    @FXML
    private Label chronoSeconde;
    
    @FXML
    private Circle cir_ter; 
    @FXML
    private Circle cir_cage1; 
    @FXML
    private Circle cir_cage2; 
    
    @FXML
    private ImageView ter;
    @FXML
    private HBox cage1;
    @FXML
    private HBox cage2;
    
    
    long min, sec = 0;
    
    boolean chronoPause = true;
    boolean chronoIsRunning = false;
    
    
    public RealTimeController() {
    }
    
	ScheduledService<Void> t = new ScheduledService<Void>() {	
		protected Task<Void> createTask() {
			return new Task<Void>(){
	            protected Void call() throws Exception{
	            	if(chronoPause == false) {
	 				   sec=sec+1;
	 				   if(sec == 60) {
	 					   min++;
	 					   sec = 0;
	 				   }
	 			   }
	            	Platform.runLater(new Runnable() {
	            		public void run () {
	            			if(min < 10) {
	            				chronoMinute.setText("0".concat(Long.toString(min)));
	            			}else {
	            				chronoMinute.setText(Long.toString(min));
	            			}
	            			
	            			if(sec < 10) {
	            				 chronoSeconde.setText("0".concat(Long.toString(sec)));
	            			}else {
	 	     	 			   chronoSeconde.setText(Long.toString(sec));
	            			}

	            		}
	            	});
	 			   return null;
	            }
			};

	   }
	};
    
    @FXML
    private void initialize() {
    	
    	
    	/*
    	cir_ter.setOnDragDone(dragEvent -> { 
    	    final Dragboard dragBroard = dragEvent.getDragboard(); 
    	   
    	    if (dragEvent.getTransferMode() == TransferMode.MOVE) { 
    	        addBut(1);
    	    	
    	        
    	    } 
    	    dragEvent.consume(); 
    	});*/
    	
    	
    	
    	
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
        
        /*imageColumn.setCellValueFactory(
        		new PropertyValueFactory<Person, Image>("../image/Carton_jaune.jpg"));*/
 
        
        setMainApp();
        
        t.setPeriod(Duration.seconds(1));

        t.start();

        //Clear person details.
        //showPersonDetails(null, 0);

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
        		   if(person.getYellow() == 1) {
   	            	yellow1.setVisible(true);
   	            }else {
   	            	yellow1.setVisible(false);
   	            }
   	            if(person.getRed() == 1) {
   	            	red1.setVisible(true);
   	            }else {
   	            	red1.setVisible(false);
   	            }
   	            if(person.hasReport()) {
   	            	blue1.setVisible(true);
   	            }else {
   	            	blue1.setVisible(false);
   	            }
        		
   	            switch(person.getNbExcl()) {
	   	            case 0:
	   	            	excl11.setVisible(false);
	   	            	excl21.setVisible(false);
	   	            	excl31.setVisible(false);
	   	            break;
	   	            case 1:
	   	            	excl11.setVisible(true);
	   	            	excl21.setVisible(false);
	   	            	excl31.setVisible(false);
	   	            break;
	   	            case 2:
	   	            	excl11.setVisible(true);
	   	            	excl21.setVisible(true);
	   	            	excl31.setVisible(false);
	   	            break;
	   	            case 3:
	   	            	excl11.setVisible(true);
	   	            	excl21.setVisible(true);
	   	            	excl31.setVisible(true);
	   	            break;
   	            }
        	}else {
        		if(person.getYellow() == 1) {
   	            	yellow2.setVisible(true);
   	            }else {
   	            	yellow2.setVisible(false);
   	            }
   	            if(person.getRed() == 1) {
   	            	red2.setVisible(true);
   	            }else {
   	            	red2.setVisible(false);
   	            }
   	            if(person.hasReport()) {
   	            	blue2.setVisible(true);
   	            }else {
   	            	blue2.setVisible(false);
   	            }
        		
   	            switch(person.getNbExcl()) {
	   	            case 0:
	   	            	excl12.setVisible(false);
	   	            	excl22.setVisible(false);
	   	            	excl32.setVisible(false);
	   	            break;
	   	            case 1:
	   	            	excl12.setVisible(true);
	   	            	excl22.setVisible(false);
	   	            	excl32.setVisible(false);
	   	            break;
	   	            case 2:
	   	            	excl12.setVisible(true);
	   	            	excl22.setVisible(true);
	   	            	excl32.setVisible(false);
	   	            break;
	   	            case 3:
	   	            	excl12.setVisible(true);
	   	            	excl22.setVisible(true);
	   	            	excl32.setVisible(true);
	   	            break;
   	            }
        	}
	         

        } else {
            yellow1.setVisible(false);
            blue1.setVisible(false);
            red1.setVisible(false);
            excl11.setVisible(false);
            excl21.setVisible(false);
            excl31.setVisible(false);
            yellow2.setVisible(false);
            blue2.setVisible(false);
            red2.setVisible(false);
            excl12.setVisible(false);
            excl22.setVisible(false);
            excl32.setVisible(false);
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
           alert.setTitle("Aucune personne n'a ete selectionnee");
           alert.setHeaderText("Aucune persone n'est selectionnee");
           alert.setContentText("Merci de bien vouloir selectionner une persone.");

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
           alert.setTitle("Aucune personne n'a ete selectionnee");
           alert.setHeaderText("Aucune persone n'est selectionnee");
           alert.setContentText("Merci de bien vouloir selectionner une persone.");

           alert.showAndWait();
	   }
   }
   
public void addRed(TableView<Person> tabPers, int nb) {
	   
	   Person selectedPerson = tabPers.getSelectionModel().getSelectedItem();
	   if(selectedPerson != null) {
		   selectedPerson.addRed();
		   showPersonDetails(selectedPerson, nb);
	   }else {
		// Nothing selected.
           Alert alert = new Alert(AlertType.WARNING);
           alert.initOwner(Main.getPrimaryStage());
           alert.setTitle("Aucune personne n'a ete selectionnee");
           alert.setHeaderText("Aucune persone n'est selectionnee");
           alert.setContentText("Merci de bien vouloir selectionner une persone.");

           alert.showAndWait();
	   }

   }

public void addBlue(TableView<Person> tabPers, int nb) {
	   
	   Person selectedPerson = tabPers.getSelectionModel().getSelectedItem();
	   if(selectedPerson != null) {
		   selectedPerson.addBlue();
		   showPersonDetails(selectedPerson, nb);
	   }else {
		// Nothing selected.
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(Main.getPrimaryStage());
        alert.setTitle("Aucune personne n'a ete selectionnee");
        alert.setHeaderText("Aucune persone n'est selectionnee");
        alert.setContentText("Merci de bien vouloir selectionner une persone.");

        alert.showAndWait();
	   }

}

public void addShoot(TableView<Person> tabPers, int nb) {
	Person selectedPerson = tabPers.getSelectionModel().getSelectedItem();
	   if(selectedPerson != null) {
		   cir_ter.setVisible(true);
		   cir_ter.setDisable(false);
		   cir_ter.setLayoutX(625);
		   cir_ter.setLayoutY(625);
		   
		   cir_ter.setOnDragDetected(mouseEvent -> {
	    		final Dragboard dragBroard = cir_ter.startDragAndDrop(TransferMode.ANY);
	    		final ClipboardContent content = new ClipboardContent();
	    		final WritableImage capture = cir_ter.snapshot(null, null);
	    		content.putImage(capture);
	            dragBroard.setContent(content); 
	            mouseEvent.consume();
	    		
	    		
	    	});
	    	
	    	ter.setOnDragOver(dragEvent -> { 
	    	    final Dragboard dragBroard = dragEvent.getDragboard(); 
	    	   
	    	    if (dragEvent.getGestureSource() != ter) { 
	    	        // Indique les modes de transfert autoris�s sur cette destination. 
	    	        dragEvent.acceptTransferModes(TransferMode.ANY); 
	    	    } 
	    	    dragEvent.consume(); 
	    	});
	    	
	    	ter.setOnDragDropped(dragEvent -> { 
	    	    boolean success = false; 
	    	    try { 
	    	        final Dragboard dragBroard = dragEvent.getDragboard(); 
	    	        Point pointerLocation = MouseInfo.getPointerInfo().getLocation(); 

	    	        int sceneX = pointerLocation.x;
	    	        sceneX -= Main.getScene().getWindow().getX();
	    	        sceneX -= Main.getScene().getX();
	    	        

	    	        int sceneY = pointerLocation.y; 
	    	        sceneY -= Main.getScene().getWindow().getY();
	    	        sceneY -= Main.getScene().getY();
	    	        sceneY -= 26;
	    	        
	    	        cir_ter.setLayoutX(sceneX);
	    	        cir_ter.setLayoutY(sceneY);
	    	        
	    	        
	    	        success = true; 
	    	    } catch (Exception ex) { 
	    	       
	    	    } finally { 
	    	        dragEvent.setDropCompleted(success); 
	    	        dragEvent.consume(); 
	    	    } 
	    	});
		   
		   cir_ter.setOnDragDone(dragEvent -> { 
	    	    final Dragboard dragBroard = dragEvent.getDragboard(); 
	    	   
	    	    if (dragEvent.getTransferMode() == TransferMode.MOVE) { 
	    	        
	    	    	
	    	        
	    	    }
	    	  
	    	    dragEvent.consume();
	    	    cir_ter.setDisable(true);
	    	    addBut(nb);
	    	});
		   
		   
	   }else {
		// Nothing selected.
     Alert alert = new Alert(AlertType.WARNING);
     alert.initOwner(Main.getPrimaryStage());
     alert.setTitle("Aucune personne n'a ete selectionnee");
     alert.setHeaderText("Aucune persone n'est selectionnee");
     alert.setContentText("Merci de bien vouloir selectionner une persone.");

     alert.showAndWait();
	   }
	
}

public void addBut(int nb) {
	if(nb==1) {
		cir_cage1.setVisible(true);
		 cir_cage1.setOnDragDetected(mouseEvent -> {
	    		final Dragboard dragBroard = cir_cage1.startDragAndDrop(TransferMode.ANY);
	    		final ClipboardContent content = new ClipboardContent();
	    		final WritableImage capture = cir_cage1.snapshot(null, null);
	    		content.putImage(capture);
	            dragBroard.setContent(content); 
	            mouseEvent.consume();
	    		
	    		
	    	});
	    	
	    	cage1.setOnDragOver(dragEvent -> { 
	    	    final Dragboard dragBroard = dragEvent.getDragboard(); 
	    	   
	    	    if (dragEvent.getGestureSource() != cage1) { 
	    	        // Indique les modes de transfert autoris�s sur cette destination. 
	    	        dragEvent.acceptTransferModes(TransferMode.ANY); 
	    	    } 
	    	    dragEvent.consume(); 
	    	});
	    	
	    	cage1.setOnDragDropped(dragEvent -> { 
	    	    boolean success = false; 
	    	    try { 
	    	        final Dragboard dragBroard = dragEvent.getDragboard(); 
	    	        Point pointerLocation = MouseInfo.getPointerInfo().getLocation(); 

	    	        int sceneX = pointerLocation.x;
	    	        sceneX -= Main.getScene().getWindow().getX();
	    	        sceneX -= Main.getScene().getX();
	    	        

	    	        int sceneY = pointerLocation.y; 
	    	        sceneY -= Main.getScene().getWindow().getY();
	    	        sceneY -= Main.getScene().getY();
	    	        sceneY -= 26;
	    	        
	    	        cir_cage1.setLayoutX(sceneX);
	    	        cir_cage1.setLayoutY(sceneY);
	    	        
	    	        
	    	        success = true; 
	    	    } catch (Exception ex) { 
	    	       
	    	    } finally { 
	    	        dragEvent.setDropCompleted(success); 
	    	        dragEvent.consume(); 
	    	    } 
	    	});
		   
		   cir_cage1.setOnDragDone(dragEvent -> { 
	    	    final Dragboard dragBroard = dragEvent.getDragboard(); 
	    	   
	    	    if (dragEvent.getTransferMode() == TransferMode.MOVE) { 
 
	    	    }
	    	    
	    	    Alert dialogC= new Alert(AlertType.CONFIRMATION);
	    	    dialogC.setTitle("But ou pas ?");
	    	    dialogC.setHeaderText(null);
	    	    dialogC.setContentText("Pavard");
	    	    ButtonType btnBut= new ButtonType("But");
	    	    ButtonType btnPasBut= new ButtonType("Pas but");
	    	    dialogC.getButtonTypes().setAll(btnBut, btnPasBut);
	    	    Optional<ButtonType> answer= dialogC.showAndWait();
	    	    if(answer.get() == btnBut){
	    	    	System.out.println("BBUUUT");
	    	    }
	    	    else{
	    	    	System.out.println("PPAASS BUUUT");
	    	    }
	    	    cir_ter.setVisible(false);
	    	    cir_cage1.setVisible(false);
	    	    
	    	    
	    	  
	    	    dragEvent.consume(); 
	    	});
	}else {
		cir_cage2.setVisible(true);
		 cir_cage2.setOnDragDetected(mouseEvent -> {
	    		final Dragboard dragBroard = cir_cage2.startDragAndDrop(TransferMode.ANY);
	    		final ClipboardContent content = new ClipboardContent();
	    		final WritableImage capture = cir_cage2.snapshot(null, null);
	    		content.putImage(capture);
	            dragBroard.setContent(content); 
	            mouseEvent.consume();
	    		
	    		
	    	});
	    	
	    	cage2.setOnDragOver(dragEvent -> { 
	    	    final Dragboard dragBroard = dragEvent.getDragboard(); 
	    	   
	    	    if (dragEvent.getGestureSource() != cage2) { 
	    	        // Indique les modes de transfert autoris�s sur cette destination. 
	    	        dragEvent.acceptTransferModes(TransferMode.ANY); 
	    	    } 
	    	    dragEvent.consume(); 
	    	});
	    	
	    	cage2.setOnDragDropped(dragEvent -> { 
	    	    boolean success = false; 
	    	    try { 
	    	        final Dragboard dragBroard = dragEvent.getDragboard(); 
	    	        Point pointerLocation = MouseInfo.getPointerInfo().getLocation(); 

	    	        int sceneX = pointerLocation.x;
	    	        sceneX -= Main.getScene().getWindow().getX();
	    	        sceneX -= Main.getScene().getX();
	    	        

	    	        int sceneY = pointerLocation.y; 
	    	        sceneY -= Main.getScene().getWindow().getY();
	    	        sceneY -= Main.getScene().getY();
	    	        sceneY -= 26;
	    	        
	    	        cir_cage2.setLayoutX(sceneX);
	    	        cir_cage2.setLayoutY(sceneY);
	    	        
	    	        
	    	        success = true; 
	    	    } catch (Exception ex) { 
	    	       
	    	    } finally { 
	    	        dragEvent.setDropCompleted(success); 
	    	        dragEvent.consume(); 
	    	    } 
	    	});
		   
		   cir_cage2.setOnDragDone(dragEvent -> { 
	    	    final Dragboard dragBroard = dragEvent.getDragboard(); 
	    	   
	    	    if (dragEvent.getTransferMode() == TransferMode.MOVE) { 
	    	        
	    	    	
	    	        
	    	    }
	    	    Alert dialogC= new Alert(AlertType.CONFIRMATION);
	    	    dialogC.setTitle("But ou pas ?");
	    	    dialogC.setHeaderText(null);
	    	    dialogC.setContentText("Pavard");
	    	    ButtonType btnBut= new ButtonType("But");
	    	    ButtonType btnPasBut= new ButtonType("Pas but");
	    	    dialogC.getButtonTypes().setAll(btnBut, btnPasBut);
	    	    Optional<ButtonType> answer= dialogC.showAndWait();
	    	    if(answer.get() == btnBut){
	    	    	System.out.println("BBUUUT");
	    	    }
	    	    else{
	    	    	System.out.println("PPAASS BUUUT");
	    	    }
	    	    cir_ter.setVisible(false);
	    	    cir_cage2.setVisible(false);
	    	  
	    	    dragEvent.consume(); 
	    	});
		
	}
}

@FXML
public void addShoot1() {
	addShoot(personTable1, 1);
}

@FXML
public void addShoot2() {
	addShoot(personTable2, 2);
}

	@FXML
	public void addRed1() {
		addRed(personTable1, 1);
	}
	
	@FXML
	public void addRed2() {
		addRed(personTable2, 2);
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
   public void demarrerChrono() {
	 
	  if(!chronoIsRunning) {
		  min = 0;
		  sec = 0;
		  chronoPause = false;
		  chronoIsRunning = true;
		   
	  }
	 
   }
   
   @FXML 
   public void pauseChrono() {
	   chronoPause = true; 
   }
   
   @FXML 
   public void repprendreChrono() {
	   chronoPause = false;
   }
   
   
   
   
   

   @FXML
   public void addExcl1() {
	   addExcl(personTable1, 1);
   }
   
   @FXML
   public void addExcl2() {
	   addExcl(personTable2, 2);
   }
   
   @FXML
   public void addBlue1() {
	   addBlue(personTable1, 1);
   }
   
   @FXML
   public void addBlue2() {
	   addBlue(personTable2, 2);
   }


}
