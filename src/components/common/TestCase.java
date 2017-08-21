package components.common;


import java.io.File;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
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
			driverFilePath =driverFilePath+File.separator+"src"+File.separator+"webdrivers"+File.separator+"chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", driverFilePath);
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("pichrome")){
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setPlatform(Platform.LINUX);
		    capabilities.setBrowserName("chrome");
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("gecko")){
			driverFilePath =driverFilePath+File.separator+"src"+File.separator+"webdrivers"+File.separator+"geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", driverFilePath);
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("pi")){
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		    System.setProperty("webdriver.gecko.driver", File.separator+"usr"+File.separator+"local"+File.separator+"bin"+File.separator+"geckodriver");
		    capabilities.setCapability("marionette", false);
		    capabilities.setPlatform(Platform.LINUX);
		    capabilities.setBrowserName("firefox");
		    driver = new FirefoxDriver(capabilities);
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
