package com.test.automation.uiAutomation.testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.test.automation.uiAutomation.customListner.Listener;
import com.test.automation.uiAutomation.customListner.WebEventListener;
import com.test.automation.uiAutomation.excelReader.Excel_Reader;

public class TestBase {
	
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
     public WebDriver dr;
     String url = "file:///Users/bsingh5/Desktop/demoSite.htm";
     String browser = "firefox";
     Excel_Reader excel;
     public EventFiringWebDriver eventdriver;
     public EventFiringWebDriver driver;
     public WebEventListener eventListener;
     

     public  EventFiringWebDriver getDriver() {
		return driver;
	}


	public void setDriver(EventFiringWebDriver driver) {
		this.driver = driver;
	}


     
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
			dr = new FirefoxDriver();
			driver = new EventFiringWebDriver(dr);
			eventListener = new WebEventListener();
			driver.register(eventListener);
			//setDriver(driver);
			
			//For Window
			//System.setProperty("webdriver.gecko.driver ", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
		}
		else if(browser.equalsIgnoreCase("chrome")){
			//https://sites.google.com/a/chromium.org/chromedriver/downloads
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			//driver = new ChromeDriver();
		}
	}
     
     public void getUrl(String url){
    	 log.info("navigating to :-"+url);
    	 driver.get(url);
    	 driver.manage().window().maximize();
    	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     }
     
     public String[][] getData(String excelName, String sheetName){
    	 String path = System.getProperty("user.dir")+"/src/main/java/com/test/automation/uiAutomation/data/"+excelName;
    	 excel = new Excel_Reader(path);
    	 String[][] data = excel.getDataFromSheet(sheetName, excelName);
    	 return data;
     }
     
     public void waitForElement(WebDriver driver, int timeOutInSeconds, WebElement element){
    	 WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
    	 wait.until(ExpectedConditions.visibilityOf(element));
     }
     
     public void getScreenShot(String name){
    	 
    	Calendar calendar = Calendar.getInstance();
 		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
 		
 			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
 			
 			try {
 				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/test/automation/uiAutomation/screenshot/";
 				File destFile = new File((String) reportDirectory + name + "_" + formater.format(calendar.getTime()) + ".png");
 				FileUtils.copyFile(scrFile, destFile);
 				// This will help us to link the screen shot in testNG report
 				Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 		}
     
	public static void highlightMe(WebDriver driver, WebElement element) throws InterruptedException {
		// Creating JavaScriptExecuter Interface
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Execute javascript
		js.executeScript("arguments[0].style.border='4px solid yellow'", element);
		Thread.sleep(3000);
		js.executeScript("arguments[0].style.border=''", element);
	}
	
	public void closeBrowser(){
		driver.close();
		log.info("browser closed");
	}
	
	public Iterator<String> getAllWindows(){
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		return itr;
	}
	
	public void getScreenShot(WebDriver driver, ITestResult result, String folderName){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		String methodName = result.getName();

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/test/automation/uiAutomation/";
			File destFile = new File((String) reportDirectory + "/"+folderName+"/" + methodName + "_" + formater.format(calendar.getTime()) + ".png");
			
			FileUtils.copyFile(scrFile, destFile);
			
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getScreenShotOnSucess(WebDriver driver, ITestResult result){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		String methodName = result.getName();

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/test/automation/uiAutomation/";
			File destFile = new File((String) reportDirectory + "/failure_screenshots/" + methodName + "_" + formater.format(calendar.getTime()) + ".png");
			
			FileUtils.copyFile(scrFile, destFile);
			
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

  }

