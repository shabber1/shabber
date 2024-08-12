package TestngImplementation;

import java.util.Map;

import org.testng.annotations.Test;

import genericutilities.BaseClass;
import genericutilities.TabNames;
import objectrepositories.CreatingNewOrganizationPage;

import objectrepositories.organizationInformationPage;
import objectrepositories.OrganizationsPage;

public class CreateOrganizationTest extends BaseClass {

	@Test(groups="organizations")
	public void createOrgTest() {
		OrganizationsPage organization = pageObjectManager.getOrganization();
		CreatingNewOrganizationPage createOrg = pageObjectManager.getCreateOrg();
		organizationInformationPage orgInfo = pageObjectManager.getOrgInfo();
	
		home.clickRequiredTab(driverUtil, TabNames.ORGANIZATION);

		soft.assertTrue(driver.getTitle().contains("Organizations"));
			
		organization.clickCreateOrgBTN();

		soft.assertTrue(createOrg.getPageHeader().equalsIgnoreCase("creating new organization"));
			
		Map<String, String> map = excel.readfromexcel("OrganizationsTestData", "Create Organization");
		String orgName = map.get("Organization Name")+jutil.generateRandomNum(100);
		createOrg.setOrganizationName(orgName);
		createOrg.clickSaveBTN();

		soft.assertTrue(orgInfo.getPageHeader().contains(orgName));
			
		orgInfo.clickDeleteBTN();
		driverUtil.handleAlert("ok");
		soft.assertTrue(driver.getTitle().contains("Organizations"));
		if (driver.getTitle().contains("Organizations")) 
			excel.writetoexcel("OrganizationsTestData", "Create Organization", "Pass");
		 else 
			excel.writetoexcel("OrganizationsTestData", "Create Organization", "Fail");
		
	}

}