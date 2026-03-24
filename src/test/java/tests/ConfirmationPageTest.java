package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ConfirmationPage;
import pages.OrdersPage;
import pages.ProductCatalogue;

public class ConfirmationPageTest extends BaseTest{

	
	@Test(dataProvider="getData")
	public void ProductNamesConfirmation(HashMap<String,String> input) {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"),input.get("password"));
		productCatalogue.addMultipleItemsToCart();
		CartPage cartPage = productCatalogue.goToCart();
		List<String> before = cartPage.getCartProductNames();
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		ConfirmationPage confirmationPage = checkoutPage.placeOrder(input.get("country"),"");
		List<String> after = confirmationPage.getProductNames();
		System.out.println(before);
		System.out.println(after);
		assertTrue(before.stream().map(String::toLowerCase).toList()
		        .containsAll(after.stream().map(String::toLowerCase).toList()));
		
		
	}
	
	@Test(dataProvider="getData")
	public void OrdersHistoryBtnValidation(HashMap<String, String> input) {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"),input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		ConfirmationPage confirmationPage = checkoutPage.placeOrder(input.get("country"),"");		
		confirmationPage.goToOrderHistoryPage();
		assertEquals(driver.getCurrentUrl(), "https://rahulshettyacademy.com/client/#/dashboard/myorders");
	}
	
	@Test(dataProvider="getData")
	public void OrdersIdValidation(HashMap<String, String> input) throws InterruptedException {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"),input.get("password"));
		productCatalogue.addMultipleItemsToCart();
		CartPage cartPage = productCatalogue.goToCart();
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		ConfirmationPage confirmationPage = checkoutPage.placeOrder(input.get("country"), input.get("creditCard"));
		List<String> before = confirmationPage.getOrderId();
		OrdersPage ordersPage = confirmationPage.goToOrderHistoryPage();
		List<String> after = ordersPage.getOrderId();
		assertTrue(after.containsAll(before));
	
	}

	 @DataProvider
	 public Object[][] getData() throws IOException {
		 
		List<HashMap<String, String>> data = getJsonDataToMap("C:\\Users\\KIBZ\\eclipse-workspace\\ECommerceProject\\src\\test\\java\\testData\\TestData.json");
		return new Object[][] {{data.get(0)}}; 
		 
	 }




	
	
	
	
	
	
	
	
	
	
}
