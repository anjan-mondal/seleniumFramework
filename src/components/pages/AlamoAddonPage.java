package components.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import components.common.Report;
import components.common.Reuseable;

public class AlamoAddonPage {

	 WebDriver driver;

	    Reuseable reuseable;
	    Report report;
	

	By ContinueButton = By.xpath(".//*[@data-id='addon_continue_btn']");
	
	public AlamoAddonPage(WebDriver driver,Report report){
		this.driver = driver;
		this.report =report;
	}

	public void clickContinue() {
		WebDriverWait wait = new WebDriverWait(driver,120);
		wait.until(ExpectedConditions.elementToBeClickable(ContinueButton));
		driver.findElement(ContinueButton).click();
		report.updateReport("Continue Button clicked " , "PASS");
		
	}
	
}

