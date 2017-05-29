package com.test.automation.uiAutomation.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.test.automation.uiAutomation.testBase.TestBase;


public class ProductDetails_AutomationPractices extends TestBase {
	
	private final static Logger log = Logger.getLogger(ProductDetails_AutomationPractices.class.getName());
	
	WebDriver driver;
	
	public final String fadedShortSleeveT_shirts = "Faded Short Sleeve T-shirts";
	public final String blouse = "Blouse";
	public final String printedDress = " Printed Dress ";
	
	public ProductDetails_AutomationPractices(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * This method will select small size in product details page
	 */
	public void selectSmallSize() {
		driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_1']")).click();
	}

	/**
	 * This method will select medium size in product details page
	 */
	public void selectMediumSize() {

		driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_2']']")).isSelected();

	}

	/**
	 * This method will select large size in product details page
	 */
	public void selectLargeSize() {

		driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_3']")).click();

	}

	/**
	 * This method will count all product displayed on product details page
	 * @return
	 */
	public int countProducts() {
		int count = driver.findElements(By.xpath(".//*[@id='center_column']/ul/li")).size();
		return count;
	}

	/**
	 * This method will select color
	 * @param color
	 */
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

	
	/**
	 * This method will click on first product in product details page
	 */
	public void selectProduct() {
		Actions obj = new Actions(driver);
		obj.moveToElement(driver.findElements(By.xpath(".//*[@id='center_column']/ul/li")).get(0)).build().perform();
		driver.findElement(By.xpath(".//*[@id='center_column']/ul/li[1]/div/div[2]/div[2]/a[1]/span")).click();
	}

	/**
	 * This method will verify whether product is added successfully or not in pop up window
	 * @return
	 */
	public boolean verifyProductAddedSuccesfullyMesaage() {
		String text = driver.findElement(By.xpath(".//*[@id='layer_cart']/div[1]/div[1]/h2")).getText();
		try {
			Assert.assertEquals(text, "Product successfully added to your shopping cart");
			return true;
		} catch (AssertionError e) {
			return false;
		}
	}

	/**
	 * This method will click on proceed to check out
	 */
	public void clickOnProceedTocheckout() {
		driver.findElement(By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span")).click();
	}
	
	/**
	 * This method will click on product from navigation menu
	 * @param data
	 */
	public void clickOnProduct(String data){
		WebElement object = driver.findElement(By.xpath("//*[contains(text(),'"+data+"')]"));
		waitForElement(driver,object, 120);
		Actions action = new Actions(driver);
		action.moveToElement(object).build().perform();
		driver.findElement(By.xpath(".//*[@id='center_column']/ul/li/div/div[2]/div[2]/a[1]/span")).click();
	}
	
	/**
	 * This method will get the price from successful add window
	 * @return
	 */
	public String getProductPrice(){
		WebElement itemprice1 = driver.findElement(By.xpath(".//*[@id='layer_cart']/div[1]/div[1]/div[2]/div[2]/strong/following-sibling::*"));
		waitForElement(driver,itemprice1, 120);
		return itemprice1.getText();
	}
	
	public boolean verifyProductAddSuccessMsg(){
		WebElement successText = driver.findElement(By.xpath("//*[@id='layer_cart']/div[1]/div[1]/h2"));
		try {
			successText.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * This Method will click on continue shopping
	 */
	public void clickOnContinueShopping(){
		WebElement continueBtn = driver.findElement(By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/span/span/i"));
		waitForElement(driver,continueBtn, 120);
		continueBtn.click();
	}
	
	/**
	 * This method will click on proceed to checkout
	 */
	public void clickOnProceedToCheckOut(){
		WebElement ProcCheckOutBtn = driver.findElement(By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span"));
		waitForElement(driver,ProcCheckOutBtn, 120);
		ProcCheckOutBtn.click();
	}

}
