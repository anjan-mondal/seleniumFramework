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

import utility.ExcelUtils1;

public class TC_02 extends TestCase {

	Reuseable reuseable;
	String caseName;
	Report report;

	@BeforeSuite

	public void setUp() {
		caseName = (getTestCaseName(this.toString()));
		driver = getDriver("chrome");
		report = new Report(driver);
		report.setTestSuiteName(caseName);
		report.setTestSummary("Open google page");
	}

	@Test(dataProvider = "test")

	public void executeTest(String Username, String password, String url, String broswer) {

		reuseable = new Reuseable(driver);
		driver.manage().window().maximize();
		reuseable.LaunchApplication(url);
		report.updateReport("URl launched" + url, "PASS");
		FBLogin login = new FBLogin(driver);

		// .login(Username, password);

		FBProfile profile = new FBProfile(driver);
		// report.updateReport("Profile not loaded","FAIL");
		Wrap();
	}

	@AfterSuite
	public void tearDown() {
		report.wrapReport();
	}

	@DataProvider(name = "test")
	public Object[][] Authentication() throws Exception {
		Object[][] testObjArray = ExcelUtils1.getData(ExcelUtils1.getTestCaseName(this.toString()), "Sheet1");
		return (testObjArray);

	}

}
