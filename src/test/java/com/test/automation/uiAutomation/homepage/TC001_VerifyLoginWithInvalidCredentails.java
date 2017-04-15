package com.test.automation.uiAutomation.homepage;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.HomePage;

public class TC001_VerifyLoginWithInvalidCredentails extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC001_VerifyLoginWithInvalidCredentails.class.getName());	

	HomePage homapage;
	
	@BeforeTest
	public void setUp(){
     init();

	}
	
	@Test
	public void verifyLoginWithInvalidCredentails(){
		log.info("=========== Starting verifyLoginWithInvalidCredentails Test=============");
		homapage = new HomePage(driver);
		homapage.loginToApplication("test@gmail.com", "password123");
		Assert.assertEquals(homapage.getInvalidLoginText(), "Authentication failed.");
		log.info("=========== Finished verifyLoginWithInvalidCredentails Test=============");
	}
	
	@AfterClass
	public void endTest(){
		driver.close();
	}

}
