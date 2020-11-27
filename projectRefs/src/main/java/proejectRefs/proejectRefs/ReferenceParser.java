package proejectRefs.proejectRefs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map.Entry;
import java.util.Set;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;


public class ReferenceParser {
	

	private Ini ini;
	private String filePath;
	private Path path;
	
	public ReferenceParser(String filePath) {
		this.filePath = filePath;
		
	}
	
	public void parse() {
		
		Ini ini = null;
		try {
			File file = new File(filePath);
			Path path = file.toPath();
			ini = new Ini(file);
			
			this.ini = ini;
			this.path = path;
			
		} catch (InvalidFileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getTitle() {
		return ini.get("header", "title");
	}
	
	public String getAuthor() {
		return ini.get("header", "author");
	}
	
	public String getClient() {
		return ini.get("header", "client");
	}
	
	public String getImage() {
		return ini.get("header", "image");
	}
	
	public boolean imageExists() {
		
		Path imagePath = Paths.get(path.getParent().toString(), getImage());
		File imageFile = imagePath.toFile();
		
		if (imageFile.exists())
			return true;
		
		return false;
	}
	
	public String getDuration() {
		return ini.get("header", "duration");
	}
	
	public String getPeople() {
		return ini.get("header", "people");
	}
	
	public String getTeam() {
		return ini.get("header", "team");
	}
	
	public String getManager() {
		return ini.get("header", "manager");
	}
	
	public String getLanguage() {
		return ini.get("header", "language");
	}
	
	
	public String[] getSectionList(String name) {
		String context = ini.get(name, "list");
		
		if (context == null) {
			System.out.println("Missing Section named [ " + name + " ] !!");
			return null;
		}
		return context.split(";");	
	}
	
	public String[] getContext(){
		return getSectionList("context");
	}
	
	public String[] getObjectives(){
		return getSectionList("objectives");	
	}
	
	public String[] getResults(){
		return getSectionList("results");
	}
	
	public void printList(String [] list) {
		for(int i=0; i<list.length;i++) {
			System.out.println(list[i]);
		}
	}
	
	public Set<Entry<String, String>> getSolution() {
		
		Section solution = ini.get("solution");
		
		if (solution == null)
			return null;
		
		return solution.entrySet();
	}
}
