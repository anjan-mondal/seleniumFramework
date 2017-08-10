package components.common;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;

import bsh.This;

public class Reporter {
	
	private static List<Result> details;
	private static final String resultPlaceholder = "<!-- INSERT_RESULTS -->";
	private static final String templatePath = "D:\\report_template.html";
	private static String currentDate;		
	private static String Tc_Name;
	private static String suiteName;
	public Reporter() 
	{
	}
	
	public static void initialize(String Tc_name,String SuiteName) {
		details = new ArrayList<Result>();
		Tc_Name =Tc_name;
		suiteName =SuiteName;
	}
	
	public static void report(String actualValue,String expectedValue,String status) {
		if(status.equalsIgnoreCase("PASS")) {
			Result r = new Result("Pass", actualValue + "   " + expectedValue,"https://elearningindustry.com/wp-content/uploads/2014/07/Top-10-Reasons-Why-LMS-Implementation-Fail.png");
			details.add(r);
		} else {
			Result r = new Result("Fail", actualValue + "   " + expectedValue,"https://elearningindustry.com/wp-content/uploads/2014/07/Top-10-Reasons-Why-LMS-Implementation-Fail.png");
			details.add(r);
		}
	}
	
	public static void showResults() {
		for (int i = 0;i < details.size();i++) {
			System.out.println("Result " + Integer.toString(i) + ": " + details.get(i).getResult());
		}
	}
	
	
	public static String projectFolder(){
		String projectPath= System.getProperty("user.dir");
		return projectPath;
	}
	
	public static String createResultFolder()
	{
		currentDate = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
		 File file = new File(projectFolder()+File.separator+"result"+File.separator+currentDate+File.separator);
		 System.out.println(projectFolder()+File.separator+"result"+File.separator+currentDate+File.separator);
	        if (!file.exists()) {
	        	System.out.println(file.mkdir());
	            if (file.mkdir()) {
	            	
	                System.out.println("Directory is created!");
	            } else {
	                System.out.println("Failed to create directory!");
	            }
	        }
			return projectFolder()+File.separator+"result"+File.separator+currentDate+File.separator;	    
	}
	
	public static String createResultFolderImage(String reportPath)
	{
		currentDate = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
		 File file = new File(reportPath+"image"+File.separator);
	        if (!file.exists()) {
	            if (file.mkdir()) {
	                System.out.println("Directory is created!");
	            } else {
	                System.out.println("Failed to create directory!");
	            }
	        }
			return reportPath+"image"+File.separator;	    
	}
	public static String createResultFolderSuite(String reportPath)
	{
		currentDate = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
		 File file = new File(reportPath+"image"+File.separator);
	        if (!file.exists()) {
	            if (file.mkdir()) {
	                System.out.println("Directory is created!");
	            } else {
	                System.out.println("Failed to create directory!");
	            }
	        }
			return reportPath+"image"+File.separator;	    
	}
	
}