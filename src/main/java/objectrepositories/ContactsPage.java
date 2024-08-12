package objectrepositories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * this class contains elements, locators and respective bussiness libraries of organoizations page
 */
public class ContactsPage {
	//Declaration
		@FindBy(xpath="//img[@alt='Create Contact...']")
		private WebElement createContactBTN;
		
		//Initilization
		public ContactsPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		//utilization
		/**
		 * this method clicks on the create contact button
		 */
		public void clickCreateContactBTN() {
			createContactBTN.click();
		}
}
