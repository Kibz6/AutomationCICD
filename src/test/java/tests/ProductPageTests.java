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
import pages.ProductCatalogue;
import pages.ProductPage;

public class ProductPageTests extends BaseTest{
	
	@Test(dataProvider="getData")
	public void ProductNameValidation(HashMap<String, String> input) throws InterruptedException {
		
			ProductCatalogue productCatalogue = loginPage.loginApplication(currentEmail,currentPassword);		
			ProductPage productPage = productCatalogue.ViewProduct(input.get("product"));
			Thread.sleep(500);
			assertEquals(productPage.getProductName(), input.get("product"));
	
	}
	
	@Test(dataProvider="getData")
	public void AddToCartValidation(HashMap<String, String> input) throws InterruptedException {
			
		    
			ProductCatalogue productCatalogue = loginPage.loginApplication(currentEmail,currentPassword);		
			ProductPage productPage = productCatalogue.ViewProduct(input.get("product"));
			Thread.sleep(1000);
			productPage.addToCart();
		    assertEquals(productPage.getToastMsg(), "Product Added To Cart");
		    CartPage cartPage = productPage.goToCart();
		    assertTrue(cartPage.getCartProductNames().contains(input.get("product")));
	
	}
	
	@Test(dataProvider="getData")
	public void ContinueShoppingValidation(HashMap<String, String> input) {
			
		    
			ProductCatalogue productCatalogue = loginPage.loginApplication(currentEmail,currentPassword);		
			ProductPage productPage = productCatalogue.ViewProduct(input.get("product"));
			productPage.continueShopping();
			assertEquals(getDriver().getCurrentUrl(), "https://rahulshettyacademy.com/client/#/dashboard/dash");
		
			
	}
	
	 @DataProvider
	 public Object[][] getData() throws IOException {
		 
		List<HashMap<String, String>> data = getJsonDataToMap("C:\\Users\\KIBZ\\eclipse-workspace\\ECommerceProject\\src\\test\\java\\testData\\TestData.json");
		return new Object[][] {{data.get(0)}}; 
		 
	 }

	
	

}
