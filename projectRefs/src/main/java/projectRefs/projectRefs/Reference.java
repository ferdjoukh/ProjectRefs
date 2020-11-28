package projectRefs.projectRefs;

import java.util.Set;
import java.util.Map.Entry;

public class Reference {

	private String filePath;
	
	private String title;
	private String client;
	private String image;
	private boolean imageExist;
	
	private String manager;
	private String author;
	private String team;
	private String language;
	private String duration;
	private String people;
		
	private String [] context;
	private String [] objectives;
	private String [] results;
	private Set<Entry<String, String>> solution;
	
	public Reference(String title, String filePath, String client, String image, boolean exist) {
		this.title = title;
		this.filePath = filePath;
		this.client = client;
		this.image = image;
		this.imageExist = exist;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String[] getContext() {
		return context;
	}

	public void setContext(String[] context) {
		this.context = context;
	}

	public String[] getObjectives() {
		return objectives;
	}

	public void setObjectives(String[] objectives) {
		this.objectives = objectives;
	}

	public String[] getResults() {
		return results;
	}

	public void setResults(String[] results) {
		this.results = results;
	}

	public Set<Entry<String, String>> getSolution() {
		return solution;
	}

	public void setSolution(Set<Entry<String, String>> solution) {
		this.solution = solution;
	}

	public boolean isImageExist() {
		return imageExist;
	}

	public void setImageExist(boolean imageExist) {
		this.imageExist = imageExist;
	}
	
}
