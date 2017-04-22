package com.test.automation.uiAutomation.addToCart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;

public class TC003_VerifyMultipleWindows extends TestBase{
	
	List<String> windowIds = new ArrayList<String>();
	
	@BeforeClass
	public void setUp() {

	}

	@Test
	public void testLogin() {
      Iterator<String> itr = getAllWindows();
      while(itr.hasNext()){
    	  windowIds.add(itr.next());
      }
      driver.switchTo().window(windowIds.get(6));
      
      driver.switchTo().window(windowIds.get(4));
      
      driver.switchTo().window(windowIds.get(0));
	}

	@AfterClass
	public void endTest() {

	}


}
