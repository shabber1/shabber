package objectrepositories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EventInformationPage {
	//declaration
	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement pageHeader;
	
	@FindBy(xpath="//input[@name='Delete']")
	private WebElement deleteBTN;
	
	//initilization
	public EventInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//utilization
	public String getPageHeader() {
		return pageHeader.getText();
	}
	public void clickDeleteBTN() {
		deleteBTN.click();
	}
}
