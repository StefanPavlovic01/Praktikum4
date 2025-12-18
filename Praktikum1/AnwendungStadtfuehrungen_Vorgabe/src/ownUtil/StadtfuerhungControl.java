package ownUtil;


import guiStadtfuerhung.*;
import java.io.IOException;

import business.StadtfuerhungModel;

import javafx.stage.Stage;

public class StadtfuerhungControl {

	
	
	private StadtfuerhungView sView;
	private StadtfuerhungModel sModel;
	
	
	public StadtfuerhungControl(Stage primaryStage) {
		sModel=StadtfuerhungModel.getInstance();
		sView=new StadtfuerhungView(primaryStage,this,sModel);
		
	}
	
	public void leseAusDatei(String typ)  {
		try {
			if(typ.equals("csv")) {
			sModel.leseAusDateiCSV();
			}else if(typ.equals("txt")){
				sModel.leseAusDateiTxt();
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void schreibeStadtfuehrungenInCsvDatei() {
		try {
			sModel.schreibeStadtfuehrungenInCsvDatei();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
				
	
	
	

   
   
   

