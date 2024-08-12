package PomImplimentation;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import genericutilities.DataType;
import genericutilities.Excelutility;
import genericutilities.Iconstantpath;
import genericutilities.TabNames;
import genericutilities.javaUtility;
import genericutilities.propertiesUtility;
import genericutilities.webdriverutility;

import objectrepositories.ContactInformationPage;
import objectrepositories.ContactsPage;
import objectrepositories.CreatingNewContactsPage;
import objectrepositories.HomePage;
import objectrepositories.LoginPage;

public class CreateContactWithExistingOrgTest {

	public static void main(String[] args) {
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

		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		ContactsPage contact = new ContactsPage(driver);
		CreatingNewContactsPage createContact = new CreatingNewContactsPage(driver);
		ContactInformationPage contactInfo = new ContactInformationPage(driver);
		
		if (driver.getTitle().contains("vtiger CRM"))
			System.out.println("Login Page Displayed");
		else
			driverUtil.quitAllWindows();

		login.loginToVtiger(propertyUtil.readfromperperties("username"), propertyUtil.readfromperperties("password"));

		if (driver.getTitle().contains("Home"))
			System.out.println("Home Page is Displayed");
		else
			driverUtil.quitAllWindows();
		
		home.clickRequiredTab(driverUtil, TabNames.CONTACTS);
		if (driver.getTitle().contains("Contacts"))
			System.out.println("Contacts Page Displayed");
		else
			driverUtil.quitAllWindows();
		
		contact.clickCreateContactBTN();

		if (createContact.getPageHeader().equalsIgnoreCase("Creating new contact"))
			System.out.println("Creating New Contact Page is Displayed");
		else
			driverUtil.quitAllWindows();
		
		Map<String, String> map = excel.readfromexcel("ContactsTestData", "Create Contact With Organization");
		
		createContact.setContactLastName(map.get("Last Name"));
		createContact.selectExistingOrganization(driverUtil, map.get("Organization Name"));

		createContact.clickSaveBTN();

		if (contactInfo.getPageHeader().contains(map.get("Last Name")))
			System.out.println("Contact created successfully");
		else
			driverUtil.quitAllWindows();
		
		contactInfo.clickDeleteBTN();
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

		home.signOutOfVtiger(driverUtil);
		excel.closeexcel();
		driverUtil.quitAllWindows();
	}

}