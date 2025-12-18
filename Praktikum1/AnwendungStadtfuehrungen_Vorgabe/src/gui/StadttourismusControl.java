package gui;

import business.StadtfuerhungModel;
import guiStadtfuerhung.StadtfuerhungView;
import javafx.stage.Stage;
import ownUtil.StadtfuerhungControl;






public class StadttourismusControl {	
	
	private StadttourismusView stadttourismusView;
	private StadtfuerhungModel stadtfuehrungenModel;
	
	
	
	
	public StadttourismusControl(Stage primaryStage){
 		this.stadtfuehrungenModel = StadtfuerhungModel.getInstance(); 
		this.stadttourismusView=new StadttourismusView(this, primaryStage, stadtfuehrungenModel);
	}
	
	
	
}
