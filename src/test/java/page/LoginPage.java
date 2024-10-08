package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	@FindBy(id="input-username")
	private WebElement uName;
	
	@FindBy(id="input-password")
	private WebElement pwd;
	
	@FindBy(name="login-button")
	private WebElement goButton;
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void sendUname(String name)
	{
		uName.sendKeys(name);
	}
	public void sendPwd(String password)
	{
		pwd.sendKeys(password);
	}
	public void clickGoutton()
	{
		goButton.click();
	}
}
