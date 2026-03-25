package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
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
import pages.OrdersPage;
import pages.ProductCatalogue;

public class OrdersPageTests extends BaseTest {
	
	
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void BackToShopBtnValidation(HashMap<String,String> input) {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
        OrdersPage ordersPage = productCatalogue.goToMyOrders();
        ordersPage.goBackToShop();
        assertEquals(driver.getCurrentUrl(), "https://rahulshettyacademy.com/client/#/dashboard/dash");
	
		
	}
	
	
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void BackToCartBtnValidation(HashMap<String,String> input) {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
        OrdersPage ordersPage = productCatalogue.goToMyOrders();
        ordersPage.goBackToCart();
        assertEquals(driver.getCurrentUrl(), "https://rahulshettyacademy.com/client/#/dashboard/cart");
	
}
	
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void ViewItemValidation(HashMap<String,String> input) {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		ConfirmationPage confirmationPage = checkoutPage.placeOrder(input.get("country"), input.get("creditCard"));
		String orderId = confirmationPage.getOrderId().get(0);
		OrdersPage ordersPage = confirmationPage.goToMyOrders();
		ordersPage.viewItem(orderId);
		assertTrue(driver.getCurrentUrl().contains(orderId));
				
	}
	
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void DeleteOrderValidation(HashMap<String,String> input) {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		ConfirmationPage confirmationPage = checkoutPage.placeOrder(input.get("country"), input.get("creditCard"));
		String orderId = confirmationPage.getOrderId().get(0);
		OrdersPage ordersPage = confirmationPage.goToMyOrders();
		ordersPage.deleteOrder(orderId);
		assertEquals(ordersPage.getToastMsg(), "Orders Deleted Successfully");
		assertFalse(ordersPage.getOrderId().contains(orderId));
	
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		 
		List<HashMap<String, String>> data = getJsonDataToMap("C:\\Users\\KIBZ\\eclipse-workspace\\ECommerceProject\\src\\test\\java\\testData\\TestData.json");
		return new Object[][] {{data.get(0)}}; 
		 
	}



	
	
	
	
	
	
}