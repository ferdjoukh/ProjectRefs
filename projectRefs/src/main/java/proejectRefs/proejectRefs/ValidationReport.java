package proejectRefs.proejectRefs;

import java.util.ArrayList;

public class ValidationReport {
	
	private boolean valid;
	private ArrayList<String> issues = new ArrayList<String>();
	
	public ValidationReport() {
		
	}
	
	public ValidationReport(boolean status, String issue) {
		this.valid = status;
		addIssue(issue);
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public ArrayList<String> getIssues() {
		return issues;
	}

	public void setIssues(ArrayList<String> issues) {
		this.issues = issues;
	}
	
	public void addIssue(String issue) {
		issues.add(issue);
	}
	
	
	

}
