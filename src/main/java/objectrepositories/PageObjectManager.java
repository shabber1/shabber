package objectrepositories;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {

//	LoginPage login;
//	HomePage home;
//	OrganizationsPage organization;
//	ContactsPage contacts;
//	LeadsPage leads;
//	CreateToDoPage createToDo;
//	CreatingNewOrganizationPage createOrg;
//	CreatingNewContactPage createContact;
//	CreatingNewLeadPage createLead;
//	OrganizationInformationPage orgInfo;
//	ContactInformationPage contactInfo;
//	LeadInformationPage leadInfo;
//	EventInformationPage eventInfo;
//	DuplicatingPage duplicateLead;
	
	WebDriver driver;
	
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage getLogin() {
		return new LoginPage(driver);
	}

	public HomePage getHome() {
		return new HomePage(driver);
	}

	public OrganizationsPage getOrganization() {
		return new OrganizationsPage(driver);
	}

	public ContactsPage getContacts() {
		return new ContactsPage(driver);
	}

	public LeadsPage getLeads() {
		return new LeadsPage(driver);
	}

	public CreateToDoPage getCreateToDo() {
		return new CreateToDoPage(driver);
	}

	public CreatingNewOrganizationPage getCreateOrg() {
		return new CreatingNewOrganizationPage(driver);
	}

	public CreatingNewContactsPage getCreateContact() {
		return new CreatingNewContactsPage(driver);
	}

	public CreatingNewLeadPage getCreateLead() {
		return new CreatingNewLeadPage(driver);
	}

	public organizationInformationPage getOrgInfo() {
		return new organizationInformationPage(driver);
	}

	public ContactInformationPage getContactInfo() {
		return new ContactInformationPage(driver);
	}

	public LeadInformationPage getLeadInfo() {
		return new LeadInformationPage(driver);
	}

	public EventInformationPage getEventInfo() {
		return new EventInformationPage(driver);
	}

	public DuplicatingPage getDuplicateLead() {
		return new DuplicatingPage(driver);
	}
}