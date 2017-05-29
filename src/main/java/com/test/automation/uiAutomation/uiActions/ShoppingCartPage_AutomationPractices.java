package com.test.automation.uiAutomation.uiActions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.uiAutomation.testBase.TestBase;

/**
 * 
 * @author bsingh5
 *
 */
public class ShoppingCartPage_AutomationPractices extends TestBase{
	
	WebDriver driver;

	public ShoppingCartPage_AutomationPractices(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public ArrayList<String> getPriceOfProductsFromShoppingCart() {

		ArrayList<String> prices = new ArrayList<String>();
		waitForElement(driver,driver.findElement(By.xpath("//*[@id='cart_summary']/tbody/tr")), 120);
		int rows = driver.findElements(By.xpath(".//*[@id='cart_summary']/tbody/tr")).size();
		for (int i = 1; i <= rows; i++) {

			String part1 = ".//*[@id='cart_summary']/tbody/tr[";
			String part2 = "]/td[6]";

			String finalPart = part1 + i + part2;

			String p = driver.findElement(By.xpath(finalPart)).getText();
			prices.add(p.substring(1));
		}
		return prices;
	}

	public void delectProductFromShoppingCart() throws InterruptedException {
		// Delete all items from cart
		List<WebElement> deletes = driver.findElements(By.xpath("//a[@class='cart_quantity_delete']"));
		Iterator<WebElement> itr = deletes.iterator();
		while (itr.hasNext()) {
			itr.next().click();
			Thread.sleep(2000);
		}
	}
	
	public boolean verifyEmptyShoppingCartMesssage(){
		try {
			driver.findElement(By.xpath(".//*[contains(text(),'Your shopping cart is empty')]")).isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
