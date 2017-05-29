package com.test.automation.uiAutomation.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.test.automation.uiAutomation.testBase.TestBase;
/**
 * 
 * @author bsingh5
 *
 */

public class HomePage_AutomationPractices extends TestBase {
	
	
	WebDriver driver;
	public final String tshirts = "T-shirts";
	public final String blouses = "Blouses";
	public final String casualDresses = "Casual Dresses";
	
	public final String women = "Women";


	private final static Logger log = Logger.getLogger(HomePage_AutomationPractices.class.getName());

	@FindBy(css=".login")
	WebElement signIn;

	@FindBy(id = "email")
	WebElement emailAddress;

	@FindBy(id = "passwd")
	WebElement passowrd;

	@FindBy(xpath = "//*[@id='SubmitLogin']")
	WebElement submitButton;

	public HomePage_AutomationPractices(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnsignIn() {
		signIn.click();
		log.info("click on sign in and object is:-" + signIn.toString());
	}

	public void enterEmailAddress(String emailAddress) {
		this.emailAddress.sendKeys(emailAddress);
	}

	public void enterPassword(String password) {
		this.passowrd.sendKeys(password);
	}

	public void clickOnSubmitButton() {
		submitButton.click();
	}

	public String getInvalidLoginText() {
		String text = driver.findElement(By.xpath(".//*[@id='center_column']/div[1]/ol/li")).getText();
		return text;
	}

	public void loginToApplication(String emailAddress, String password) {
		clickOnsignIn();
		enterEmailAddress(emailAddress);
		enterPassword(password);
		clickOnSubmitButton();
	}
	
	public void navigateTo(String data){
		String locator = "//a[contains(text(),'"+data+"')]";
		driver.findElement(By.xpath(locator)).click();
	}
	
	public void mouseOver(String data) throws InterruptedException{
		String locator = "//a[contains(text(),'"+data+"')]";
		Actions select = new Actions(driver);
		select.moveToElement(driver.findElement(By.xpath(locator))).build().perform();
		Thread.sleep(1000);
	}
	
	public void clickOnProduct(String data){
		String locator = "//a[contains(text(),'"+data+"')]";
		driver.findElement(By.xpath(locator)).click();
	}

}
