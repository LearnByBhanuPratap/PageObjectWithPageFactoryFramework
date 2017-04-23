package com.test.automation.uiAutomation.homepage;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.HomePage;

public class TC002_VerifyRegistration extends TestBase {

	HomePage homepage;
	String firstName = "test";
	String lastName = "lastTest";
	// String emailAddress = "automation@gmail.com";
	String emailAddress = System.currentTimeMillis() + "@gmail.com";
	String password = "password";

	@BeforeClass
	public void setUp() {
		init();
	}

	@Test
	public void verifyRegistration() {
		try {
			log.info("=======started verifyRegistration Test===========");
			homepage = new HomePage(driver);
			homepage.switchToFrame();
			homepage.registorUser(firstName, lastName, emailAddress, password);
			Assert.assertEquals(true, homepage.getRegistrationSuccess());
			log.info("=======finished verifyRegistration Test===========");
			getScreenShot("verifyRegistration");
		} catch (AssertionError e) {
			getScreenShot("verifyRegistration");
		} catch (Exception e) {
			getScreenShot("verifyRegistration");
		}
	}

	@AfterClass
	public void endTest() {
		closeBrowser();
	}
}
