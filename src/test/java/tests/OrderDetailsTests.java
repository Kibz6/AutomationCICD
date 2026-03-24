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
import pages.OrderDetailsPage;
import pages.OrdersPage;
import pages.ProductCatalogue;

public class OrderDetailsTests extends BaseTest {

	@Test(dataProvider="getData")
	public void OrderDetailsValidation(HashMap<String, String> input) {
		
		
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"),input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		int price = cartPage.getCartItemsTotal();
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		ConfirmationPage confirmationPage = checkoutPage.placeOrder(input.get("country"), "123123123");
		String orderId = confirmationPage.getOrderId().get(0);
		OrdersPage ordersPage = confirmationPage.goToMyOrders();
		OrderDetailsPage orderDetails = ordersPage.viewItem(orderId);
		assertEquals(orderDetails.getGreetMsg(), "Thank you for Shopping With Us");
		assertEquals(orderDetails.getBillingAddress(), input.get("email"));
		assertEquals(orderDetails.getDeliveryAddress(), input.get("email"));
		assertEquals(orderDetails.getBillingCountry(), input.get("country"));
		assertEquals(orderDetails.getDeliveryCountry(), input.get("country"));
		assertEquals(orderDetails.getOrderId(), orderId);
		assertEquals(orderDetails.getProductName(), input.get("product"));	
		assertEquals(orderDetails.getProductPrice(), price);
	
	}
	
	@Test(dataProvider="getData")
	public void ViewOrdersBtnValidation(HashMap<String, String> input){
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"),input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		ConfirmationPage confirmationPage = checkoutPage.placeOrder(input.get("country"), input.get("creditCard"));
		String orderId = confirmationPage.getOrderId().get(0);
		OrdersPage ordersPage = confirmationPage.goToMyOrders();
		OrderDetailsPage orderDetails = ordersPage.viewItem(orderId);
		orderDetails.goToViewOrders();
		assertEquals(driver.getCurrentUrl(), "https://rahulshettyacademy.com/client/#/dashboard/myorders");
		

	}
	
	 @DataProvider
	 public Object[][] getData() throws IOException {
		 
		List<HashMap<String, String>> data = getJsonDataToMap("C:\\Users\\KIBZ\\eclipse-workspace\\ECommerceProject\\src\\test\\java\\testData\\TestData.json");
		return new Object[][] {{data.get(0)}}; 
		 
	 }
	
	
	
	
	
}
