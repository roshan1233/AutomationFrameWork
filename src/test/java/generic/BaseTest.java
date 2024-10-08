package generic;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
 
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
public class BaseTest implements IAutoConst{

	public WebDriver driver;
	public WebDriverWait wait;
	@Parameters({"Grid","GridURL","Browser","ApplicationURL"})
	@BeforeMethod
	public void beforeMethod(@Optional(GRID) String grid, @Optional(GRIDURL) String gridURL , @Optional(BROWSER) String br, @Optional(APP_URL) String application_URL) throws IOException
	{
		if(grid.equalsIgnoreCase("yes"))
		{
			// Take the GridURL from config file
			String grid__url = Utility.getProperties(ENV, "Grid_URL");
			Reporter.log(grid__url , true);
			URL url = new URL(grid__url);
			if(br.equalsIgnoreCase("Chrome"))
			{
				ChromeOptions options = new ChromeOptions();
				driver = new RemoteWebDriver(url, options);
				Reporter.log("Server : Chrome Browser Launched",true);
			}
			else if(br.equalsIgnoreCase("Firefox"))
			{
				FirefoxOptions options = new FirefoxOptions();
				driver = new RemoteWebDriver(url, options);
				Reporter.log("Server : Firefox Browser Launched",true);
			}
			else
			{
				EdgeOptions options = new EdgeOptions();
				driver = new RemoteWebDriver(url, options);
				Reporter.log("Server : Edge Browser Launched",true);
			}
		}
		else
		{
			if(br.equalsIgnoreCase("Chrome"))
			{
				driver = new ChromeDriver();
				Reporter.log("Local : Chrome Browser Launched",true);
			}
			else if(br.equalsIgnoreCase("Firefox"))
			{
				driver = new FirefoxDriver();
				Reporter.log("Local : Firefox Browser Launched",true);
			}
			else
			{
				driver = new EdgeDriver();
				Reporter.log("Local : Edge Browser Launched",true);
			}
		}
		driver.manage().window().maximize();
		// To take the AppURL from Config File
		//String APPURL = Utilities.getProperties("./config.properties", "APPURL");
		// To take the AppURL from TestNG.XML file
		driver.get(application_URL);
		Reporter.log("Enter URL : "+application_URL,true);
		String takeITOValuefromConfig = Utility.getProperties(ENV, "ITO");
		int ITO = Integer.parseInt(takeITOValuefromConfig);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ITO));
		Reporter.log("Set ITO : "+ITO,true);

		String takeETOValuefromConfig = Utility.getProperties(ENV, "ETO");
		int ETO = Integer.parseInt(takeETOValuefromConfig);
		wait = new WebDriverWait(driver, Duration.ofSeconds(ETO));
		Reporter.log("Set ETO : "+ETO,true);
	}

	@AfterMethod
	public void afterMethod(ITestResult testResult) throws IOException
	{
		String testName = testResult.getName();
		Reporter.log(testName , true);
		int testStatus = testResult.getStatus();
		String testStatusInString = String.valueOf(testStatus);
		Reporter.log(testStatusInString , true);

		if(testStatus == 2)
		{
			TakesScreenshot t = (TakesScreenshot)driver;
			File srcFile = t.getScreenshotAs(OutputType.FILE);
			String path = SCREENSHOT+testName+".png";
			File destFile = new File(path);
			FileUtils.copyFile(srcFile, destFile);
			Reporter.log(testName + " TestCase is Failed , Hence screenshot is taken" + " & the TestCase status code is = " + testStatus , true);
		}
		else
		{
			Reporter.log(testName + " TestCase is Passed , Hence screenshot is not taken" + " & the TestCase status is = " + testStatus , true);
		}
		driver.quit();
		Reporter.log("Browser is closed :" , true);
	}
}


