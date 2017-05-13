package com.test.automation.uiAutomation.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.test.automation.uiAutomation.testBase.TestBase;


public class ProductDetails_AutomationPractices extends TestBase {
	
	private final static Logger log = Logger.getLogger(ProductDetails_AutomationPractices.class.getName());
	
	public final String fadedShortSleeveT_shirts = "Faded Short Sleeve T-shirts";
	public final String blouse = "Blouse";
	public final String printedDress = " Printed Dress ";

	public void selectSmallSize() {
		driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_1']")).click();
	}

	public void selectMediumSize() {

		driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_2']']")).isSelected();

	}

	public void selectLargeSize() {

		driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_3']")).click();

	}

	public int countProducts() {
		int count = driver.findElements(By.xpath(".//*[@id='center_column']/ul/li")).size();
		return count;
	}

	public void selectColor(String color) {
		driver.findElement(By.xpath("//a[contains(text(),'" + color + "')]/parent::label/preceding-sibling::input")).click();
	}

	public boolean verifyInformationSectionLinksCount(int count) {
		int countLinkes = driver.findElements(By.xpath(".//*[@id='informations_block_left_1']/div/ul/li")).size();
		if (count == countLinkes) {
			return true;

		} else {
           return false;
		}
	}

	public void selectProduct(String productName) {
		Actions obj = new Actions(driver);
		String locator = "//a[contains(text(),'"+productName+"')]";
		obj.moveToElement(driver.findElement(By.xpath(locator))).build().perform();
		driver.findElement(By.xpath(".//*[@id='center_column']/ul/li[1]/div/div[2]/div[2]/a[1]/span")).click();
	}
	
	public void selectProduct() {
		Actions obj = new Actions(driver);
		obj.moveToElement(driver.findElements(By.xpath(".//*[@id='center_column']/ul/li")).get(0)).build().perform();
		driver.findElement(By.xpath(".//*[@id='center_column']/ul/li[1]/div/div[2]/div[2]/a[1]/span")).click();
	}

	public boolean verifyProductAddedSuccesfullyMesaage() {
		String text = driver.findElement(By.xpath(".//*[@id='layer_cart']/div[1]/div[1]/h2")).getText();
		try {
			Assert.assertEquals(text, "Product successfully added to your shopping cart");
			return true;
		} catch (AssertionError e) {
			return false;
		}
	}

	public void clickOnProceedTocheckout() {
		driver.findElement(By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span")).click();
	}

}
