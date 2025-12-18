package main;

import ownUtil.*;
import gui.StadttourismusControl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		new StadtfuerhungControl(primaryStage);
		Stage fenster=new Stage();
		new StadttourismusControl(fenster);
	}	
	
	public static void main(String[] args){
		launch(args);
	}
}
//view observer