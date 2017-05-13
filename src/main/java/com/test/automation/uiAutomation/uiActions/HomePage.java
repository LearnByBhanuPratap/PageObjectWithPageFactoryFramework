package com.test.automation.uiAutomation.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.test.automation.uiAutomation.testBase.TestBase;
/**
 * 
 * @author Bhanu Pratap
 *
 */
public class HomePage extends TestBase{
	
	public final String mens = "Mens";
	public final String womens = "Womens";
	public final String blog = "Blog";
	
	public final String jackets = "Jackets";
	public final String pants = "Pants";
	
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
		//testBase = new TestBase();
		PageFactory.initElements(driver, this);
	}
	
	public void loginToApplication(String emailAddress, String password){
		signIn.click();
		log("cliked on sign in and object is:-"+signIn.toString());
		loginEmailAddress.sendKeys(emailAddress);
		log("entered email address:-"+emailAddress+" and object is "+loginEmailAddress.toString());
		loginPassword.sendKeys(password);
		log("entered password:-"+password+" and object is "+loginPassword.toString());
		submitButton.click();
		log("clicked on sublit butto and object is:-"+submitButton.toString());
	}
	
	public String getInvalidLoginText(){
		log("erorr message is:-"+authenticationFailed.getText());
		return authenticationFailed.getText();
	}

	public void registorUser(String firstName, String lastName, String emailAddress, String password){
		signUpLink.click();
		log("clicked on sign Up link and object is:-"+signUpLink.toString());
		
		this.firstName.clear();
		this.firstName.sendKeys(firstName);
		log("entered data to first name field and object is:-"+this.firstName.toString());
		this.lastName.clear();
		this.lastName.sendKeys(lastName);
		log("entered data to last name field and object is:-"+this.lastName.toString());
		email.clear();
		email.sendKeys(emailAddress);
		log("entered data to email field and object is:-"+email.toString());
		createPassword.clear();
		createPassword.sendKeys(password);
		log("entered data to password field and object is:-"+createPassword.toString());
		createAccount.click();
		log("clicked on craete and account and object is:-"+signUpLink.toString());
	}
	
	public boolean getRegistrationSuccess(){
		try {
			driver.findElement(By.xpath("//*[@id='MainContent']/div/p")).isDisplayed();
			return true;
		} catch (Exception e) {
		   return false;
		}
	}
	
	public void loginToDemoSite(String emailAddress,String loginPassword){
		loginLink.click();
		loginEmail.sendKeys(emailAddress);
		password.sendKeys(loginPassword);
		clickOnSignIn.click();
	}
	
	public boolean verifyLogoutDisplay(){
		try {
			logout.isDisplayed();
			log("logout is dispalyed and object is:-"+logout.toString());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickOnLogout() {
		logout.click();
	}
	
	public void clickOnNavigationMenu(String menuName){
		driver.findElement(By.xpath("//button[contains(text(),'"+menuName+"') and @aria-expanded='false']")).click();
		log("clicked on:-"+menuName+" navigation menu");
	}
	
	public void clickOnProductInMensSection(String product){
		
		waitForElement(driver, 80, driver.findElement(By.xpath(".//button[contains(text(),'Mens') and @aria-expanded='true']/following-sibling::ul/child::li/child::a[contains(text(),'"+product+"')]")));
		driver.findElement(By.xpath(".//button[contains(text(),'Mens') and @aria-expanded='true']/following-sibling::ul/child::li/child::a[contains(text(),'"+product+"')]")).click();
		log("clicked on:-"+product);
	}
	
	public void clickOnProductInWomensSection(String product){
		waitForElement(driver, 80, driver.findElement(By.xpath(".//button[contains(text(),'Womens') and @aria-expanded='true']/following-sibling::ul/child::li/child::a[contains(text(),'"+product+"')]")));
		driver.findElement(By.xpath(".//button[contains(text(),'Womens') and @aria-expanded='true']/following-sibling::ul/child::li/child::a[contains(text(),'"+product+"')]")).click();
	}
	
	public void switchToFrame(){
		driver.switchTo().frame(homePageIframe);
		log("switched to the iframe");
	}
	
	public void switchToDefaultContent(){
		driver.switchTo().defaultContent();
		log("switched to the default Content");
	}
	
	public void log(String data){
		log.info(data);
		Reporter.log(data);
	}
	
}
