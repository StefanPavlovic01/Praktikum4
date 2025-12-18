package business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KonstruktorTest {

	@Test
	void test() {
		Stadtfuehrung s=new Stadtfuehrung("Brunnen", 44, "Brunnenbesichtigung", 20251231f, new String[] {"20260101"});
		assertTrue(()->s.getTitel().equals("Brunnen"));
		Throwable exc = assertThrows(IllegalArgumentException.class, () -> {new Stadtfuehrung("Brunnen", 44, "Brunnenbesichtung", 2025121f, null);});
		//kommentar
		//neuer Kommentar
		
	}

}
