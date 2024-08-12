package PomImplimentation;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import genericutilities.DataType;
import genericutilities.Excelutility;
import genericutilities.Iconstantpath;
import genericutilities.javaUtility;
import genericutilities.propertiesUtility;
import genericutilities.webdriverutility;
import objectrepositories.CreateToDoPage;
import objectrepositories.EventInformationPage;
import objectrepositories.HomePage;
import objectrepositories.LoginPage;

public class CreateEventTest {

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
		CreateToDoPage createToDo = new CreateToDoPage(driver);
		EventInformationPage eventInfo = new EventInformationPage(driver);
		
		if (driver.getTitle().contains("vtiger CRM"))
			System.out.println("Login Page Displayed");
		else
			driverUtil.quitAllWindows();

		login.loginToVtiger(propertyUtil.readfromperperties("username"), propertyUtil.readfromperperties("password"));

		if (driver.getTitle().contains("Home"))
			System.out.println("Home Page is Displayed");
		else
			driverUtil.quitAllWindows();

		Map<String, String> map = excel.readfromexcel("EventsTestData", "Create New Event");
		home.selectFromQuickCreateDD(driverUtil, map.get("Quick Create"));
		
		jutil.waiting(3000);

		String subject = map.get("Subject") + jutil.generateRandomNum(100);
		createToDo.setSubject(subject);
		createToDo.clickStartDateWidget();

		createToDo.datePicker(jutil, driverUtil, map.get("Start Date"));
		jutil.waiting(2000);

		createToDo.clickDueDateWidget();
		createToDo.datePicker(jutil, driverUtil, map.get("Due Date"));

		createToDo.clickSaveBTN();
		
		if (eventInfo.getPageHeader().contains(subject)) {
			System.out.println("Event Created");
			excel.writetoexcel("EventsTestData", "Create New Event", "Pass");
		} else {
			System.out.println("Event Not Created");
			excel.writetoexcel("EventsTestData", "Create New Event", "Fail");
		}

		eventInfo.clickDeleteBTN();
		driverUtil.handleAlert("ok");
		
		excel.saveexcel(Iconstantpath.EXCEL_PATH);

		home.signOutOfVtiger(driverUtil);
		excel.closeexcel();

		driverUtil.quitAllWindows();
	}

}