package components.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import components.common.Report;
import components.common.Reporter;
import components.common.Reuseable;


public class FBProfile {
    WebDriver driver;
    SoftAssert softAssert ;
    Reuseable reuseable;
    Report report;
    
    
    public FBProfile(WebDriver driver){
     this.driver=driver;
    
    }
    
    
    // logout from page
    
    public void logout(){
        
        try{
           
            WebDriverWait wait =new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("main_multi_filter__member_type_pk_")));
            driver.findElement(By.linkText("Sign Out")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_name_")));
           
            
            
        }catch(NoSuchElementException el){
            
        	
        }
        catch(Exception e){
        	
            
        }
        
        
        
    }

}
