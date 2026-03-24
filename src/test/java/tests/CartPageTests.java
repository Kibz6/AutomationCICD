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
import pages.CartPage;
import pages.ProductCatalogue;

public class CartPageTests extends BaseTest{
	
	@Test(dataProvider="getData")
	public void EmptyCartValidation(HashMap<String, String> input) {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
		CartPage cartPage = productCatalogue.goToCart();
		assertEquals(cartPage.getEmptyCartMsg(), "No Products in Your Cart !");
		assertEquals(cartPage.getToastMsg(), "No Product in Your Cart");
		
	}
	
	@Test(dataProvider="getData")
	public void ContinueShoppingButtonValidation(HashMap<String, String> input) {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
		CartPage cartPage = productCatalogue.goToCart();
		cartPage.continueShopping();
		assertEquals("https://rahulshettyacademy.com/client/#/dashboard/dash", driver.getCurrentUrl());

	}
	
	@Test(dataProvider="getData")
	public void RemoveItemFromCartValidation(HashMap<String, String> input)  {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addMultipleItemsToCart();
		CartPage cartPage = productCatalogue.goToCart();
		cartPage.removeItem(input.get("product"));
		List<String> list = cartPage.getCartProductNames();
		assertFalse(list.contains(input.get("product")));
	    
	}
	
	
	@Test(dataProvider="getData")
	public void CartSubtotalValidation(HashMap<String, String> input) {
		
	ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
	productCatalogue.addMultipleItemsToCart();
	CartPage cartPage = productCatalogue.goToCart();
	assertEquals(cartPage.getCartItemsTotal(), cartPage.getSubTotalValue());
				
	}
	
	@Test(dataProvider="getData")
	public void CartSubtotalDeductionValidation(HashMap<String, String> input) {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addMultipleItemsToCart();
		CartPage cartPage = productCatalogue.goToCart();
		cartPage.removeItem(input.get("product"));
		assertEquals(cartPage.getCartItemsTotal(), cartPage.getSubTotalValue());
		
		
	//Test should fail cause removing an item from the cart is actually adding instead of subtracting from the subtotal	
				
	}
	
	@Test(dataProvider="getData")
	public void CartSessionValidation(HashMap<String, String> input) {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		List<String> before = cartPage.getCartProductNames();
		cartPage.signOut();
		loginPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.goToCart();
		List<String> after = cartPage.getCartProductNames();
		assertTrue(after.containsAll(before));
		
		//Test should fail cause the cart session doesn't get saved after sign out.
	}
	
	
	@Test(dataProvider="getData")
	public void CartPageRefreshValidation(HashMap<String, String> input) {
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		List<String> before = cartPage.getCartProductNames();
		cartPage.refreshPage();
		List<String> after = cartPage.getCartProductNames();
		assertEquals(after,before);
			
	}
	
	
	@Test(dataProvider="getData")
	public void CheckoutBtnValidation(HashMap<String, String> input){
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		cartPage.goToCheckout();
		assertTrue(driver.getCurrentUrl().contains("https://rahulshettyacademy.com/client/#/dashboard/order"));
	
	}
	
	@Test(dataProvider="getData")
	public void ProductCodeValidation(HashMap<String, String> input) {
		
		    
			ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));		
			productCatalogue.getProductName(input.get("product"));
			productCatalogue.addProductToCart(input.get("product"));
			CartPage cartPage = productCatalogue.goToCart();
			List<String> productCode = cartPage.getProductCode();		
			cartPage.goToHome();
			productCatalogue.ViewProduct(input.get("product"));
			assertTrue(driver.getCurrentUrl().contains(productCode.get(0)));
			

	}
	
	@Test(dataProvider="getData")
	public void ViewProductValidation(HashMap<String, String> input) {
		
	
			ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));		
			productCatalogue.getProductName(input.get("product"));
	
	}
	
	@DataProvider
	private Object getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap("C:\\Users\\KIBZ\\eclipse-workspace\\ECommerceProject\\src\\test\\java\\testData\\TestData.json");
		
		return new Object[][] {{data.get(0)}};
		
		
		
	}

}
