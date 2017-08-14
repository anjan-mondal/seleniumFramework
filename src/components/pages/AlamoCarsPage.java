package components.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import components.common.Report;
import components.common.Reuseable;

public class AlamoCarsPage {

	WebDriver driver;

    Reuseable reuseable;
    Report report;
	

	public AlamoCarsPage(WebDriver driver, Report report){
		this.driver = driver;
		this.report =report;
	}

	public void selectCar(String car) {
		WebDriverWait wait = new WebDriverWait(driver,120);
		String carXPath = ".//*[text()='"+car+"']/../../../../..//*[@data-id='selectCar_payLater_button']";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(carXPath)));
		driver.findElement(By.xpath(carXPath)).click();
		//report.updateReport("Car selected "+ car , "PASS");
	}
	
}

