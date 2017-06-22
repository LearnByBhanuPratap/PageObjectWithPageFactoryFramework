package com.test.automation.uiAutomation.addToCart;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.automation.uiAutomation.testBase.TestBase;
import com.test.automation.uiAutomation.uiActions.HomePage_AutomationPractices;
import com.test.automation.uiAutomation.uiActions.ProductDetails_AutomationPractices;
import com.test.automation.uiAutomation.uiActions.ShoppingCartPage_AutomationPractices;


public class VerifyAddProductToCartAndDeleteFromCart extends TestBase {

	String price1;
	String price2;
	
	HomePage_AutomationPractices homepage;
	
	ProductDetails_AutomationPractices productDeatilsPage;
	
	ShoppingCartPage_AutomationPractices shoppingCartPage;

	//@BeforeClass
	public void setUp() throws IOException {
		init();
	}

	@Test
	public void assigment() throws InterruptedException {
		
		homepage  = new HomePage_AutomationPractices(driver);
		
		// Mouse over on Women
        homepage.mouseOver("Women");

        // Click on T-shirts
		homepage.clickOnProduct("T-shirts");

		productDeatilsPage = new ProductDetails_AutomationPractices(driver);
		
		// Mouse Over on Faded Short Sleeve T-shirts and click on Add to cart
		productDeatilsPage.clickOnProduct("Faded Short Sleeve T-shirts");

		// Get Price form product added window
		String itemprice1 = productDeatilsPage.getProductPrice();
		
		Assert.assertEquals(itemprice1, "$16.51");

		// Here substring will remove $ from price
		price1 = itemprice1.substring(1);
		
		// converting to Double
		double priceN1 = Double.parseDouble(price1);

		System.out.println(priceN1);

		// Verify product added successfully
		Assert.assertEquals(true, productDeatilsPage.verifyProductAddSuccessMsg());

		// Click on continue shopping
		productDeatilsPage.clickOnContinueShopping();

		// Mouse Over on Women
		homepage.mouseOver("Women");

		// Click on Casual Dresses
		homepage.clickOnProduct("Casual Dresses");
		
		// Mouse Over on Printed Dress and click on Add to cart
		productDeatilsPage.clickOnProduct("Printed Dress");

		// Get Price form product added window
		String item2Price = productDeatilsPage.getProductPrice();
		Assert.assertEquals(item2Price, "$26.00");
		price2 = item2Price.substring(1);
		System.out.println(price2);
		
		// converting String to Double
		double priceN2 = Double.parseDouble(price2);
		System.out.println(priceN2);

		// Click on Proceed to checkout in product add window
		productDeatilsPage.clickOnProceedToCheckOut();

		// We are getting price since we have added two products
		shoppingCartPage = new ShoppingCartPage_AutomationPractices(driver);
		ArrayList<String> prices = shoppingCartPage.getPriceOfProductsFromShoppingCart();
		System.out.println(prices);

		String s1 = prices.get(0).toString();
		String s2 = prices.get(1).toString();

		// Converting String to double
		double d1 = Double.parseDouble(s1);
		double d2 = Double.parseDouble(s2);

		// Making object of big decimal so that we can get long value from it
		BigDecimal bg = new BigDecimal(d1 + d2);
		long subTotal = bg.longValue();
		System.out.println("subTotal:-  " + subTotal);

		// Delete all items from cart
		shoppingCartPage.delectProductFromShoppingCart();

		// Verify Your shopping cart is empty message
		boolean isDisplayed = shoppingCartPage.verifyEmptyShoppingCartMesssage();

		Assert.assertTrue(isDisplayed, "cart is empty");

	}
}
