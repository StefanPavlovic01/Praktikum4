package guiStadtfuerhung;
   
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import business.Stadtfuehrung;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class StadtfuehrungenAnwendungssystem {
	  
    //---Anfang Attribute der grafischen Oberflaeche---
    private Pane pane     					= new  Pane();
    private Label lblEingabe    	 		= new Label("Eingabe");
    private Label lblAnzeige   	 	    	= new Label("Anzeige");
    private Label lblTitel 					= new Label("Titel:");
    private Label lblIdentnummer   		 	= new Label("Identnummer:");
    private Label lblKurzbeschreibung  	 	= new Label("Kurzbeschreibung:");
    private Label lblStartuhrzeit   		= new Label("Startuhrzeit:");
    private Label lblDaten  		 		= new Label("Daten:");
    private TextField txtTitel 	 			= new TextField();
    private TextField txtIdentnummer		= new TextField();
    private TextField txtKurzbeschreibung	= new TextField();
    private TextField txtStartuhrzeit		= new TextField();
    private TextField txtDaten	 	 		= new TextField();
    private TextArea txtAnzeige  			= new TextArea();
    private Button btnEingabe 		 		= new Button("Eingabe");
    private Button btnAnzeige 		 		= new Button("Anzeige");
    private MenuBar mnbrMenuLeiste  		= new MenuBar();
    private Menu mnDatei             		= new Menu("Datei");
    private MenuItem mnItmCsvImport 		= new MenuItem("csv-Import");
    private MenuItem mnItmTxtImport 		= new MenuItem("txt-Import");
    private MenuItem mnItmCsvExport 		= new MenuItem("csv-Export");    
    //-------Ende Attribute der grafischen Oberflaeche-------
    
    // speichert temporaer ein Objekt vom Typ Stadtfuehrung
    private Stadtfuehrung stadtfuehrung;
    
    public StadtfuehrungenAnwendungssystem(Stage primaryStage){
    	Scene scene = new Scene(this.pane, 700, 340);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Verwaltung von Stadtfuehrungen");
    	primaryStage.show();
    	this.initKomponenten();
		this.initListener();
    }
    
    private void initKomponenten(){
       	// Labels
    	lblEingabe.setLayoutX(20);
    	lblEingabe.setLayoutY(40);
    	Font font = new Font("Arial", 24); 
    	lblEingabe.setFont(font);
    	lblEingabe.setStyle("-fx-font-weight: bold;"); 
    	lblAnzeige.setLayoutX(400);
    	lblAnzeige.setLayoutY(40);
      	lblAnzeige.setFont(font);
       	lblAnzeige.setStyle("-fx-font-weight: bold;"); 
       	lblTitel.setLayoutX(20);
    	lblTitel.setLayoutY(90);
    	lblIdentnummer.setLayoutX(20);
    	lblIdentnummer.setLayoutY(130);
    	lblKurzbeschreibung.setLayoutX(20);
    	lblKurzbeschreibung.setLayoutY(170);
    	lblStartuhrzeit.setLayoutX(20);
    	lblStartuhrzeit.setLayoutY(210);
    	lblDaten.setLayoutX(20);
    	lblDaten.setLayoutY(250);    	
       	pane.getChildren().addAll(lblEingabe, lblAnzeige, 
       		lblTitel, lblIdentnummer, lblKurzbeschreibung,
       		lblStartuhrzeit, lblDaten);
    
    	// Textfelder
     	txtTitel.setLayoutX(170);
    	txtTitel.setLayoutY(90);
    	txtTitel.setPrefWidth(200);
    	txtIdentnummer.setLayoutX(170);
    	txtIdentnummer.setLayoutY(130);
    	txtIdentnummer.setPrefWidth(200);
    	txtKurzbeschreibung.setLayoutX(170);
    	txtKurzbeschreibung.setLayoutY(170);
    	txtKurzbeschreibung.setPrefWidth(200);
      	txtStartuhrzeit.setLayoutX(170);
    	txtStartuhrzeit.setLayoutY(210);
    	txtStartuhrzeit.setPrefWidth(200);
    	txtDaten.setLayoutX(170);
    	txtDaten.setLayoutY(250);
    	txtDaten.setPrefWidth(200);
      	pane.getChildren().addAll( 
     		txtTitel, txtIdentnummer, txtKurzbeschreibung,
     		txtStartuhrzeit, txtDaten);
     	
        // Textbereich	
        txtAnzeige.setEditable(false);
     	txtAnzeige.setLayoutX(400);
    	txtAnzeige.setLayoutY(90);
     	txtAnzeige.setPrefWidth(270);
    	txtAnzeige.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeige); 
       	
        // Buttons
        btnEingabe.setLayoutX(20);
        btnEingabe.setLayoutY(290);
        btnAnzeige.setLayoutX(400);
        btnAnzeige.setLayoutY(290);
        pane.getChildren().addAll(btnEingabe, btnAnzeige); 
        
 		// Menue
  	    this.mnbrMenuLeiste.getMenus().add(mnDatei);
  	    this.mnDatei.getItems().add(mnItmCsvImport);
  	    this.mnDatei.getItems().add(mnItmTxtImport);
  	    this.mnDatei.getItems().add(new SeparatorMenuItem());
  	    this.mnDatei.getItems().add(mnItmCsvExport);
 	    pane.getChildren().add(mnbrMenuLeiste);
   }
   
   private void initListener() {
	    btnEingabe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
        	    nehmeStadtfuehrungAuf();
            }
	    });
	    btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	            zeigeStadtfuehrungenAn();
	        } 
   	    });
	    mnItmCsvImport.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	       	 	leseAusDatei("csv");
	    	}
	    });
	    mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		     	leseAusDatei("txt");
		    }
    	});
	    mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				schreibeStadtfuehrungenInCsvDatei();
			}	
	    });
    }
    
    private void nehmeStadtfuehrungAuf(){
    	try{ 
    		this.stadtfuehrung = new Stadtfuehrung(
    			txtTitel.getText(), 
   	            Integer.parseInt(txtIdentnummer.getText()),
   	            txtKurzbeschreibung.getText(),
   	            Float.parseFloat(txtStartuhrzeit.getText()),
    		    txtDaten.getText().split(";"));
    		zeigeInformationsfensterAn("Die Stadtfuehrung wurde aufgenommen!");
       	}
       	catch(Exception exc){
       		zeigeFehlermeldungsfensterAn(exc.getMessage());
     	}
    }
   
    private void zeigeStadtfuehrungenAn(){
    	if(this.stadtfuehrung != null){
    		txtAnzeige.setText(
    			this.stadtfuehrung.gibStadtfuehrungZurueck(' '));
    	}
    	else{
    		zeigeInformationsfensterAn("Bisher wurde keine Stadtfuehrung aufgenommen!");
    	}
    }    
		  
    private void leseAusDatei(String typ){
    	try {
      		if("csv".equals(typ)){
      			BufferedReader ein = new BufferedReader(new FileReader("Stadtfuehrung.csv"));
      			String[] zeile = ein.readLine().split(";");
      			this.stadtfuehrung = new Stadtfuehrung(zeile[0], 
      				Integer.parseInt(zeile[1]), 
      				zeile[2], 
      				Float.parseFloat(zeile[3]), 
      				zeile[4].split("_"));
      				ein.close();
      	  			zeigeInformationsfensterAn(
      	  	   			"Das Stadtfuehrung wurde gelesen!");
      		}
       		else{
	   			zeigeInformationsfensterAn(
	   				"Noch nicht implementiert!");
	   		}
		}
		catch(IOException exc){
			zeigeFehlermeldungsfensterAn(
				"IOException beim Lesen!");
		}
		catch(Exception exc){
			zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Lesen!");
		}
	}
		
	private void schreibeStadtfuehrungenInCsvDatei() {
		try {
			BufferedWriter aus 
				= new BufferedWriter(new FileWriter("StadtfuehrungenAusgabe.csv", true));
			aus.write(stadtfuehrung.gibStadtfuehrungZurueck(';'));
			aus.close();
   			zeigeInformationsfensterAn(
	   			"Die Stadtfuehrungen wurden gespeichert!");
		}	
		catch(IOException exc){
			zeigeFehlermeldungsfensterAn(
				"IOException beim Speichern!");
		}
		catch(Exception exc){
			zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Speichern!");
		}
	}

    private void zeigeInformationsfensterAn(String meldung){
    	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
    		"Information", meldung).zeigeMeldungsfensterAn();
    }	
    
    void zeigeFehlermeldungsfensterAn(String meldung){
       	new MeldungsfensterAnzeiger(AlertType.ERROR,
        	"Fehler", meldung).zeigeMeldungsfensterAn();
    }

}
