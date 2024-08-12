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

public class CreateOrganizationTest {

	public static void main(String[] args) {
		propertiesUtility propertyUtil=new propertiesUtility();
		Excelutility excel = new Excelutility();
		javaUtility jutil=new javaUtility();
		webdriverutility driverutil=new webdriverutility();
		
		propertyUtil.PropertiesInit(Iconstantpath.PROPERTIES_FILE_PATH);
		excel.excelInit(Iconstantpath.EXCEL_PATH);
		
		WebDriver driver = driverutil.launchBrowser(propertyUtil.readfromperperties("browser"));
		driverutil.maximizeBrowser();
		driverutil.navigateToApp(propertyUtil.readfromperperties("url"));
		long time=(Long) jutil.convertStringToAnyDataType(propertyUtil.readfromperperties("timeouts"),DataType.LONG);
		driverutil.waitTillElementFound(time);
		
		
		if(driver.getTitle().contains("vtiger CRM"))
			System.out.println("Login Page Displayed");
		else
			driverutil.quitAllWindows();
		
		driver.findElement(By.name("user_name")).sendKeys(propertyUtil.readfromperperties("username"));
		driver.findElement(By.name("user_password")).sendKeys(propertyUtil.readfromperperties("password"));
		driver.findElement(By.id("submitButton")).submit();
		
		if(driver.getTitle().contains("Home"))
			System.out.println("Home Page is Displayed");
		else
			driverutil.quitAllWindows();
		
		driver.findElement(By.xpath("//a[contains(@href,'Accounts&action=index')]")).click();
		
		if(driver.getTitle().contains("Organizations"))
			System.out.println("Organizations Page is Displayed");
		else
			driverutil.quitAllWindows();
		
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		WebElement pageHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if(pageHeader.isDisplayed())
			System.out.println("Creating New Organization Page is Displayed");
		else
			driverutil.quitAllWindows();
		Map<String, String> map=excel.readfromexcel("OrganizationsTestData", "Create Organization");
		driver.findElement(By.name("accountname")).sendKeys(map.get("Organization Name"));
		driver.findElement(By.xpath("//input[contains(@title,'Save')]")).click();
		
		String newOrgPageHeader = driver.findElement(By.cssSelector("span.dvHeaderText")).getText();
		if(newOrgPageHeader.contains(map.get("Organization Name")))
			System.out.println("Organization created successfully");
		else
			driverutil.quitAllWindows();
		
		driver.findElement(By.xpath("//input[@name='Delete']")).click();
		driverutil.handleAlert("ok");
		
		if(driver.getTitle().contains("Organizations")) {
			System.out.println("Organizations Page is Displayed");
		excel.writetoexcel("OrganizationsTestData", "Create Organization","Pass");}
		else
			driverutil.quitAllWindows();		
		WebElement adminWidget = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		driverutil.mouseHover(adminWidget);
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		excel.closeexcel();
		driverutil.quitAllWindows();
	}
}
