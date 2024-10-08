//package page;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Reporter;
//
//public class HomePage {
//	@FindBy(xpath="//a[text()='Logout]")
//	private WebElement logout;
//	
//	public HomePage(WebDriver driver)
//	{
//		PageFactory.initElements(driver, this);
//	}
//
//	public void clickLogout()
//	{
//		logout.click();
//	}
//	public boolean homePageDisplayed(WebDriverWait wait)
//	{
//		try {
//			Reporter.log("Inside try",true);
//			wait.until(ExpectedConditions.visibilityOf(logout));
//			Reporter.log("Logout displayed and clicked :",true);
//			return true;
//		}
//		catch(Exception e)
//		{
//			Reporter.log("Logout not displayed",true);
//			return false;
//		}
//		
//	}
//}

package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class HomePage {
	@FindBy(xpath="//a[text()='Logout']")
	private WebElement logout;
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickLogout()
	{
		Reporter.log("Going to click logout",true);
		logout.click();
	}
	
	public boolean homePageIsDisplayed(WebDriverWait wait)
	{
		try {
			wait.until(ExpectedConditions.visibilityOf(logout));
			return true;
		}
		catch(Exception e){
			Reporter.log("Logout page is not dsplayed",true);
			return false;
		}
		
		
	}

}

