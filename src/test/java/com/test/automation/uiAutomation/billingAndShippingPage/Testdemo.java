package com.test.automation.uiAutomation.billingAndShippingPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Testdemo {

	WebDriver driver;

	@BeforeClass
	public void setUp() {

	}

	@Test
	public void testLogin() {
		driver.findElement(By.id("customer_register_link")).click();
		driver.findElement(By.id("FirstName")).clear();
		driver.findElement(By.id("FirstName")).sendKeys("test");
		driver.findElement(By.id("LastName")).clear();
		driver.findElement(By.id("LastName")).sendKeys("testlast");
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("CreatePassword")).clear();
		driver.findElement(By.id("CreatePassword")).sendKeys("password");
		driver.findElement(By.cssSelector("input.btn")).click();
	}

	@AfterClass
	public void endTest() {

	}

}
