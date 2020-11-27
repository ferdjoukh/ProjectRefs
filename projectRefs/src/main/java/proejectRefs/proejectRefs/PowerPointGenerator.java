package proejectRefs.proejectRefs;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map.Entry;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.TextParagraph.TextAlign;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;


public class PowerPointGenerator {

	private String template;
	private Reference reference;
	private XMLSlideShow powerpoint;
	private XSLFSlideLayout referenceLayout; 
	private String outputPath;
	private int currentSlide = 0;
	
	public PowerPointGenerator(String template, Reference ref, String outPath) {
		this.template = template;
		this.outputPath = outPath;
		this.reference = ref;
	}
	
	public void generate() {
		
		File file = new File(template);
		FileInputStream inputstream;
		try {
			inputstream = new FileInputStream(file);
			powerpoint = new XMLSlideShow(inputstream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String duration = "";
		String people = "";
		if (reference.getDuration() != null)
			duration = "\n" + reference.getDuration() + " " ;
		if (reference.getPeople() != null)
			people = " " + reference.getPeople() + " " ;
		String client = reference.getClient().toUpperCase() +  duration + people; 
		
		this.referenceLayout =  getTemplateLayout();
		powerpoint.createSlide(referenceLayout);
		setTitle(reference.getTitle());
		setClient(client);
		setContext(reference.getContext());
		setObjectives(reference.getObjectives());
		setResults(reference.getResults());
		try {
			setImage(reference.getImage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		expandSolutions();
	}
	
	public void expandSolutions() {
		Set<Entry<String,String>> sols = reference.getSolution();
		int nb = 0;
		for (Entry e : sols) {
			String [] temps = ((String) e.getValue()).split(";");
			nb = nb + temps.length;
		}
		
		String [] temps = null;
		String [] allsols = new String[nb];
		int j=0;
		for (Entry e : sols) {
			temps = ((String) e.getValue()).split(";");
			for (int i=0; i< temps.length; i++) {
				allsols[j] = temps[i];
				j++;
			}
		}
		
		setSolution(allsols);
				
	}
	
	public void createNewReference() {
		powerpoint.createSlide(referenceLayout);
		currentSlide++;
	}
	
	private XSLFSlideLayout getTemplateLayout() {
		
		//getting the list of all slide masters
		  for(XSLFSlideMaster master : powerpoint.getSlideMasters()) {

		     //getting the list of the layouts in each slide master
		     for(XSLFSlideLayout layout : master.getSlideLayouts()) {

		        //getting the list of available slides
		        if (layout.getName().equals("project-reference"))
		        	return layout;
		     } 
		  }
		return null;
	}
	
	
	public void setTitle(String value) {
		
		XSLFSlide slide = powerpoint.getSlides().get(currentSlide);
		XSLFTextShape title = null;
		  
		for (XSLFShape shape : slide.getShapes()) {
		  if (shape.getShapeName().equals("title"))
			  title = (XSLFTextShape) shape;
		}
		if(title != null) {
			title.clearText();
			XSLFTextParagraph p = title.addNewTextParagraph();
			p.setTextAlign(TextAlign.CENTER);
			XSLFTextRun r = p.addNewTextRun();
			r.setFontColor(Color.decode("#FFFFFF"));
			r.setFontSize(18.);
			r.setBold(true);
			r.setText(value);
		}
	}
	
	public void setClient(String value) {
		
		XSLFSlide slide = powerpoint.getSlides().get(currentSlide);
		XSLFTextShape title = null;
		  
		for (XSLFShape shape : slide.getShapes()) {
		  if (shape.getShapeName().equals("infos"))
			  title = (XSLFTextShape) shape;
		}
		if(title != null) {
			title.clearText();
			XSLFTextParagraph p = title.addNewTextParagraph();
			p.setTextAlign(TextAlign.CENTER);
			XSLFTextRun r = p.addNewTextRun();
			r.setFontColor(Color.decode("#FFFFFF"));
			r.setFontSize(12.);
			r.setBold(true);
			r.setText(value);
		}
	}
	
	private void fillList(String shapeName, String[] list) {
		XSLFSlide slide = powerpoint.getSlides().get(currentSlide);
		XSLFTextShape content = null;
		  
		for (XSLFShape shape : slide.getShapes()) {
		  if (shape.getShapeName().equals(shapeName))
			  content = (XSLFTextShape) shape;
		}
		if(content != null) {
			content.clearText();
			
			for(String item : list) {
				XSLFTextParagraph p = content.addNewTextParagraph();
				p.setBullet(true); 
				p.setTextAlign(TextAlign.JUSTIFY);
				XSLFTextRun r = p.addNewTextRun();
				r.setFontColor(Color.decode("#FFFFFF"));
				r.setFontSize(12.);
				r.setText(item);
			}									
		}
	}
	
	public void setContext(String [] context){
		fillList("context", context);
	}
	
	public void setObjectives(String [] objectives){
		fillList("objectives", objectives);
	}
	
	public void setResults(String [] results){
		fillList("results", results);
	}
	
	public void setSolution(String [] results){
		fillList("solution", results);
	}
	
	public void setImage(String path) throws Exception {
		
		XSLFSlide slide = powerpoint.getSlides().get(currentSlide);
		
		BufferedImage bImage = ImageIO.read(new File(path));
		//converting it into a byte array
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bImage, "jpg", bos);
		byte [] data = bos.toByteArray();
		XSLFPictureData pictureData = powerpoint.addPicture(data, PictureData.PictureType.PNG);
		XSLFPictureShape pictureShape = slide.createPicture(pictureData);
		pictureShape.setAnchor(new Rectangle(0, 0, 555, 170));		
	}
		
	public void save() throws Exception {
		FileOutputStream out = new FileOutputStream(outputPath);
		powerpoint.write(out);
		out.close();
		powerpoint.close();
	}
	
}
