package objectrepositories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericutilities.TabNames;
import genericutilities.webdriverutility;

/**
 * This class contains elements, locators and respective business libraries of Home page
 * @author sncsr
 *
 */
public class HomePage {

	// Declaration
	private String commonPathForTabs = "//a[contains(@href,'%s&action=index')]";
	
	@FindBy(id = "qccombo")
	private WebElement quickCreateDD;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminWidget;
	
	@FindBy(xpath = "//a[text()='Sign Out']")
	private WebElement signOutLink;
	
	// Initialization
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	// Utilization
	
	public void clickRequiredTab(webdriverutility driverUtil, TabNames tabName) {
		driverUtil.convertDynamicXpathToWebElement(commonPathForTabs, tabName.getTabName()).click();
	}
	/**
	 * This method is used to sign out of vtiger
	 * @param driverUtil
	 */
	public void signOutOfVtiger(webdriverutility driverUtil) {
		driverUtil.mouseHover(adminWidget);
		signOutLink.click();
	}
	
	/**
	 * This method is used to select an option from quick create drop down
	 * @param driverUtil
	 * @param value
	 */
	public void selectFromQuickCreateDD(webdriverutility driverUtil, String value) {
		driverUtil.handleDropdown(quickCreateDD, value);
	}
}