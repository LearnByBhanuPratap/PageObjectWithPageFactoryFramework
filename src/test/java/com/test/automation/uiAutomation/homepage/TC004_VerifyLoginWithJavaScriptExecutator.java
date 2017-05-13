package com.test.automation.uiAutomation.homepage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.HomePage;

public class TC004_VerifyLoginWithJavaScriptExecutator extends TestBase{
	HomePage homepage;
	@BeforeClass
	public void setUp() throws IOException {
      init();
	}
	//For Java Script Video
	//https://www.youtube.com/watch?v=4cr3GG54hVo&list=PL5NG-eEzvTtgd4vj_CWaJgWw2nj5L8SUP&index=6
	@Test
	public void testLogin() {
	homepage = new HomePage(driver);
	homepage.switchToFrame();
	driver.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='customer_login_link']")));
    driver.executeScript("arguments[0].value=\"testhgjfjhfgdjfh@gmail.com\";", driver.findElement(By.xpath(".//*[@id='CustomerEmail']")));
    driver.executeScript("arguments[0].value=\"testhgjfjhfgdjfh\";", driver.findElement(By.xpath(".//*[@id='CustomerPassword']")));
    driver.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='customer_login']/p[1]/input")));
    
	}

	@AfterClass
	public void endTest() {

	}

}
