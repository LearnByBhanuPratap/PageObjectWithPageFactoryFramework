package com.test.automation.uiAutomation.homepage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.HomePage_AutomationPractices;

public class TC001_VerifyLoginWithInvalidCredentailsToAutoMationPractices extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC001_VerifyLoginWithInvalidCredentailsToAutoMationPractices.class.getName());	

	HomePage_AutomationPractices homepage;
	
	@BeforeClass
	public void setUp() throws IOException{
     init();

	}
	
	@Test
	public void verifyLoginWithInvalidCredentails(){
		log.info("=========== Starting verifyLoginWithInvalidCredentails Test=============");
		homepage = new HomePage_AutomationPractices(driver);
		homepage.loginToApplication("test@gmail.com", "password123");
		Assert.assertEquals(homepage.getInvalidLoginText(), "Authentication failed.");
		log.info("=========== Finished verifyLoginWithInvalidCredentails Test=============");
	}
	
//	@AfterClass
//	public void endTest(){
//		driver.close();
//	}

}
