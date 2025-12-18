package fileCreatorPavlovic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import business.Stadtfuehrung;
import business.StadtfuerhungModel;

public class ConcreteProductB extends ReaderProductPavlovic{

	BufferedReader ein;
	
	public ConcreteProductB() throws FileNotFoundException {
		ein=new BufferedReader(new FileReader("Stadtfuehrung.txt"));
		
	}
	@Override
	public String[] leseAusDatei() throws IOException {
		String[] zeile = ein.readLine().split(";");
		Stadtfuehrung sfuerhung= new Stadtfuehrung(zeile[0], 
  				Integer.parseInt(zeile[1]), 
  				zeile[2], 
  				Float.parseFloat(zeile[3]), 
  				zeile[4].split("_"));
  				
  				StadtfuerhungModel.getInstance().addSfuerhung(sfuerhung);
				return zeile;
		
	}

	@Override
	public void schließeDatei() throws IOException {
		ein.close();
		
	}

}
