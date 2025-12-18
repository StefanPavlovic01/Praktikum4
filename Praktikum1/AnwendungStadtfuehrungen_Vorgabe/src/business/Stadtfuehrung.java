package business;
//Brunnen,44,Brunnenbesich-tigung,16,20251231,20260101
public class Stadtfuehrung {
	
    private String titel;
    private int identnummer;
    private String kurzbeschreibung;
    private float startuhrzeit;
    private String[] daten;
    
    public Stadtfuehrung(String titel, int identnummer, String kurzbeschreibung,
       	float startuhrzeit, String[] daten){
    	if(daten==null) {
    		throw new IllegalArgumentException();
    	}
    	this.titel = titel;
      	this.identnummer = identnummer;
       	this.kurzbeschreibung = kurzbeschreibung;
       	this.startuhrzeit = startuhrzeit;
       	this.daten = daten;
    }

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public int getIdentnummer() {
		return identnummer;
	}

	public void setIdentnummer(int identnummer) {
		this.identnummer = identnummer;
	}

	public String getKurzbeschreibung() {
		return kurzbeschreibung;
	}

	public void setKurzbeschreibung(String kurzbeschreibung) {
		this.kurzbeschreibung = kurzbeschreibung;
	}

	public float getStartuhrzeit() {
		return startuhrzeit;
	}

	public void setStartuhrzeit(float startuhrzeit) {
		this.startuhrzeit = startuhrzeit;
	}

	public String[] getDaten() {
		return daten;
	}

	public void setDaten(String[] daten) {
		this.daten = daten;
	}
	
 	public String getDatenAlsString(char trenner) {
		String ergebnis = "";
		int i = 0;
		for(i = 0; i < this.getDaten().length - 1; i++) {
			ergebnis = ergebnis + this.getDaten()[i] + trenner; 
		}
		return ergebnis	+ this.getDaten()[i];
	}
	
	public String gibStadtfuehrungZurueck(char trenner){
  		return this.getTitel() + trenner 
  			+ this.getIdentnummer() + trenner
  			+ this.getKurzbeschreibung() + trenner
  		    + this.getStartuhrzeit() + trenner + "\n"
  		    + this.getDatenAlsString(trenner) + "\n";
  	}
}

