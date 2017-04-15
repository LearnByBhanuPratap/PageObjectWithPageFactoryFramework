package com.test.automation.uiAutomation.testBase;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {
	
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
     public WebDriver driver;
     String url = "http://automationpractice.com/index.php";
     String browser = "firefox";
     
     public void init(){
    	  selectBrowser(browser);
    	  getUrl(url);
    	  String log4jConfPath = "log4j.properties";
    	  PropertyConfigurator.configure(log4jConfPath);
     }
	
	
     public void selectBrowser(String browser){
		if(browser.equalsIgnoreCase("firefox")){
			// For Mac os
			System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "/drivers/geckodriver");
			log.info("creating object of "+browser);
			driver = new FirefoxDriver();
			//For Window
			//System.setProperty("webdriver.gecko.driver ", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
			
		}
	}
     
     public void getUrl(String url){
    	 log.info("navigating to :-"+url);
    	 driver.get(url);
    	 driver.manage().window().maximize();
    	 driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
     }
}
