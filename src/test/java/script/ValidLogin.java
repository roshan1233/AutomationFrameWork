package script;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;
import page.HomePage;
import page.LoginPage;

public class ValidLogin extends BaseTest {
	@Test(priority=1)
	public void loginTest()
	{
		Reporter.log("inside test",true);
		String name = Utility.getExceldata(EXCELPATH, "ValidLogin", 1,0);
		String pwd = Utility.getExceldata(EXCELPATH, "ValidLogin", 1,1);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.sendUname(name);
		loginPage.sendPwd(pwd);
		loginPage.clickGoutton();
		
		HomePage page = new HomePage(driver);
		boolean b = page.homePageIsDisplayed(wait);
		//Assert.assertTrue(b);
		
	}

}
