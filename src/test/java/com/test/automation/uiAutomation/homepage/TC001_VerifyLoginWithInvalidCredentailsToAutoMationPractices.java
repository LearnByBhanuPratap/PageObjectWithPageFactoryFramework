package com.test.automation.uiAutomation.homepage;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.HomePage;

public class TC001_VerifyLoginWithInvalidCredentailsToAutoMationPractices extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC001_VerifyLoginWithInvalidCredentailsToAutoMationPractices.class.getName());	

	HomePage homeapage;
	
	@BeforeTest
	public void setUp(){
     init();

	}
	
	@Test
	public void verifyLoginWithInvalidCredentails(){
		log.info("=========== Starting verifyLoginWithInvalidCredentails Test=============");
		homeapage = new HomePage(driver);
		homeapage.loginToApplication("test@gmail.com", "password123");
		Assert.assertEquals(homeapage.getInvalidLoginText(), "Authentication failed.");
		log.info("=========== Finished verifyLoginWithInvalidCredentails Test=============");
	}
	
	@AfterClass
	public void endTest(){
		driver.close();
	}

}
