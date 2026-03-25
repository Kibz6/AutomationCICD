package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import TestComponents.BaseTest;
import TestComponents.Retry;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ConfirmationPage;
import pages.ProductCatalogue;

public class CheckoutPageTests extends BaseTest{

	
	
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void CheckoutProductValidation(HashMap<String, String> input) throws InterruptedException {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addMultipleItemsToCart();
		CartPage cartPage = productCatalogue.goToCart();
		List<String> before = cartPage.getCartProductNames();
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		List<String> after =checkoutPage.getProductNames();
		Thread.sleep(500);
		assertTrue(after.stream().map(String::toLowerCase).toList()
				.containsAll(before.stream().map(String::toLowerCase).toList()));
	}
	
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void CouponValidation(HashMap<String, String> input) {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.applyCoupon();
		String toast = checkoutPage.getToastMsg();
		assertEquals(toast, "Please Enter Coupon");
		
	}
	
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void ShippingInformationValidation(HashMap<String, String> input) {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.placeOrder("",input.get("creditCard"));
		String toast = checkoutPage.getToastMsg();
		assertEquals(toast, "Please Enter Full Shipping Information");
		
	}
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void CreditCardValidation(HashMap<String, String> input) throws InterruptedException {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.placeOrder(input.get("country")," ");
		Thread.sleep(500);
		String toast = checkoutPage.getToastMsg();
		assertEquals(toast, "Please Enter Full Shipping Information");
	
		//Test should fail cause you can order without putting in your credit card info
	}
	
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void PlaceOrderValidation(HashMap<String, String> input) throws InterruptedException  {
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		ConfirmationPage confirmationPage = checkoutPage.placeOrder(input.get("country"),input.get("creditCard"));
		Thread.sleep(200);
		assertEquals(confirmationPage.getToastMsg(), "Order Placed Successfully");
		assertTrue(confirmationPage.getConfirmation().equalsIgnoreCase("Thankyou for the order."));
		
		
	}
	
	 @DataProvider
	 public Object[][] getData() throws IOException {
		 
		List<HashMap<String, String>> data = getJsonDataToMap("C:\\Users\\KIBZ\\eclipse-workspace\\ECommerceProject\\src\\test\\java\\testData\\TestData.json");
		return new Object[][] {{data.get(0)}}; 
		 
	 }
     	

	
	
	
	
	
	
}
