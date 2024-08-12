package TestngImplementation;

import java.util.Map;

import org.testng.annotations.Test;

import genericutilities.BaseClass;
import genericutilities.TabNames;
import objectrepositories.ContactInformationPage;
import objectrepositories.ContactsPage;
import objectrepositories.CreatingNewContactsPage;

public class CreateContactWithExistingOrgTest extends BaseClass{

	@Test(groups="contacts")
	public void createContact() {
		ContactsPage contact = pageObjectManager.getContacts();
		CreatingNewContactsPage createContact = pageObjectManager.getCreateContact();
		ContactInformationPage contactInfo = pageObjectManager.getContactInfo();
		
		home.clickRequiredTab(driverUtil, TabNames.CONTACTS);
		soft.assertTrue(driver.getTitle().contains("Contacts"));
			
		contact.clickCreateContactBTN();

		soft.assertTrue(createContact.getPageHeader().equalsIgnoreCase("Creating new contact"));
			
		Map<String, String> map = excel.readfromexcel("ContactsTestData", "Create Contact With Organization");
		
		createContact.setContactLastName(map.get("Last Name"));
		createContact.selectExistingOrganization(driverUtil, map.get("Organization Name"));

		createContact.clickSaveBTN();

		soft.assertTrue(contactInfo.getPageHeader().contains(map.get("Last Name")));
			
		contactInfo.clickDeleteBTN();
		driverUtil.handleAlert("ok");
		soft.assertTrue(driver.getTitle().contains("Contacts"));
		if(driver.getTitle().contains("Contacts")) 
			excel.writetoexcel("ContactsTestData", "Create Contact With Organization", "Pass");
		else 
			excel.writetoexcel("ContactsTestData", "Create Contact With Organization", "Fail");
		
	}

}