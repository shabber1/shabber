package TestngImplementation;

import java.util.Map;

import org.testng.annotations.Test;

import genericutilities.BaseClass;
import objectrepositories.CreateToDoPage;
import objectrepositories.EventInformationPage;

public class CreateEventTest extends BaseClass {

	@Test(groups="events")
	public void createEventTest() {		
		CreateToDoPage createToDo = pageObjectManager.getCreateToDo();
		EventInformationPage eventInfo = pageObjectManager.getEventInfo();
		
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
		soft.assertTrue(eventInfo.getPageHeader().contains(subject));
		if (eventInfo.getPageHeader().contains(subject)) 
			excel.writetoexcel("EventsTestData", "Create New Event", "Pass");
		 else 
			excel.writetoexcel("EventsTestData", "Create New Event", "Fail");

		eventInfo.clickDeleteBTN();
		driverUtil.handleAlert("ok");
	}

}