package objectrepositories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

		/**
		 * this class contains elements, locators and respective bussiness libraries of organoizations page
		 */
public class OrganizationsPage {
	//Declaration
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement createOrgBTN;
	
	//Initilization
	public OrganizationsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//utilization
	/**
	 * this method clicks on the create organization button
	 */
	public void clickCreateOrgBTN() {
		createOrgBTN.click();
	}
}
