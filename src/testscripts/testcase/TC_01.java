package testscripts.testcase;

import org.openqa.selenium.Dimension;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import components.pages.AlamoAddonPage;
import components.pages.AlamoCarsPage;
import components.pages.AlamoHomePage;
import components.pages.AlamoReviewPage;
import utility.ExcelUtils1;
import components.common.Report;
import components.common.Reuseable;
import components.common.TestCase;

public class TC_01 extends TestCase {

	Reuseable reuseable;
	// ExtentReports report;
	// ExtentTest logger;
	String caseName;
	Report report;


	@BeforeClass

	public void setUp() {
		caseName = (getTestCaseName(this.toString()));
		driver = getDriver("pichrome");
		report = new Report(driver);
		report.setTestCaseName(caseName);
		report.setTestSummary("Alamo Sanity Reservation Flow. Batch #1");
	}

	@Test(dataProvider = "sanity")
	public void runSanity(String location,String pickupdate,String dropOffDate,String car,String firstname,String lastname,String mailid) {
		reuseable=new Reuseable(driver,report);
		Dimension dimobj = new Dimension(1280,720);
		driver.manage().window().setSize(dimobj);
	    reuseable.LaunchApplication("https://www.alamo.com/en_US/car-rental/home.html");
		AlamoHomePage alamoHomePage = new AlamoHomePage(driver, report);
		AlamoCarsPage alamoCarsPage = new AlamoCarsPage(driver, report);
		AlamoAddonPage alamoAddonPage = new AlamoAddonPage(driver, report);
		AlamoReviewPage alamoReviewPage = new AlamoReviewPage(driver, report);
		alamoHomePage.enterPickUpLocation(location);
		alamoHomePage.enterpickUpDate(pickupdate);
		alamoHomePage.enterdropOffDate(dropOffDate);
		alamoHomePage.clickBookNowButton();
		alamoCarsPage.selectCar(car);
		alamoAddonPage.clickContinue();
		alamoReviewPage.enterFirstName(firstname);
		alamoReviewPage.enterLastName(lastname);
		alamoReviewPage.enterEmail(mailid);
		//Wrap();
	}
	
	@AfterClass
	public void tearDown(){
		Wrap();
		report.wrapReport();
		report.sendReportInMain();		
	}
	// *********Data provider name should be different for every test case******

	@DataProvider(name = "sanity")

	public Object[][] Authentication() throws Exception {

		Object[][] testObjArray = ExcelUtils1.getData(ExcelUtils1.getTestCaseName(this.toString()), "Sheet1");

		return (testObjArray);

	}

}
