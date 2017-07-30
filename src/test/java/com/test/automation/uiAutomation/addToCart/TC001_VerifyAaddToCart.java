package com.test.automation.uiAutomation.addToCart;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.HomePage;
import com.test.automation.uiAutomation.uiActions.ProductDetailsPage;

public class TC001_VerifyAaddToCart extends TestBase{
	HomePage homepage;
	ProductDetailsPage productDetailsPage;
	
	@BeforeClass
	public void setUp() throws IOException {
      init(); 
	}

	@Test
	public void verifyAaddToCart() {
		try {
			log.info("=======Starting verifyAaddToCart test========");
			homepage = new  HomePage(driver);
			homepage.switchToFrame();
			homepage.clickOnNavigationMenu(homepage.mens);
			homepage.clickOnProductInMensSection(homepage.jackets);
			productDetailsPage = new ProductDetailsPage(driver);
			productDetailsPage.selectProduct(productDetailsPage.contrast_jacket);
			homepage.switchToDefaultContent();
			log.info("=======Finished verifyAaddToCart test========");
			getScreenShot("verifyAaddToCart");
		} catch (Exception e) {
           getScreenShot("verifyAaddToCart");
		}
	}

//	@AfterClass
//	public void endTest() {
//      closeBrowser();
//	}

}
