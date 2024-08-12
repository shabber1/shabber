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
import objectrepositories.CreatingNewLeadPage;
import objectrepositories.DuplicatingPage;
import objectrepositories.HomePage;
import objectrepositories.LeadInformationPage;
import objectrepositories.LeadsPage;
import objectrepositories.LoginPage;

public class CreateAndDuplicateLeadTest {

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

		long time = (Long) jutil.convertStringToAnyDataType(propertyUtil.readfromperperties("timeouts"), DataType.LONG);
		driverUtil.waitTillElementFound(time);

		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		LeadsPage leads = new LeadsPage(driver);
		CreatingNewLeadPage createLead = new CreatingNewLeadPage(driver);
		DuplicatingPage duplicateLead = new DuplicatingPage(driver);
		LeadInformationPage leadInfo = new LeadInformationPage(driver);

		if (driver.getTitle().contains("vtiger CRM"))
			System.out.println("Login Page Displayed");
		else
			driverUtil.quitAllWindows();

		login.loginToVtiger(propertyUtil.readfromperperties("username"), propertyUtil.readfromperperties("password"));

		if (driver.getTitle().contains("Home"))
			System.out.println("Home Page is Displayed");
		else
			driverUtil.quitAllWindows();

		home.clickRequiredTab(driverUtil, TabNames.LEADS);

		if (driver.getTitle().contains("Leads")) {
			System.out.println("Leads Page is Displayed");}
		else {
			driverUtil.quitAllWindows();}

		leads.clickCreateLeadBTN();

		if (createLead.getPageHeader().equalsIgnoreCase("creating new lead"))
			System.out.println("Creating New lead Page is Displayed");
		else
			driverUtil.quitAllWindows();

		Map<String, String> map = excel.readfromexcel("LeadsTestData", "Create and Duplicate Lead");
		String lastName = map.get("Last Name") + jutil.generateRandomNum(100);
		createLead.setLeadLastName(lastName);
		createLead.setCompanyName(map.get("Company"));
		createLead.clickSaveBTN();

		if (leadInfo.getPageHeader().contains(lastName))
			System.out.println("Lead created successfully");
		else
			driverUtil.quitAllWindows();

		leadInfo.clickDuplicateBTN();
		if (duplicateLead.getPageHeader().contains("Duplicating"))
			System.out.println("Duplicating lead page displayed");
		else
			driverUtil.quitAllWindows();

		String newLastName = map.get("New Last Name") + jutil.generateRandomNum(100);
		duplicateLead.setLeadLastName(newLastName);
		duplicateLead.clickSaveBTN();

		if (leadInfo.getPageHeader().contains(newLastName)) {
			System.out.println("Duplicate Lead created successfully");
			excel.writetoexcel("LeadsTestData", "Create and Duplicate Lead", "Pass");
		} else {
			driverUtil.quitAllWindows();
			excel.writetoexcel("LeadsTestData", "Create and Duplicate Lead", "Fail");
		}

		excel.saveexcel(Iconstantpath.EXCEL_PATH);

		home.signOutOfVtiger(driverUtil);
		excel.closeexcel();
		driverUtil.quitAllWindows();
	}

}