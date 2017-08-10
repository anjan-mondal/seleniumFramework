package components.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class Report  {
    WebDriver driver;
    ExtentReports report;
	ExtentTest logger;
	private  String reportPath;

    public Report(WebDriver driver){
        this.driver =driver;
        reportPath =Reporter.createResultFolder();       
    }
    
    public void updateReport(String log1 ,String Status){
        
        if (Status.equalsIgnoreCase("FAIL")){
        	String imgFilePath = takeScreenShot();
        	log1 =log1+"<a href ='"+imgFilePath+"'/> click";
        	 logger.log(LogStatus.FAIL, log1);
        }else if(Status.equalsIgnoreCase("PASS")){
        	String imgFilePath = takeScreenShot();
        	log1 =log1+"<a href ='"+imgFilePath+"'/> click";
        	 logger.log(LogStatus.PASS, log1);
        }
        
        
    }
   
    public void wrapReport(){
    	report.endTest(logger);
		report.flush();
		System.out.println(logger.getRunStatus().toString());
    }
    public void setTestSummary(String testSummary){
    	
    	logger=report.startTest(testSummary);
    }
    public void setTestCaseName(String testCaseName){
    	
    	report = new ExtentReports(reportPath+testCaseName+".html",false);
    	report.config()
        .documentTitle("Automation Report")
        .reportHeadline(testCaseName)
        .reportName("Regression Suite - >");
    }
 
    public String takeScreenShot(){
    	String currentDate = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String imgFilePath=Reporter.createResultFolderImage(reportPath)+currentDate+".png";
        try {
			FileUtils.copyFile(scrFile, new File(imgFilePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("***Placed screen shot in "+imgFilePath+" ***");
   
        return imgFilePath;
    }
    
}

