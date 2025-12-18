package guiStadtfuerhung;

import business.Stadtfuehrung;
import business.StadtfuerhungModel;
import gui.StadttourismusControl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.MeldungsfensterAnzeiger;
import ownUtil.Observer;
import ownUtil.StadtfuerhungControl;

public class StadtfuerhungView implements Observer{

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
    
    private StadtfuerhungControl cfuerhung;
    private StadtfuerhungModel sModel;
    
    public StadtfuerhungView(Stage primaryStage, StadtfuerhungControl cfuerhung, StadtfuerhungModel sModel){
    	
    	this.sModel=sModel;
    	this.cfuerhung=cfuerhung;
    	Scene scene = new Scene(this.pane, 700, 340);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Verwaltung von Stadtfuehrungen");
    	primaryStage.show();
    	this.initKomponenten();
		this.initListener();
		sModel.addObserver(this);
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
	    btnEingabe.setOnAction(e->nehmeStadtfuehrungAuf());
	    
	    btnAnzeige.setOnAction(e->zeigeStadtfuehrungenAn());
	    
	    mnItmCsvImport.setOnAction(e->cfuerhung.leseAusDatei("csv"));
	    
	    mnItmTxtImport.setOnAction(e->cfuerhung.leseAusDatei("txt"));
	    
	    mnItmCsvExport.setOnAction(e->cfuerhung.schreibeStadtfuehrungenInCsvDatei());
   }
   
   private void nehmeStadtfuehrungAuf(){
   	try{ 
   		sModel.addSfuerhung( new Stadtfuehrung(
   			txtTitel.getText(), 
  	            Integer.parseInt(txtIdentnummer.getText()),
  	            txtKurzbeschreibung.getText(),
  	            Float.parseFloat(txtStartuhrzeit.getText()),
   		    txtDaten.getText().split(";")));
   		zeigeInformationsfensterAn("Die Stadtfuehrung wurde aufgenommen!");
      	}
      	catch(Exception exc){
      		zeigeFehlermeldungsfensterAn(exc.getMessage());
    	}
   }
   
   public void zeigeStadtfuehrungenAn(){
		if(sModel.getSfuerhung().size()>0) {
			StringBuffer text=new StringBuffer();
			for(Stadtfuehrung s:sModel.getSfuerhung()) {
				text.append(s.gibStadtfuehrungZurueck(' '));
			}
			this.txtAnzeige.setText(text.toString());
		}
		else{
			zeigeInformationsfensterAn(
				"Bisher wurde keine Stadtführung aufgenommen!");
		}
}	
		  
   public void zeigeInformationsfensterAn(String meldung){
   	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
   		"Information", meldung).zeigeMeldungsfensterAn();
   }	
   
   public void zeigeFehlermeldungsfensterAn(String meldung){
      	new MeldungsfensterAnzeiger(AlertType.ERROR,
       	"Fehler", meldung).zeigeMeldungsfensterAn();
   }







@Override
public void update() {
	zeigeStadtfuehrungenAn();
	
}

		
}
