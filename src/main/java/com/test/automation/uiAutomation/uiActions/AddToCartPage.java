package com.test.automation.uiAutomation.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.uiAutomation.testBase.TestBase;
/**
 * 
 * @author Bhanu Pratap
 *
 */
public class AddToCartPage  extends TestBase{

	public final static Logger log = Logger.getLogger(AddToCartPage.class.getName());
	WebDriver driver;

	@FindBy(xpath = "//*[@id='ProductSection']/div[1]/div[2]/div/div[2]/a[1]")
	WebElement facebookLink;

	@FindBy(xpath = "//*[@id='ProductSection']/div[1]/div[2]/div/div[2]/a[2]/svg")
	WebElement tweet;

	@FindBy(xpath = "//*[@id='ProductSection']/div[1]/div[2]/div/div[2]/a[3]")
	WebElement pinit;

	@FindBy(css = "AddToCart")
	WebElement addToCart;
	
	@FindBy(xpath="//*[contains(text(),'Log in to your Facebook account to share.')]")
	WebElement faceBookMessage;

	public AddToCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnfacebookLink() {
       facebookLink.click();
       log.info("clicked on facebook link and object is:-"+facebookLink.toString());
	}
	
	public boolean verifyFaceBookMessage(){
		try {
			faceBookMessage.isDisplayed();
			log.info("facebook page dispalyed and object is:-"+faceBookMessage.toString());
			return true;
		} catch (Exception e) {
            return false;
		}
	}

	public void tweet() {
		tweet.click();
		log.info("clicked on tweet link and object is:-"+tweet.toString());
	}

}
