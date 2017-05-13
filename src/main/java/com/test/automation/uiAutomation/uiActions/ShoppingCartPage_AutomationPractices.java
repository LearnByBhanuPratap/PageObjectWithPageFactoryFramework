package com.test.automation.uiAutomation.uiActions;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.test.automation.uiAutomation.testBase.TestBase;


public class ShoppingCartPage_AutomationPractices extends TestBase{
	
	public void clickProceedToCheckout(){
	  driver.findElement(By.xpath(".//*[@id='center_column']/p[2]/a[1]/span")).click();
	}
	
	public void deleteAllItemsFromShoppingCart() throws InterruptedException{
		List<WebElement> items = driver.findElements(By.xpath("//a[@title='Delete']"));
		Iterator<WebElement> itr = items.iterator();
		while(itr.hasNext()){
			itr.next().click();
			Thread.sleep(2000);
		}
	}
	
	public void clickOnContinueShopping(){
		driver.findElement(By.xpath(".//*[@id='center_column']/p[2]/a[2]")).click();
	}

}
