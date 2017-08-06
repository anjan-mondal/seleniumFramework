package components.common;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.asserts.SoftAssert;

public class TestCase {
	
	protected WebDriver driver;

	public TestCase(){
		
	}
	
	public void Wrap(){
		driver.quit();
		
		
	}
	
	public WebDriver getDriver(String browser){
		String driverFilePath= System.getProperty("user.dir");
		if (browser.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("chrome")){
			driverFilePath =driverFilePath+"/src/webdrivers/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", driverFilePath);
			driver = new ChromeDriver();
		}else{
			System.out.println("Browerd not defined");
		}
		return driver;	
	}
	
	// This test case will return test case name
	
	
	public String getTestCaseName(String TestcaseSuite){	
		try {
			int posi = TestcaseSuite.indexOf("@");
			TestcaseSuite = TestcaseSuite.substring(0, posi);
			posi = TestcaseSuite.lastIndexOf(".");
			TestcaseSuite = TestcaseSuite.substring(posi + 1);
			return TestcaseSuite;
		}catch (Exception e) {
			throw (e);
		}		
	}
	
	// This test case will return test suite name
	
	public String getTestSuiteName(String TestcaseSuite){	
		String[] words=TestcaseSuite.split("\\.");
		System.out.println(words[1]);
		return words[1];	
	}	
}
