package com.test.automation.uiAutomation.addToCart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;

public class TC003_VerifyMultipleWindows extends TestBase {

	List<String> windowIds = new ArrayList<String>();

	@BeforeClass
	public void setUp() {

	}
	
	/*Dear sir, thanks for your valuable teaching towards us. 
	 * Sir I have done software testing and in an interview the interview
	 * asked me a question and I want to know fully step by step answer method of this question. 
	 * The question is - assume that you opened a browser and in the browser window you have opened 8 tabs, 
	 * you are in the 1st tab so you need to go to  4th tab  and in this tab there is a login page where user name , 
	 * password, and submit button is available. What you need to do is you will give the user name , 
	 * password and click on suit button. After submitting the submit button one new window will 
	 * open and you don't need to do anything just in the top of the window logout button is present. 
	 * click on it and it will return to the previous page. anain you need to do the same thing for 10 users.
	 *  after that in this same tab perform a open new tab and enter Google.com and close the browser. 
	 *  Please i want a video to perform this script step by step method and right click action so that I will clear property. 
	 */

	@Test
	public void testLogin() {
		
		for (int i = 0; i < 10; i++) {
			// After clicking on link call getWindowHandles(), it will get all window ids,
			// Store all IDs in array list
			Set<String> allwindows = driver.getWindowHandles();
			List<String> windowIds = new ArrayList<String>();
			Iterator<String> itr = allwindows.iterator();
			while (itr.hasNext()) {
				windowIds.add(itr.next());
			}
			// switch to 4th window and do sing in.
			driver.switchTo().window(windowIds.get(4));
			driver.findElement(By.xpath("")).sendKeys("userName");
			driver.findElement(By.xpath("")).sendKeys("passowrd");
			driver.findElement(By.xpath("")).sendKeys(Keys.ENTER);
			// After clicking on submit button again get all windows id by calling getWindowHandles();
			Set<String> allwins = driver.getWindowHandles();
			List<String> winIds = new ArrayList<String>();
			Iterator<String> itr1 = allwins.iterator();
			while (itr1.hasNext()) {
				winIds.add(itr.next());
			}
			// Now switch to child window do log out.
			driver.switchTo().window(winIds.get(1));
			driver.findElement(By.xpath("logout")).click();
			// again switch back to parent window from second array list
			driver.switchTo().window(winIds.get(0));
			driver.get("url");
			
			new Actions(driver).contextClick();
		}
		
	}

	@AfterClass
	public void endTest() {

	}

}
