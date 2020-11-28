package projectRefs.projectRefs;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import projectRefs.projectRefs.PowerPointGenerator;
import projectRefs.projectRefs.Reference;
import projectRefs.projectRefs.ReferenceValidatorAndCreator;


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
