package projectRefs.projectRefs;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final String TEMPLATE_1 = "resources/reference-template.pptx";
	
	/**
	 * 
	 * @param args
	 */
    public static void main( String[] args)
    {
    	if(args.length==0) {
			Help.printHelp();
    	}else if (args.length<4) {
    		System.out.println("Incorrect number of parameters ! At least 4 are required");
    		return;
		}else {
			switch(args[0]) {
			
				case "h":
				case "help":{
					Help.printHelp();
				}
				break;
				
				case "f":
				case "file":{
					try {
						generateFromFile(args);
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
				}
				break;
				
				case "d":
				case "directory":{
					try {
						System.out.println("Directory mode is coming soon");
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
				}
				break;
				
				default:{
						System.out.println("invalid parameter " + args[0] +" ! Please check help");
				}
			}
		}
    }

	private static void generateFromFile(String[] args) {
		
		File in = new File(args[1]);
		File out = new File(args[3]);		
		
		if (!in.exists()) {
			System.out.println("Invalid input file : "+ args[1]);
			return;
		}
		
		if (!args[2].equals("o") && !args[2].equals("output")) {
			System.out.println("parameter number 3 must be o or output");
			return;
		}
		
//		if(!out.exists()) {
//			System.out.println("Invalid output file : "+ args[3]);
//			return;
//		}
		
		ReferenceValidatorAndCreator validator = new ReferenceValidatorAndCreator(args[1]);
		ValidationReport report = validator.validate();
		if (!report.isValid()) {
			report.printReport();
			return;
		}
		
		Reference ref = validator.createReference();
		PowerPointGenerator generator = new PowerPointGenerator(TEMPLATE_1, ref, args[3]);
		generator.generate();
		try {
			generator.save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
