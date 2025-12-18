package fileCreatorPavlovic;

import java.io.IOException;

public abstract class ReaderProductPavlovic {

	public abstract String[] leseAusDatei() throws IOException;
	public abstract void schließeDatei() throws IOException;
}
