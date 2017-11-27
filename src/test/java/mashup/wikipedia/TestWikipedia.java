package mashup.wikipedia;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestWikipedia {

	@Test
	public void test() {
		Wikipedia wikipedia = new Wikipedia();
		String desc = wikipedia.getDescription("");
		assertEquals(desc, "");
	}

}
