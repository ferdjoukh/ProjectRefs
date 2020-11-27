package proejectRefs.proejectRefs;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;


public class PPTGeneratorTest extends TestCase {
	
	@Test
	public void createRef1() throws Exception {
		ReferenceValidatorAndCreator validator = new ReferenceValidatorAndCreator("resources/test1.ini");
		Reference ref = validator.createReference();
		
		String temp = "resources/reference-template.pptx";
		String out = "resources/test.pptx";
		
		PowerPointGenerator generator = new PowerPointGenerator(temp, ref, out);
		generator.generate();
		generator.save();
	}

}
