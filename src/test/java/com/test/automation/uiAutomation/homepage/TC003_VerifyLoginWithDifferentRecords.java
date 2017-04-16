package com.test.automation.uiAutomation.homepage;

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

	HomePage homapage;

	@DataProvider(name="loginData")
	public String[][] getTestData(){
		String[][] testRecords = getData("TestData.xlsx", "LoginTestData");
		return testRecords;
	}
	

	@BeforeClass
	public void setUp() {
     init();
	}

	@Test(dataProvider="loginData")
	public void testLogin(String emailAddress, String loginPassword, String runMode) {
		if(runMode.equalsIgnoreCase("n")){
			throw new SkipException("user marked this record as no run");
		}
		log.info("============= Strting VerifyLoginWithDifferentRecords Test===========");
		homapage = new HomePage(driver);
		homapage.loginToDemoSite(emailAddress, loginPassword);
		boolean status = homapage.verifyLogoutDisplay();
		if(status){
			homapage.clickOnLogout();
		}
		Assert.assertEquals(status, true);
		log.info("============= Finished VerifyLoginWithDifferentRecords Test===========");
	}

	@AfterClass
	public void endTest() {
       driver.close();
	}
}
