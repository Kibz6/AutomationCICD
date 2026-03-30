 package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import TestComponents.Retry;
import pages.CartPage;
import pages.ProductCatalogue;

public class CartPageTests extends BaseTest{
	
	
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void EmptyCartValidation(HashMap<String, String> input) {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(currentEmail,currentPassword);
		CartPage cartPage = productCatalogue.goToCart();
		assertEquals(cartPage.getEmptyCartMsg(), "No Products in Your Cart !");
		assertEquals(cartPage.getToastMsg(), "No Product in Your Cart");
		
	}
	
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void ContinueShoppingButtonValidation(HashMap<String, String> input) {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(currentEmail,currentPassword);
		CartPage cartPage = productCatalogue.goToCart();
		cartPage.continueShopping();
		assertEquals("https://rahulshettyacademy.com/client/#/dashboard/dash", getDriver().getCurrentUrl());

	}
	
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void RemoveItemFromCartValidation(HashMap<String, String> input)  {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(currentEmail,currentPassword);
		productCatalogue.addMultipleItemsToCart();
		CartPage cartPage = productCatalogue.goToCart();
		cartPage.removeItem(input.get("product"));
		List<String> list = cartPage.getCartProductNames();
		assertFalse(list.contains(input.get("product")));
	    
	}
	
	
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void CartSubtotalValidation(HashMap<String, String> input) {
		
	ProductCatalogue productCatalogue = loginPage.loginApplication(currentEmail, currentPassword);
	productCatalogue.addMultipleItemsToCart();
	CartPage cartPage = productCatalogue.goToCart();
	assertEquals(cartPage.getCartItemsTotal(), cartPage.getSubTotalValue());
				
	}
	
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void CartSubtotalDeductionValidation(HashMap<String, String> input) {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(currentEmail,currentPassword);
		productCatalogue.addMultipleItemsToCart();
		CartPage cartPage = productCatalogue.goToCart();
		cartPage.removeItem(input.get("product"));
		assertEquals(cartPage.getCartItemsTotal(), cartPage.getSubTotalValue());
		
		
	//Test should fail cause removing an item from the cart is actually adding instead of subtracting from the subtotal	
				
	}
	
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void CartSessionValidation(HashMap<String, String> input) {
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(currentEmail,currentPassword);
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		List<String> before = cartPage.getCartProductNames();
		cartPage.signOut();
		loginPage.loginApplication(currentEmail,currentPassword);
		productCatalogue.goToCart();
		List<String> after = cartPage.getCartProductNames();
		assertTrue(after.containsAll(before));
		
		//Test should fail cause the cart session doesn't get saved after sign out.
	}
	
	
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void CartPageRefreshValidation(HashMap<String, String> input) {
		ProductCatalogue productCatalogue = loginPage.loginApplication(currentEmail,currentPassword);
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		List<String> before = cartPage.getCartProductNames();
		cartPage.refreshPage();
		List<String> after = cartPage.getCartProductNames();
		assertEquals(after,before);
			
	}
	
	
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void CheckoutBtnValidation(HashMap<String, String> input){
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(currentEmail,currentPassword);
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		cartPage.goToCheckout();
	    assertTrue(getDriver().getCurrentUrl().contains("https://rahulshettyacademy.com/client/#/dashboard/order"));
	}
	
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void ProductCodeValidation(HashMap<String, String> input) {
		
		    
			ProductCatalogue productCatalogue = loginPage.loginApplication(currentEmail,currentPassword);		
			productCatalogue.getProductName(input.get("product"));
			productCatalogue.addProductToCart(input.get("product"));
			CartPage cartPage = productCatalogue.goToCart();
			List<String> productCode = cartPage.getProductCode();		
			cartPage.goToHome();
			productCatalogue.ViewProduct(input.get("product"));
			assertTrue(getDriver().getCurrentUrl().contains(productCode.get(0)));
			

	}
	
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void ViewProductValidation(HashMap<String, String> input) {
		
	
			ProductCatalogue productCatalogue = loginPage.loginApplication(currentEmail,currentPassword);		
			productCatalogue.getProductName(input.get("product"));
	
	}
	
	@DataProvider
	private Object getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap("C:\\Users\\KIBZ\\eclipse-workspace\\ECommerceProject\\src\\test\\java\\testData\\TestData.json");
		
		return new Object[][] {{data.get(0)}};
		
		
		
	}

}
