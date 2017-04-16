package com.test.automation.uiAutomation.homepage;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.HomePage;

public class TC003_VerifyLoginWithDifferentRecords extends TestBase{

	public static final Logger log = Logger.getLogger(TC003_VerifyLoginWithDifferentRecords.class.getName());	

	HomePage homapage;
	String emailAddress = "automation@gmail.com";
	String loginPassword = "password";
	

	@BeforeClass
	public void setUp() {
     init();
	}

	@Test
	public void testLogin() {
		log.info("============= Strting VerifyLoginWithDifferentRecords Test===========");
		homapage = new HomePage(driver);
		homapage.loginToDemoSite(emailAddress, loginPassword);
		log.info("============= Finished VerifyLoginWithDifferentRecords Test===========");
	}

	@AfterClass
	public void endTest() {
       driver.close();
	}
}
