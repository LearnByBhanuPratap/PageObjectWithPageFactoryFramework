package com.test.automation.uiAutomation.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.uiAutomation.homepage.TC001_VerifyLoginWithInvalidCredentails;

public class HomePage {
	
	public static final Logger log = Logger.getLogger(HomePage.class.getName());
	WebDriver driver;
	
	@FindBy(xpath="//*[@id='header']/div[2]/div/div/nav/div[1]/a")
	WebElement signIn;
	
	@FindBy(xpath="//*[@id='email']")
	WebElement loginEmailAddress;
	
	@FindBy(xpath="//*[@id='passwd']")
	WebElement loginPassword;
	
	@FindBy(id="SubmitLogin")
	WebElement submitButton;
	
	@FindBy(xpath="//*[@id='center_column']/div[1]/ol/li")
	WebElement authenticationFailed;
	
	public HomePage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void loginToApplication(String emailAddress, String password){
		signIn.click();
		log.info("cliked on sign in and object is:-"+signIn.toString());
		loginEmailAddress.sendKeys(emailAddress);
		log.info("entered email address:-"+emailAddress+" and object is "+loginEmailAddress.toString());
		loginPassword.sendKeys(password);
		log.info("entered password:-"+password+" and object is "+loginPassword.toString());
		submitButton.click();
		log.info("clicked on sublit butto and object is:-"+submitButton.toString());
	}
	
	public String getInvalidLoginText(){
		log.info("erorr message is:-"+authenticationFailed.getText());
		return authenticationFailed.getText();
	}
	
	
}
