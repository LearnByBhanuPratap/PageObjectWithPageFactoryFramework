package com.test.automation.uiAutomation.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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
	
	@FindBy(xpath="//*[@id='customer_login_link']")
	WebElement loginLink;
	
	@FindBy(id="CustomerEmail")
	WebElement loginEmail;
	
	@FindBy(xpath=".//*[@id='CustomerPassword']")
	WebElement password;
	
	@FindBy(xpath=".//*[@id='customer_login']/p[1]/input")
	WebElement clickOnSignIn;
	
	@FindBy(xpath="//*[@id='customer_logout_link']")
	WebElement logout;
	
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

	public void registorUser(String firstName, String lastName, String emailAddress, String password){
		driver.switchTo().frame(homePageIframe);
		log.info("swithing to homepage frame and frame object is:-"+homePageIframe.toString());
		signUpLink.click();
		log.info("clicked on sign Up link and object is:-"+signUpLink.toString());
		this.firstName.clear();
		this.firstName.sendKeys(firstName);
		log.info("entered data to first name field and object is:-"+this.firstName.toString());
		this.lastName.clear();
		this.lastName.sendKeys(lastName);
		log.info("entered data to last name field and object is:-"+this.lastName.toString());
		email.clear();
		email.sendKeys(emailAddress);
		log.info("entered data to email field and object is:-"+email.toString());
		createPassword.clear();
		createPassword.sendKeys(password);
		log.info("entered data to password field and object is:-"+createPassword.toString());
		createAccount.click();
		log.info("clicked on craete and account and object is:-"+signUpLink.toString());
	}
	
	public boolean getRegistrationSuccess(){
		try {
			driver.findElement(By.xpath(".//*[@id='MainContent']/div/p")).isDisplayed();
			return true;
		} catch (Exception e) {
		   return false;
		}
	}
	
	public void loginToDemoSite(String emailAddress,String loginPassword){
		driver.switchTo().frame(homePageIframe);
		loginLink.click();
		loginEmail.sendKeys(emailAddress);
		password.sendKeys(loginPassword);
		clickOnSignIn.click();
		driver.switchTo().defaultContent();
	}
	
	public boolean verifyLogoutDisplay(){
		try {
			driver.switchTo().frame(homePageIframe);
			//waitForElement(300, logout);
			logout.isDisplayed();
			log.info("logout is dispalyed and object is:-"+logout.toString());
			driver.switchTo().defaultContent();
			return true;
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			return false;
		}
	}
	
	public void clickOnLogout() {
		driver.switchTo().frame(homePageIframe);
		//waitForElement(300, logout);
		logout.click();
		driver.switchTo().defaultContent();
		log.info("cliked on logout button and object is:-" + logout.toString());
	}
	
	public void clickOnNavigationMenu(String menuName){
		driver.findElement(By.xpath("//button[contains(text(),'"+menuName+"') and @aria-expanded='false'])")).click();
		log.info("clicked on:-"+menuName+" navigation menu");
	}
	
	public void clickOnProductInMensSection(String product){
		driver.findElement(By.xpath(".//button[contains(text(),'Mens') and @aria-expanded='true']/following-sibling::ul/child::li/child::a[contains(text(),'"+product+"')]")).click();
	}
	
	public void clickOnProductInWomensSection(String product){
		driver.findElement(By.xpath(".//button[contains(text(),'Womens') and @aria-expanded='true']/following-sibling::ul/child::li/child::a[contains(text(),'"+product+"')]")).click();
	}
	
}
