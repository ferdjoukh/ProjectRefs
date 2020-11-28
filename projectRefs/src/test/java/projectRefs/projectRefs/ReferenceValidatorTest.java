package projectRefs.projectRefs;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import projectRefs.projectRefs.Reference;
import projectRefs.projectRefs.ReferenceValidatorAndCreator;


public class ReferenceValidatorTest extends TestCase {
	
	
	@Test
	public void missingFile() {
		ReferenceValidatorAndCreator validator = new ReferenceValidatorAndCreator("resources/wrongFile.ini");
		assertFalse(validator.validate().isValid());
		assertEquals("Reference File does not exist", validator.validate().getIssues().get(0));
		assertEquals(1, validator.validate().getIssues().size());
	}
	
	@Test
	public void missingTitle() {
		ReferenceValidatorAndCreator validator = new ReferenceValidatorAndCreator("resources/missingTitle.ini");
		assertFalse(validator.validate().isValid());
		assertEquals("Title is missing", validator.validate().getIssues().get(0));
		assertEquals(1, validator.validate().getIssues().size());
	}
	
	@Test
	public void missingImage() {
		ReferenceValidatorAndCreator validator = new ReferenceValidatorAndCreator("resources/missingImage.ini");
		assertFalse(validator.validate().isValid());
		assertEquals("Image is missing", validator.validate().getIssues().get(0));
		assertEquals(1, validator.validate().getIssues().size());
	}
	
	@Test
	public void missingClient() {
		ReferenceValidatorAndCreator validator = new ReferenceValidatorAndCreator("resources/missingClient.ini");
		assertFalse(validator.validate().isValid());
		assertEquals("Client is missing", validator.validate().getIssues().get(0));
		assertEquals(1, validator.validate().getIssues().size());
	}
	
	@Test
	public void missingImageFile() {
		ReferenceValidatorAndCreator validator = new ReferenceValidatorAndCreator("resources/missingImageFile.ini");
		assertFalse(validator.validate().isValid());
		assertEquals("Image File does not exist", validator.validate().getIssues().get(0));
		assertEquals(1, validator.validate().getIssues().size());
	}
	
	@Test
	public void missingContext() {
		ReferenceValidatorAndCreator validator = new ReferenceValidatorAndCreator("resources/missingContext.ini");
		assertFalse(validator.validate().isValid());
		assertEquals("CONTEXT section is missing", validator.validate().getIssues().get(0));
		assertEquals(1, validator.validate().getIssues().size());
	}
	
	@Test
	public void missingObjectives() {
		ReferenceValidatorAndCreator validator = new ReferenceValidatorAndCreator("resources/missingObjectives.ini");
		assertFalse(validator.validate().isValid());
		assertEquals("OBJECTIVES section is missing", validator.validate().getIssues().get(0));
		assertEquals(1, validator.validate().getIssues().size());
	}
	
	@Test
	public void missingSolution() {
		ReferenceValidatorAndCreator validator = new ReferenceValidatorAndCreator("resources/missingSolution.ini");
		assertFalse(validator.validate().isValid());
		assertEquals("SOLUTION section is missing", validator.validate().getIssues().get(0));
		assertEquals(1, validator.validate().getIssues().size());
	}
	
	@Test
	public void missingResults() {
		ReferenceValidatorAndCreator validator = new ReferenceValidatorAndCreator("resources/missingResults.ini");
		assertFalse(validator.validate().isValid());
		assertEquals("RESULTS section is missing", validator.validate().getIssues().get(0));
		assertEquals(1, validator.validate().getIssues().size());
	}
	
	@Test
	public void createMissingResults() {
		ReferenceValidatorAndCreator validator = new ReferenceValidatorAndCreator("resources/missingResults.ini");
		assertNull(validator.createReference());
	}
	
	@Test
	public void validRef1() {
		ReferenceValidatorAndCreator validator = new ReferenceValidatorAndCreator("resources/test1.ini");
		assertTrue(validator.validate().isValid());
		assertEquals(0, validator.validate().getIssues().size());
	}
	
	@Test
	public void createRef1() {
		ReferenceValidatorAndCreator validator = new ReferenceValidatorAndCreator("resources/test1.ini");
		Reference ref = validator.createReference();
		assertEquals(true, ref.isImageExist());
		assertNotNull(ref.getAuthor());
		assertNotNull(ref.getClient());
		assertNotNull(ref.getDuration());
		assertNotNull(ref.getFilePath());
		assertNotNull(ref.getImage());
		assertNotNull(ref.getLanguage());
		assertNotNull(ref.getManager());
		assertNotNull(ref.getPeople());
		assertNotNull(ref.getTeam());
		assertNotNull(ref.getTitle());
		assertNotNull(ref.getContext());
		assertNotNull(ref.getObjectives());
		assertNotNull(ref.getResults());
		assertNotNull(ref.getSolution());
	}

}
