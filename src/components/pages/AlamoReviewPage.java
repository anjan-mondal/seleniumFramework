package components.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import components.common.Report;
import components.common.Reuseable;

public class AlamoReviewPage {

	WebDriver driver;
	Reuseable reuseable;
	Report report;
	

	By firstName = By.xpath(".//*[@name='firstName']");
	By lastName = By.xpath(".//*[@name='lastName']");
	By email = By.xpath(".//*[@name='email']");
	
	public AlamoReviewPage(WebDriver driver,Report report){
		this.driver = driver;
		this.report =report;
	}

	public void enterFirstName(String strFirstName) {
		WebDriverWait wait = new WebDriverWait(driver,120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
		driver.findElement(firstName).sendKeys(strFirstName);
		report.updateReport("first Name entered "+ strFirstName , "PASS");
	}
	
	public void enterLastName(String strLastName) {
		driver.findElement(lastName).sendKeys(strLastName);
		report.updateReport("last Name entered "+ strLastName , "PASS");
	}
	
	public void enterEmail(String stremail) {
		driver.findElement(email).sendKeys(stremail);
		report.updateReport("Email entered "+ stremail , "PASS");
	}
	
}

