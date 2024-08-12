package objectrepositories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * this class contains elements, locators and respective bussiness libraries of organoizations page
 */
public class organizationInformationPage {
	//Declaration
	@FindBy(css="span.dvHeaderText")
	private WebElement pageHeader;
	
	@FindBy(xpath="//input[@name='Delete']")
	private WebElement deleteBTN;
	
	//initilization
	public organizationInformationPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
}
	//utilization
	/**
	 * this method fetches the page header
	 * @return String
	 */
	public String getPageHeader() {
		return pageHeader.getText();
		
	}
	/**
	 * this method is used to click on delete button
	 */
	public void clickDeleteBTN() {
		deleteBTN.click();
	}
}