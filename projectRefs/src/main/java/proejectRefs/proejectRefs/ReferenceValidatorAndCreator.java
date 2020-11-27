package proejectRefs.proejectRefs;

import java.io.File;
import java.util.ArrayList;

public class ReferenceValidatorAndCreator {

	private String filePath;
	private ReferenceParser parser;
	
	public ReferenceValidatorAndCreator(String filePath) {
		this.filePath = filePath;
		this.parser = new ReferenceParser(filePath);
	}
	
	public ValidationReport validate() {
		ValidationReport report = new ValidationReport();
		ArrayList<String> issues = new ArrayList<String>();
		
		File file = new File(filePath);
		
		if (!file.exists()) {
			return new ValidationReport(false, "Reference File does not exist");
		}
		
		parser.parse();
		
		System.out.println("Validation of Reference : " + filePath);
			
		if (parser.getTitle() == null) {
			String issue = "Title is missing";
			issues.add(issue);
		}
		
		if (parser.getClient() == null) {
			String issue = "Client is missing";
			issues.add(issue);
		}
		
		if (parser.getImage() == null) {
			String issue = "Image is missing";
			issues.add(issue);
		}else {
			if (!parser.imageExists()) {
				String issue = "Image File does not exist";
				issues.add(issue);
			}
		}
		
		if(parser.getContext() == null) {
			String issue = "CONTEXT section is missing";
			issues.add(issue);
		}
		
		if(parser.getSolution() == null) {
			String issue = "SOLUTION section is missing";
			issues.add(issue);
		}
		
		if(parser.getObjectives() == null) {
			String issue = "OBJECTIVES section is missing";
			issues.add(issue);
		}
		
		if(parser.getResults() == null) {
			String issue = "RESULTS section is missing";
			issues.add(issue);
		}
				
		if (issues.size() == 0) {
			report.setValid(true);
		}else {
			report.setValid(false);
			report.setIssues(issues);
		}
		
		return report;
	}
	
	public Reference createReference() {
		
		if(validate().isValid()) {
		
			Reference ref = new Reference(parser.getTitle(), filePath, parser.getClient(), parser.getImage(), parser.imageExists());
			
			ref.setAuthor(parser.getAuthor() == null ? null : parser.getDuration());
			ref.setDuration(parser.getDuration() == null ? null : parser.getDuration());
			ref.setLanguage(parser.getLanguage() == null ? null : parser.getLanguage());
			ref.setManager(parser.getManager() == null ? null : parser.getManager());
			ref.setPeople(parser.getPeople() == null ? null : parser.getPeople());
			ref.setTeam(parser.getTeam() == null ? null : parser.getTeam());
			
			ref.setContext(parser.getContext());
			ref.setSolution(parser.getSolution());
			ref.setResults(parser.getResults());
			ref.setObjectives(parser.getObjectives());
			
			return ref;
		
		}else {
			return null;
		}
	}
	
}
