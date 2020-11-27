package proejectRefs.proejectRefs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;


public class ReferenceParserTest {
	
	
	@Test
	public void testReadFile1() {
		ReferenceParser parser = new ReferenceParser("resources/test1.ini");
		parser.parse();
		assertEquals("PerfoRevolution : A Workbench for Performance Studies", parser.getTitle());
		assertEquals("Alice Bob", parser.getAuthor());		
		assertEquals(3, parser.getContext().length);
		assertEquals(4, parser.getResults().length);
		assertEquals(2, parser.getObjectives().length);
		assertNull(parser.getSectionList("no section"));
		assertEquals(3, parser.getSolution().size());
		assertEquals("Digital Engineering", parser.getTeam());
	}
	
	@Test
	public void imageExists1() {
		ReferenceParser parser = new ReferenceParser("resources/test1.ini");
		parser.parse();
		assertEquals(true, parser.imageExists());	
	}
	
	@Test
	public void imageExists2() {
		ReferenceParser parser = new ReferenceParser("resources/test2.ini");
		parser.parse();
		assertEquals(false, parser.imageExists());	
	}

}
