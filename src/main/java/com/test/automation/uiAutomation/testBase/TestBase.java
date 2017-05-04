package com.test.automation.uiAutomation.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Properties;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.test.automation.uiAutomation.customListner.WebEventListener;
import com.test.automation.uiAutomation.excelReader.Excel_Reader;

/**
 * 
 * @author Bhanu Pratap
 *
 */
public class TestBase {
	
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
     public WebDriver dr;
     Excel_Reader excel;
     public EventFiringWebDriver driver;
     public WebEventListener eventListener;
     public Properties OR =  new Properties();
     public static ExtentReports extent;
 	 public static ExtentTest test;
 	 public ITestResult result;
     

     public  EventFiringWebDriver getDriver() {
		return driver;
	}
     
     static{
    	 Calendar calendar = Calendar.getInstance();
  		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
  		 extent = new ExtentReports(System.getProperty("user.dir") + "/src/main/java/com/test/automation/uiAutomation/report/test"+formater.format(calendar.getTime())+".html", false);
     }
     
     public void loadData() throws IOException{
    	 File file = new  File(System.getProperty("user.dir")+"/src/main/java/com/test/automation/uiAutomation/config/config.properties");
    	 FileInputStream f = new FileInputStream(file);
    	 OR.load(f);
    			 
     }


	public void setDriver(EventFiringWebDriver driver) {
		this.driver = driver;
	}


     
     public void init() throws IOException{
    	  loadData();
    	 
    	  String log4jConfPath = "log4j.properties";
    	  PropertyConfigurator.configure(log4jConfPath);
    	  System.out.println(OR.getProperty("browser"));
    	  selectBrowser(OR.getProperty("browser"));
    	  getUrl(OR.getProperty("url"));
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
			//driver.register(eventListener);
			//setDriver(driver);
			
			//For Window
			//System.setProperty("webdriver.gecko.driver ", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
		}
		else if(browser.equalsIgnoreCase("chrome")){
			//https://sites.google.com/a/chromium.org/chromedriver/downloads
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
			// For Windows system
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			//driver = new ChromeDriver();
			log.info("creating object of "+browser);
			dr = new ChromeDriver();
			driver = new EventFiringWebDriver(dr);
			//eventListener = new WebEventListener();
			//driver.register(eventListener);
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
	
	public String captureScreen(String fileName) {
		if(fileName==""){
			fileName="blank";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
 		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
 		
 			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
 			
 			try {
 				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/test/automation/uiAutomation/screenshot/";
 				destFile = new File((String) reportDirectory + fileName + "_" + formater.format(calendar.getTime()) + ".png");
 				FileUtils.copyFile(scrFile, destFile);
 				// This will help us to link the screen shot in testNG report
 				Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 			return destFile.toString();
	}
	
	public void log(String data){
		log.info(data);
		Reporter.log(data);
		test.log(LogStatus.INFO, data);
	}
	
	public void getresult(ITestResult result){
		if(result.getStatus()==ITestResult.SUCCESS){
			test.log(LogStatus.PASS,result.getName()+" test is pass");
		}
		else if(result.getStatus()==ITestResult.SKIP){
			test.log(LogStatus.SKIP,result.getName()+" test is skipped and skip reason is:-"+result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.FAILURE){
			test.log(LogStatus.ERROR,result.getName()+" test is failed"+ result.getThrowable());
			String screen = captureScreen("");
			test.log(LogStatus.FAIL,test.addScreenCapture(screen));
		}
		else if(result.getStatus()==ITestResult.STARTED){
			test.log(LogStatus.INFO,result.getName()+" test is started");
		}
	}
	
	@AfterMethod()
	public void afterMethod(ITestResult result){
		getresult(result);
	}
	
	@BeforeMethod()
	public void beforeMethod(Method result){
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName()+" test Started");
	}
	

	@AfterClass(alwaysRun=true)
	public void endTest() {
		closeBrowser();
	}
	
	public void closeBrowser(){
		driver.quit();
		log.info("browser closed");
		extent.endTest(test);
		extent.flush();
	}

  }

