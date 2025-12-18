package fileCreatorPavlovic;

import java.io.FileNotFoundException;

public class ConcreteCreatorB extends ReaderCreatorPavlovic{

	@Override
	public ReaderProductPavlovic factoryMethod() throws FileNotFoundException {
		// TODO Auto-generated method stub
		return new ConcreteProductB();
	}

}
