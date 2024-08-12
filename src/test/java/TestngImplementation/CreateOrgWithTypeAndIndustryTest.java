package TestngImplementation;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericutilities.BaseClass;
import genericutilities.TabNames;
import objectrepositories.CreatingNewOrganizationPage;
import objectrepositories.OrganizationsPage;
import objectrepositories.organizationInformationPage;

@Listeners(genericutilities.ListenerImplimentation.class)
public class CreateOrgWithTypeAndIndustryTest extends BaseClass {

	@Test(groups="organizations")
	public void createOrgWithTypeAndIndustryTest() {
		OrganizationsPage organization = pageObjectManager.getOrganization();
		CreatingNewOrganizationPage createOrg = pageObjectManager.getCreateOrg();
		organizationInformationPage orgInfo = pageObjectManager.getOrgInfo();

		home.clickRequiredTab(driverUtil, TabNames.ORGANIZATION);

		soft.assertTrue(driver.getTitle().contains("Organizations"));
			
		organization.clickCreateOrgBTN();

		soft.assertTrue(createOrg.getPageHeader().equalsIgnoreCase("creating new organization"));

		Map<String, String> map = excel.readfromexcel("OrganizationsTestData", "Create Organization With Industry And Type");
		
		String orgName = map.get("Organization Name")+jutil.generateRandomNum(100);
		createOrg.setOrganizationName(orgName);
		createOrg.selectFromIndustryDD(driverUtil, map.get("Industry"));
		createOrg.selectFromTypeDD(driverUtil, map.get("Type"));
		createOrg.clickSaveBTN();

		soft.assertTrue(orgInfo.getPageHeader().contains(orgName));
		
		orgInfo.clickDeleteBTN();
		driverUtil.handleAlert("ok");
		soft.assertTrue(driver.getTitle().contains("Organizations"));
		if (driver.getTitle().contains("Organizations")) 
			excel.writetoexcel("OrganizationsTestData", "Create Organization With Industry And Type", "Pass");
		 else 
			excel.writetoexcel("OrganizationsTestData", "Create Organization With Industry And Type", "Fail");
		
	}

}