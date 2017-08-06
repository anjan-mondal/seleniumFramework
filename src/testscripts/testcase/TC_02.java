package testscripts.testcase;

import org.junit.Before;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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

public class TC_02 extends TestCase {

	
	Reuseable reuseable;
	ExtentReports report;
	ExtentTest logger;
	String caseName ;
	
	@BeforeSuite
	
	public void setUp(){
		caseName= (getTestSuiteName(this.toString()));
		report = new ExtentReports("D:\\report\\"+caseName+".html",false);
		
		logger=report.startTest("Test case 2");
	}
	
	@Test(dataProvider = "test")

	public void executeTest(String Username, String password, String url,String broswer) {
		
		
		driver = getDriver(broswer); 		
		reuseable=new Reuseable(driver);
		driver.manage().window().maximize();
	    reuseable.LaunchApplication(url);
	    logger.log(LogStatus.PASS, "URl launched" + url + "<a href ='#'/> click");
		FBLogin login = new FBLogin(driver);
		
		//.login(Username, password);
		
		FBProfile profile = new FBProfile(driver);
	    logger.log(LogStatus.FAIL, "Profile not loaded");

		//profile.logout();
	    Wrap();
	}
	
	
	
	@AfterSuite
	public void tearDown(){
		report.endTest(logger);
		report.flush();
		System.out.println(logger.getRunStatus().toString());
		
	}
	

	@DataProvider(name = "test")

	public Object[][] Authentication() throws Exception {

		Object[][] testObjArray = ExcelUtils1.getData(ExcelUtils1.getTestCaseName(this.toString()), "Sheet1");

		return (testObjArray);

	}

}
