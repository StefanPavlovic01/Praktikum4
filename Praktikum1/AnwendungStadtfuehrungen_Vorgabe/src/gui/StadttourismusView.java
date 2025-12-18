package gui;
   

import business.Stadtfuehrung;
import business.StadtfuerhungModel;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class StadttourismusView implements Observer{
	
private StadttourismusControl stadttourismusControl;
private StadtfuerhungModel stadtfuehrungenModel;	

    	//---Anfang Attribute der grafischen Oberflaeche---
    	private Pane pane = new  Pane();
    	private Label lblAnzeigeStadtfuehrungen     
 		= new Label("Anzeige Stadtfuehrungen");
    	private TextArea txtAnzeigeStadtfuehrungen  = new TextArea();
    	private Button btnAnzeigeStadtfuehrungen = new Button("Anzeige");
    	//-------Ende Attribute der grafischen Oberflaeche-------
    
    	public StadttourismusView(StadttourismusControl stadttourismusControl, Stage primaryStage, StadtfuerhungModel stadtfuehrungenModel){
    		Scene scene = new Scene(this.pane, 560, 340);
    		primaryStage.setScene(scene);
    		primaryStage.setTitle("Anzeige von stadttouristischen " 
 			+ "Angeboten");
    		primaryStage.show();
    		this.stadttourismusControl = stadttourismusControl;
 		this.stadtfuehrungenModel = stadtfuehrungenModel;
 		this.initKomponenten();
		this.initListener();
		stadtfuehrungenModel.addObserver(this);
    	}

 

	private void initKomponenten(){
    		// Label
 		Font font = new Font("Arial", 20);
       	lblAnzeigeStadtfuehrungen.setLayoutX(310);
    		lblAnzeigeStadtfuehrungen.setLayoutY(40);
    		lblAnzeigeStadtfuehrungen.setFont(font);
    		lblAnzeigeStadtfuehrungen.setStyle(
 			"-fx-font-weight: bold;"); 
       	pane.getChildren().add(lblAnzeigeStadtfuehrungen);           
// Textbereich	
        	txtAnzeigeStadtfuehrungen.setEditable(false);
     		txtAnzeigeStadtfuehrungen.setLayoutX(310);
    		txtAnzeigeStadtfuehrungen.setLayoutY(90);
     		txtAnzeigeStadtfuehrungen.setPrefWidth(220);
    		txtAnzeigeStadtfuehrungen.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeigeStadtfuehrungen);        	
        	// Button
          	btnAnzeigeStadtfuehrungen.setLayoutX(310);
        	btnAnzeigeStadtfuehrungen.setLayoutY(290);
        	pane.getChildren().add(btnAnzeigeStadtfuehrungen); 
   }
   
   private void initListener() {
	    btnAnzeigeStadtfuehrungen.setOnAction(
 			new EventHandler<ActionEvent>() {
	    		@Override
	        	public void handle(ActionEvent e) {
	            	zeigeStadtfuehrungenAn();
	        	} 
   	    });
    }
   
    public void zeigeStadtfuehrungenAn(){
    		if(stadtfuehrungenModel.getSfuerhung().size()>0) {
    			StringBuffer text=new StringBuffer();
    			for(Stadtfuehrung s:stadtfuehrungenModel.getSfuerhung()) {
    				text.append(s.gibStadtfuehrungZurueck(' '));
    			}
    			this.txtAnzeigeStadtfuehrungen.setText(text.toString());
    		}
    		else{
    			zeigeInformationsfensterAn(
 				"Bisher wurde keine Stadtführung aufgenommen!");
    		}
    }	
   
    private void zeigeInformationsfensterAn(String meldung){
    	  	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
               	"Information", meldung).zeigeMeldungsfensterAn();
    }

	@Override
	public void update() {
		zeigeStadtfuehrungenAn();
		
	}	
    
}




