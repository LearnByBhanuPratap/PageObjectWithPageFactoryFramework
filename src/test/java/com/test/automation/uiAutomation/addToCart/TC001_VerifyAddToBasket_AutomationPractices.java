package com.test.automation.uiAutomation.addToCart;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.HomePage_AutomationPractices;
import com.test.automation.uiAutomation.uiActions.ProductDetails_AutomationPractices;


public class TC001_VerifyAddToBasket_AutomationPractices extends TestBase{
	HomePage_AutomationPractices homePage;
	ProductDetails_AutomationPractices productDetails;
	
	@BeforeClass
	public void setUp() throws IOException{
		init();
	}
	
	@Test
	public void verifyAddToBasket() throws InterruptedException{
		homePage = new HomePage_AutomationPractices(driver);
		productDetails = new ProductDetails_AutomationPractices(driver);
		
		try {
			homePage.mouseOver(homePage.women);
			homePage.clickOnProduct("T-shirts");
			productDetails.clickOnProduct("Faded Short Sleeve T-shirts");
			productDetails.clickOnProceedTocheckout();
		} 
		
		catch (AssertionError e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
