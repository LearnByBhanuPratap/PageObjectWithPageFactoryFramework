package com.test.automation.uiAutomation.uiActions;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.uiAutomation.testBase.TestBase;
/**
 * 
 * @author Bhanu Pratap
 *
 */
public class ProductDetailsPage extends TestBase{
	
	WebDriver driver;
	public final static Logger log = Logger.getLogger(ProductDetailsPage.class.getName());
	
	public final String contrast_jacket= "Contrast jacket";
	public final String grey_Jacket= "Grey Jacket";
	public final String off_white_trench_style_jacket= "Off white trench style jacket";
	public final String flower_print_jeans = "Flower print jeans";
	
	
	
	@FindBy(xpath="//*[@id='shopify-section-collection-template']/div/div/div/a/img")
	List<WebElement> products;

	public ProductDetailsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectProduct(String product) {
		System.out.println("driver:-"+driver);
		driver.findElement(By.xpath("//a[contains(text(),'"+product+"')]")).click();
		
		log.info(product+" has beel selected");
	}
	
	public List<WebElement> selectProduct(){
		List<WebElement> element = products;
		return element;
	}

}
