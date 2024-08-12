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

import objectrepositories.CreatingNewOrganizationPage;
import objectrepositories.HomePage;
import objectrepositories.LoginPage;
import objectrepositories.OrganizationsPage;
import objectrepositories.organizationInformationPage;

public class CreateOrgWithTypeAndIndustryTest {

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
		OrganizationsPage organization = new OrganizationsPage(driver);
		CreatingNewOrganizationPage createOrg = new CreatingNewOrganizationPage(driver);
		organizationInformationPage orgInfo = new organizationInformationPage(driver);

		if (driver.getTitle().contains("vtiger CRM"))
			System.out.println("Login Page Displayed");
		else
			driverUtil.quitAllWindows();

		login.loginToVtiger(propertyUtil.readfromperperties("username"), propertyUtil.readfromperperties("password"));

		if (driver.getTitle().contains("Home"))
			System.out.println("Home Page is Displayed");
		else
			driverUtil.quitAllWindows();

		home.clickRequiredTab(driverUtil, TabNames.ORGANIZATION);

		if (driver.getTitle().contains("Organizations"))
			System.out.println("Organizations Page is Displayed");
		else
			driverUtil.quitAllWindows();

		organization.clickCreateOrgBTN();

		if (createOrg.getPageHeader().equalsIgnoreCase("creating new organization"))
			System.out.println("Creating New Organization Page is Displayed");
		else
			driverUtil.quitAllWindows();

		Map<String, String> map = excel.readfromexcel("OrganizationsTestData", "Create Organization With Industry And Type");
		createOrg.setOrganizationName(map.get("Organization Name"));
		createOrg.selectFromIndustryDD(driverUtil, map.get("Industry"));
		createOrg.selectFromTypeDD(driverUtil, map.get("Type"));
		createOrg.clickSaveBTN();

		if (orgInfo.getPageHeader().contains(map.get("Organization Name")))
			System.out.println("Organization created successfully");
		else
			driverUtil.quitAllWindows();

		orgInfo.clickDeleteBTN();
		driverUtil.handleAlert("ok");

		if (driver.getTitle().contains("Organizations")) {
			System.out.println("Organizations Page is Displayed");
			excel.writetoexcel("OrganizationsTestData", "Create Organization With Industry And Type", "Pass");
		} else {
			driverUtil.quitAllWindows();
			excel.writetoexcel("OrganizationsTestData", "Create Organization With Industry And Type", "Fail");
		}

		excel.saveexcel(Iconstantpath.EXCEL_PATH);

		home.signOutOfVtiger(driverUtil);
		excel.closeexcel();
		driverUtil.quitAllWindows();
	}

}