package testscripts.testcase;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import components.common.Report;
import components.common.Reuseable;
import components.common.TestCase;
import components.pages.FBLogin;
import components.pages.FBProfile;
import org.testng.annotations.DataProvider;
import utility.ExcelUtils1;

public class TC_02 extends TestCase {

	Reuseable reuseable;
	String caseName ;
	Report report;
	@BeforeTest
	
	public void setUp(){
		caseName= (getTestCaseName(this.toString()));
		driver = getDriver("pi"); 	
		report  = new Report(driver);
		report.setTestCaseName(caseName);
		report.setTestSummary("Open google page");		
	}
	@Test(dataProvider = "test1")
	public void executeTest(String Username, String password, String url,String broswer) {			
		reuseable=new Reuseable(driver,report);
		driver.manage().window().maximize();
	    reuseable.LaunchApplication(url);
		FBLogin login = new FBLogin(driver,report);
		//.login(Username, password);
		FBProfile profile = new FBProfile(driver,report);
	    Wrap();
	}
	@AfterTest
	public void tearDown(){
		report.wrapReport();		
	}	
	
	
	//*********Data provider name should be different for every test case******
	
	
	@DataProvider(name = "test1")

	public Object[][] Authentication() throws Exception {

		Object[][] testObjArray = ExcelUtils1.getData(ExcelUtils1.getTestCaseName(this.toString()), "Sheet1");

		return (testObjArray);

	}

}
