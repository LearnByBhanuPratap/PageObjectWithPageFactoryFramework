package com.test.automation.uiAutomation.homepage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.HomePage;

public class TC002_VerifyRegistration extends TestBase{

	HomePage homepage;
	String firstName = "test";
	String lastName = "lastTest";
	String emailAddress = "automation@gmail.com";
	String password = "password";
	
	@BeforeClass
	public void setUp() {
      init();
	}

	@Test
	public void testLogin() {
		homepage = new HomePage(driver);
		homepage.registorUser(firstName, lastName, emailAddress, password);
		homepage.getRegistrationSuccessMessage();
		//Assert.assertEquals(arg0, homepage.getRigistrationSuccessMessage());
	}

	@AfterClass
	public void endTest() {
        driver.quit();
	}
}
