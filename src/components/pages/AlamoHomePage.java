package components.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import components.common.Report;
import components.common.Reuseable;

public class AlamoHomePage {

	WebDriver driver;
	

    Reuseable reuseable;
    Report report;

	By pickUpLocation = By.xpath("//input[@data-id='pickUpLocation.searchCriteria']");
	By pickUpDateTime = By.xpath("//input[@data-id='pickUpDateTime.date']");
	By dropOffDateTime = By.xpath("//input[@data-id='dropOffDateTime.date']");
	By BookNowButton = By.xpath("//button[@data-id='start_bookNow_button']");

	WebDriverWait wait;
	

	public AlamoHomePage(WebDriver driver,Report report){
		this.driver = driver;
		this.report =report;
	}

	public void enterPickUpLocation(String location) {
		try {
			wait = new WebDriverWait(driver,120);
			wait.until(ExpectedConditions.elementToBeClickable(pickUpLocation));
		driver.findElement(pickUpLocation).click();
		driver.findElement(pickUpLocation).sendKeys(location);
		//report.updateReport("pick Up Location is " + location, "PASS");
		}
		catch(Exception e){
			report.updateReport("pick Up Location failed: " + e.getMessage(), "FAIL");
		}
	}
	
	public void enterpickUpDate(String pickUpDate) {
		driver.findElement(pickUpDateTime).sendKeys(pickUpDate);
		//report.updateReport("pick Up Date Time is " + pickUpDate, "PASS");
	}
	
	public void enterdropOffDate(String dropOffDate) {
		driver.findElement(dropOffDateTime).sendKeys(dropOffDate);
		report.updateReport("Enter Reservation Details Complete", "PASS");
	}
	
	public void clickBookNowButton() {
		WebElement element = driver.findElement(BookNowButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		driver.findElement(BookNowButton).click();
		//report.updateReport("Book Now Button clicked ", "PASS");
	}

	
}

