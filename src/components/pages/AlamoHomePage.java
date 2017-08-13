package components.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import components.common.Report;
import components.common.Reuseable;

public class AlamoHomePage {

	WebDriver driver;
	

    Reuseable reuseable;
    Report report;

	By pickUpLocation = By.xpath(".//*[@name='pickUpLocation.searchCriteria']");
	By pickUpDateTime = By.xpath(".//*[@name='pickUpDateTime.date']");
	By dropOffDateTime = By.xpath(".//*[@name='dropOffDateTime.date']");
	By BookNowButton = By.xpath(".//*[@data-id='start_bookNow_button']");



	public AlamoHomePage(WebDriver driver,Report report){
		this.driver = driver;
		this.report =report;
	}

	public void enterPickUpLocation(String location) {
		driver.findElement(pickUpLocation).click();
		driver.findElement(pickUpLocation).sendKeys(location);
		report.updateReport("pick Up Location is " + location, "PASS");
	}
	
	public void enterpickUpDate(String pickUpDate) {
		driver.findElement(pickUpDateTime).sendKeys(pickUpDate);
		report.updateReport("pick Up Date Time is " + pickUpDate, "PASS");
	}
	
	public void enterdropOffDate(String dropOffDate) {
		driver.findElement(dropOffDateTime).sendKeys(dropOffDate);
		report.updateReport("drop Off Date Time is " + dropOffDate, "PASS");
	}
	
	public void clickBookNowButton() {
		driver.findElement(BookNowButton).click();
		report.updateReport("Book Now Button clicked ", "PASS");
	}

	
}

