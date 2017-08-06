package testscripts.testcase;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import components.common.Report;
import components.common.Reporter;
import components.common.Reuseable;
import components.common.TestCase;
import components.pages.FBLogin;
import components.pages.FBProfile;

import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utility.ExcelUtils1;

public class TC_01 extends TestCase {

	
	Reuseable reuseable;
	//ExtentReports report;
	//ExtentTest logger;
	String caseName ;
	Report report;
	@BeforeSuite
	
	public void setUp(){
		caseName= (getTestCaseName(this.toString()));
		//report = new ExtentReports("D:\\report\\"+caseName+".html",false);
		//logger=report.startTest("Test case 1");
		driver = getDriver("chrome"); 	
		report  = new Report(driver);
		
		report.setTestSuiteName(caseName);
		report.setTestSummary("Open google page");
	}
	
	@Test(dataProvider = "test")

	public void executeTest(String Username, String password, String url,String broswer) {
		
		
			
		reuseable=new Reuseable(driver);
		driver.manage().window().maximize();
	    reuseable.LaunchApplication(url);
	    report.updateReport("URl launched" + url ,"PASS");
	    //logger.log(LogStatus.PASS, "URl launched" + url + "<a href ='#'/> click");
		FBLogin login = new FBLogin(driver);
		
		//.login(Username, password);
		
		FBProfile profile = new FBProfile(driver);
		//report.updateReport("Profile not loaded","FAIL");
	    //logger.log(LogStatus.FAIL, "Profile not loaded");

		//profile.logout();
	    Wrap();
	}
	
	
	
	@AfterSuite
	public void tearDown(){
		//report.endTest(logger);
		//report.flush();
		//System.out.println(logger.getRunStatus().toString());
		report.wrapReport();
		
	}
	

	@DataProvider(name = "test")

	public Object[][] Authentication() throws Exception {

		Object[][] testObjArray = ExcelUtils1.getData(ExcelUtils1.getTestCaseName(this.toString()), "Sheet1");

		return (testObjArray);

	}

}
