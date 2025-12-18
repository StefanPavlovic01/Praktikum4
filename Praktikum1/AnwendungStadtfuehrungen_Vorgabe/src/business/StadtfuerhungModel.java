package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import fileCreatorPavlovic.ConcreteCreatorA;
import fileCreatorPavlovic.ConcreteCreatorB;
import fileCreatorPavlovic.ReaderCreatorPavlovic;
import fileCreatorPavlovic.ReaderProductPavlovic;
import ownUtil.Observable;
import ownUtil.Observer;



public class StadtfuerhungModel implements Observable{

	
	private ArrayList<Stadtfuehrung> sfuerhung=new ArrayList<>();
	private static StadtfuerhungModel instanz;
	private List<Observer>observer=new Vector<>();
	
	private StadtfuerhungModel() {
		
	}
	public static StadtfuerhungModel getInstance() {
		if(instanz==null) {
			instanz=new StadtfuerhungModel();
		}
		return instanz;
	}
	
	 public ArrayList<Stadtfuehrung> getSfuerhung() {
		
		return (ArrayList<Stadtfuehrung>) sfuerhung;
	}


	public void addSfuerhung(Stadtfuehrung s) {
		sfuerhung.add(s);
		
		notifyObserver();
	}


	/*public void leseAusDatei(String typ) throws IOException{
	    	
	      		if("csv".equals(typ)){
	      			BufferedReader ein = new BufferedReader(new FileReader("Stadtfuehrung.csv"));
	      			String[] zeile = ein.readLine().split(";");
	      			sfuerhung= new Stadtfuehrung(zeile[0], 
	      				Integer.parseInt(zeile[1]), 
	      				zeile[2], 
	      				Float.parseFloat(zeile[3]), 
	      				zeile[4].split("_"));
	      				ein.close();
	      		
	    	
	 }
	 }*/
	
	public void leseAusDateiCSV() throws IOException{
    	
  		
  			ReaderCreatorPavlovic rc=new ConcreteCreatorA();
  			ReaderProductPavlovic rp=rc.factoryMethod();
  			rp.leseAusDatei();
  			rp.schließeDatei();
  		
	

}
	
public void leseAusDateiTxt() throws IOException{
    	
  		
  			ReaderCreatorPavlovic rc=new ConcreteCreatorB();
  			ReaderProductPavlovic rp=rc.factoryMethod();
  			rp.leseAusDatei(); //nurso
  			rp.schließeDatei();
  		
	

}


	
	 public void schreibeStadtfuehrungenInCsvDatei() throws IOException {
			
				BufferedWriter aus 
					= new BufferedWriter(new FileWriter("StadtfuehrungenAusgabe.csv", true));
				for(Stadtfuehrung s:sfuerhung) {
				aus.write(s.gibStadtfuehrungZurueck(';'));
				}
				aus.close();
			
	 }
	 
	 public String gibStadtfuehrungZurueck(char trenner){
		 StringBuilder sb=new StringBuilder();
		 for(Stadtfuehrung s:sfuerhung) {
			 sb.append(s.gibStadtfuehrungZurueck(trenner)).append("\n");
		 }
		 return sb.toString();
	 }
	 
	@Override
	public void addObserver(Observer obs) {
		observer.add(obs);
		
	}
	@Override
	public void removeObserver(Observer obs) {
	observer.remove(obs);
		
	}
	@Override
	public void notifyObserver() {
		for(Observer o:observer) {
			o.update();
		}
		
	}
}

