package genericUtilityImplimentationScripts;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericutilities.DataType;
import genericutilities.Excelutility;
import genericutilities.Iconstantpath;
import genericutilities.javaUtility;
import genericutilities.propertiesUtility;
import genericutilities.webdriverutility;

public class CreateContactWithExistingOrgTest {

	public static void main(String[] args) throws InterruptedException {
		propertiesUtility propertyUtil = new propertiesUtility();
		Excelutility excel = new Excelutility();
		javaUtility jutil = new javaUtility();
		webdriverutility driverUtil = new webdriverutility();
		
		propertyUtil.PropertiesInit(Iconstantpath.PROPERTIES_FILE_PATH);
		excel.excelInit(Iconstantpath.EXCEL_PATH);
		
		WebDriver driver = driverUtil.launchBrowser(propertyUtil.readfromperperties("browser"));
		driverUtil.maximizeBrowser();
		driverUtil.navigateToApp(propertyUtil.readfromperperties("url"));
		
		long time = (Long) jutil.convertStringToAnyDataType(propertyUtil.readfromperperties("timeouts"), 
																				DataType.LONG);
		driverUtil.waitTillElementFound(time);

		if (driver.getTitle().contains("vtiger CRM"))
			System.out.println("Login Page Displayed");
		else
			driverUtil.quitAllWindows();

		driver.findElement(By.name("user_name")).sendKeys(propertyUtil.readfromperperties("username"));
		driver.findElement(By.name("user_password")).sendKeys(propertyUtil.readfromperperties("password"));
		driver.findElement(By.id("submitButton")).submit();

		if (driver.getTitle().contains("Home"))
			System.out.println("Home Page is Displayed");
		else
			driverUtil.quitAllWindows();
		driver.findElement(By.xpath("//a[contains(@href,'Contacts&action=index')]")).click();
		if (driver.getTitle().contains("Contacts"))
			System.out.println("Contacts Page Displayed");
		else
			driverUtil.quitAllWindows();
		
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();

		WebElement pageHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if (pageHeader.isDisplayed())
			System.out.println("Creating New Contact Page is Displayed");
		else
			driverUtil.quitAllWindows();
		
		Map<String, String> map = excel.readfromexcel("ContactsTestData", "Create Contact With Organization");
		
		driver.findElement(By.name("lastname")).sendKeys(map.get("Last Name"));
		driver.findElement(By.xpath("//img[contains(@onclick,'Accounts')]")).click();
		
		driverUtil.switchToWindow("Accounts");

		driver.findElement(By.xpath("//a[text()='"+map.get("Organization Name")+"']")).click();
		
		driverUtil.switchToWindow("Contacts");
		
		driver.findElement(By.xpath("//input[contains(@title,'Save')]")).click();

		String newContactPageHeader = driver.findElement(By.cssSelector("span.dvHeaderText")).getText();
		if (newContactPageHeader.contains(map.get("Last Name")))
			System.out.println("Contact created successfully");
		else
			driverUtil.quitAllWindows();
		
		driver.findElement(By.xpath("//input[@name='Delete']")).click();
		driverUtil.handleAlert("ok");
		
		if(driver.getTitle().contains("Contacts")) {
			System.out.println("Contacts Page is Displayed");
			excel.writetoexcel("ContactsTestData", "Create Contact With Organization", "Pass");
		}
		else {
			driverUtil.quitAllWindows();
			excel.writetoexcel("ContactsTestData", "Create Contact With Organization", "Fail");
		}
		
		excel.saveexcel(Iconstantpath.EXCEL_PATH);
		WebElement adminWidget = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		driverUtil.mouseHover(adminWidget);
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		excel.closeexcel();
		driverUtil.quitAllWindows();
	}

}