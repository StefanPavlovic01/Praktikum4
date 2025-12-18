package fileCreatorPavlovic;

import java.io.FileNotFoundException;

public abstract class ReaderCreatorPavlovic {

	public abstract ReaderProductPavlovic factoryMethod() throws FileNotFoundException;
}
