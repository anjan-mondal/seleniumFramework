package components.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import components.common.Report;
import components.common.Reporter;
import components.common.Reuseable;


public class FBLogin {
    WebDriver driver;

    Reuseable reuseable;
    Report report;
    
    public FBLogin(WebDriver driver,Report report ){
     this.driver=driver;
     this.report =report;
     reuseable = new Reuseable(driver,report);
     
     
    }
    
    
    public void login(String email,String pass) {
        
        try{
            driver.findElement(By.id("login_name_")).sendKeys(email);
            driver.findElement(By.id("login_password_")).sendKeys(pass);
            driver.findElement(By.id("login")).submit();
            Reporter.report("Login page loaded", "Login button clicked", "PASS");
            
        }catch(NoSuchElementException el){
            
           
        }
        catch(Exception e){
            
        	 
        }    
    }
}
