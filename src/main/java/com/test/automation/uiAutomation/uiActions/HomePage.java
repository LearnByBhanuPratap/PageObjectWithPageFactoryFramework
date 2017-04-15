package com.test.automation.uiAutomation.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.uiAutomation.testBase.TestBase;

public class HomePage extends TestBase{
	
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
	
	@FindBy(id="PreviewFrame")
	WebElement homePageIframe;
	
	@FindBy(id="customer_register_link")
	WebElement signUpLink;
	
	@FindBy(id="FirstName")
	WebElement firstName;
	
	@FindBy(id="LastName")
	WebElement lastName;
	
	@FindBy(id="Email")
	WebElement email;
	
	@FindBy(id="CreatePassword")
	WebElement createPassword;
	
	@FindBy(css="input.btn")
	WebElement createAccount;
	
	@FindBy(xpath="//*[@id='shopify-section-header']/div/div[2]/span")
	WebElement registrationMessage;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
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
	
	public void loginToDemoSite(){
		
	}
	
	public void registorUser(String firstName, String lastName, String emailAddress, String password){
		driver.switchTo().frame(homePageIframe);
		log.info("swithing to homepage frame and frame object is:-"+homePageIframe.toString());
		signUpLink.click();
		log.info("clicked on sign Up link and object is:-"+signUpLink.toString());
		this.firstName.clear();
		this.firstName.sendKeys("test");
		log.info("entered data to first name field and object is:-"+this.firstName.toString());
		this.lastName.clear();
		this.lastName.sendKeys("testlast");
		log.info("entered data to last name field and object is:-"+this.lastName.toString());
		email.clear();
		email.sendKeys("automation@gmail.com");
		log.info("entered data to email field and object is:-"+email.toString());
		createPassword.clear();
		createPassword.sendKeys("password");
		log.info("entered data to password field and object is:-"+createPassword.toString());
		createAccount.click();
		log.info("clicked on craete and account and object is:-"+signUpLink.toString());
	}
	
	public String getRegistrationSuccessMessage(){
		log.info("registratiom message is:-"+registrationMessage.getText());
		return registrationMessage.getText();
		
	}
	
	
}
