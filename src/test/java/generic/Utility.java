package generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public abstract class Utility {
	public static String getProperties(String path,String property) throws IOException
	{
		String url="";
		try
		{
			Properties prop = new Properties();
			prop.load(new FileInputStream(path));
			url = prop.getProperty(property);			
		}
		catch(Exception e)
		{
			Reporter.log("",true);
			e.printStackTrace();
		}
		return url;		
	}
	
	public static String getExceldata(String excelPath,String sheetName, int r, int c)
	{
		try
		{
			Workbook wb = WorkbookFactory.create(new FileInputStream(excelPath));
			Sheet sheet = wb.getSheet(sheetName);
			String data=sheet.getRow(r).getCell(c).toString();
			return data;	
		}
		catch(Exception e)
		{
			Reporter.log("Data not found",true);
			return "";
		}
		
	}

}
