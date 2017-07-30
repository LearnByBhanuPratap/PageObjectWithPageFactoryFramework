package com.test.automation.uiAutomation.homepage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.HomePage;

public class TC003_VerifyLoginWithDifferentRecords extends TestBase{

	public static final Logger log = Logger.getLogger(TC003_VerifyLoginWithDifferentRecords.class.getName());	

	HomePage homepage;

	@DataProvider(name="loginData")
	public String[][] getTestData(){
		String[][] testRecords = getData("TestData.xlsx", "LoginTestData");
		return testRecords;
	}
	

	@BeforeClass
	public void setUp() throws IOException {
     init();
     homepage = new HomePage(driver);
	}

	@Test(dataProvider="loginData")
	public void verifyLoginWithDifferentRecords(String emailAddress, String loginPassword, String runMode) {
		homepage = new HomePage(driver);
		if(runMode.equalsIgnoreCase("n")){
			throw new SkipException("user marked this record as no run");
		}
		try {
			log.info("============= Strting VerifyLoginWithDifferentRecords Test===========");
			homepage.switchToFrame();
			homepage.loginToDemoSite(emailAddress, loginPassword);
			boolean status = homepage.verifyLogoutDisplay();
			if(status){
				homepage.clickOnLogout();
				homepage.switchToDefaultContent();
			}
			Assert.assertEquals(status, true);
			log.info("============= Finished VerifyLoginWithDifferentRecords Test===========");
			getScreenShot("verifyLoginWithDifferentRecords");
		} catch (Exception e) {
			getScreenShot("verifyLoginWithDifferentRecords");
		}
	}

//	@AfterClass
//	public void endTest() {
//       driver.close();
//	}
}
