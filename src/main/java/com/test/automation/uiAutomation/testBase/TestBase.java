package com.test.automation.uiAutomation.testBase;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {
	
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
     public WebDriver driver;
     String url = "file:///Users/bsingh5/Desktop/demoSite.htm";
     String browser = "firefox";
     
     public void init(){
    	  selectBrowser(browser);
    	  getUrl(url);
    	  String log4jConfPath = "log4j.properties";
    	  PropertyConfigurator.configure(log4jConfPath);
     }
	
	
     public void selectBrowser(String browser){
		if(browser.equalsIgnoreCase("firefox")){
			//https://github.com/mozilla/geckodriver/releasess
			// For Mac os
			System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "/drivers/geckodriver");
			log.info("creating object of "+browser);
			driver = new FirefoxDriver();
			//For Window
			//System.setProperty("webdriver.gecko.driver ", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
		}
		else if(browser.equalsIgnoreCase("chrome")){
			//https://sites.google.com/a/chromium.org/chromedriver/downloads
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
	}
     
     public void getUrl(String url){
    	 log.info("navigating to :-"+url);
    	 driver.get(url);
    	 driver.manage().window().maximize();
    	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     }
}
