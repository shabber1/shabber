package TestngImplementation;

import java.util.Map;

import org.testng.annotations.Test;

import genericutilities.BaseClass;
import genericutilities.TabNames;
import objectrepositories.CreatingNewLeadPage;
import objectrepositories.DuplicatingPage;
import objectrepositories.LeadInformationPage;
import objectrepositories.LeadsPage;

public class CreateAndDuplicateLeadTest extends BaseClass {

	@Test(groups="leads")
	public void createAndDuplicateLeadTest() {
		LeadsPage leads = pageObjectManager.getLeads();
		CreatingNewLeadPage createLead = pageObjectManager.getCreateLead();
		DuplicatingPage duplicateLead = pageObjectManager.getDuplicateLead();
		LeadInformationPage leadInfo = pageObjectManager.getLeadInfo();

		home.clickRequiredTab(driverUtil, TabNames.LEADS);

		soft.assertTrue(driver.getTitle().contains("Leads"));

		leads.clickCreateLeadBTN();

		soft.assertTrue(createLead.getPageHeader().equalsIgnoreCase("creating new lead"));

		Map<String, String> map = excel.readfromexcel("LeadsTestData", "Create and Duplicate Lead");
		String lastName = map.get("Last Name") + jutil.generateRandomNum(100);
		createLead.setLeadLastName(lastName);
		createLead.setCompanyName(map.get("Company"));
		createLead.clickSaveBTN();

		soft.assertTrue(leadInfo.getPageHeader().contains(lastName));

		leadInfo.clickDuplicateBTN();
		soft.assertTrue(duplicateLead.getPageHeader().contains("Duplicating"));
			
		String newLastName = map.get("New Last Name") + jutil.generateRandomNum(100);
		duplicateLead.setLeadLastName(newLastName);
		duplicateLead.clickSaveBTN();
		
		soft.assertTrue(leadInfo.getPageHeader().contains(newLastName));
		if (leadInfo.getPageHeader().contains(newLastName))
			excel.writetoexcel("LeadsTestData", "Create and Duplicate Lead", "Pass");
			else 
			excel.writetoexcel("LeadsTestData", "Create and Duplicate Lead", "Fail");
		
	}

}