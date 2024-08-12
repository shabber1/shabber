package genericutilities;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import objectrepositories.HomePage;
import objectrepositories.LoginPage;
import objectrepositories.PageObjectManager;

public class BaseClass {

	//@BeforeSuite
	//@BeforeTest
	protected propertiesUtility propertyUtil;
	protected Excelutility excel;
	protected javaUtility jutil;
	protected webdriverutility driverUtil;
	
	protected WebDriver driver;
	
	public static WebDriver sdriver;
	public static javaUtility sjutil;
	
	protected PageObjectManager pageObjectManager;
	
	protected LoginPage login;
	protected HomePage home;
	
	protected SoftAssert soft;
	//@Parameters("BROWSER")
	@BeforeClass(groups = "important")
	public void classConfiguration() {
		propertyUtil = new propertiesUtility();
		excel = new Excelutility();
		jutil = new javaUtility();
		driverUtil = new webdriverutility();
		
		propertyUtil.PropertiesInit(Iconstantpath.PROPERTIES_FILE_PATH);
		excel.excelInit(Iconstantpath.EXCEL_PATH);
		//driver=driverUtil.launchBrowser(browser);
		driver = driverUtil.launchBrowser(propertyUtil.readfromperperties("browser"));
		driverUtil.maximizeBrowser();
		long time = (Long) jutil.convertStringToAnyDataType(propertyUtil.readfromperperties("timeouts"), DataType.LONG);
		driverUtil.waitTillElementFound(time);
		
		sdriver=driver;
		sjutil=jutil;
	}
	
	@BeforeMethod(groups = "important")
	public void methodConfiguration() {
		driverUtil.navigateToApp(propertyUtil.readfromperperties("url"));
		Assert.assertTrue(driver.getTitle().contains("vtiger CRM"));
		
		pageObjectManager = new PageObjectManager(driver);
		login = pageObjectManager.getLogin();
		home = pageObjectManager.getHome();

		login.loginToVtiger(propertyUtil.readfromperperties("username"), propertyUtil.readfromperperties("password"));
		Assert.assertTrue(driver.getTitle().contains("Home"));
		soft = new SoftAssert();
	}	
	
	@AfterMethod(groups = "important")
	public void methodTeardown() {
		excel.saveexcel(Iconstantpath.EXCEL_PATH);
		home.signOutOfVtiger(driverUtil);
	}
	
	@AfterClass(groups = "important")
	public void classTeardown() {
		excel.closeexcel();
		driverUtil.quitAllWindows();
	}
	//@AfterTest
	//@AfterSuite
}