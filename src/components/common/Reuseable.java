package components.common;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.asserts.SoftAssert;

public class Reuseable {
	WebDriver driver;
	
	
	public Reuseable(WebDriver driver){
		this.driver =driver;
	}
	
	public void  LaunchApplication(String url){
		driver.get(url);
	}
	
	public boolean isElementFound(By by){
		try{
			WebElement element = driver.findElement(by);
			
		}catch(NoSuchElementException el){
			
			return false;	
		}
		catch(Exception e){
			
			return false;
		}
		return true;
	}
		
	
}
