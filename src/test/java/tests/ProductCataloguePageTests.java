package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import pages.CartPage;
import pages.ProductCatalogue;



public class ProductCataloguePageTests extends BaseTest {
	
	
	
	
	
	@Test(dataProvider="getData")
	public void SearchBarValidation(HashMap<String, String> input) throws InterruptedException {
        	
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"),input.get("password"));		
		productCatalogue.searchProduct(input.get("product"));
		Thread.sleep(500);
		List<WebElement> productList = productCatalogue.getProducts();
		List<String> products = productList.stream().map(WebElement::getText).collect(Collectors.toList());
		assertTrue(products.stream().allMatch(s->s.contains(input.get("product"))));
		
	}
	
    @Test(dataProvider="getData")
    public void MyOrdersBtnValidation(HashMap<String, String> input) {
    	ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"),input.get("password"));
    	productCatalogue.goToMyOrders();
    	assertEquals("https://rahulshettyacademy.com/client/#/dashboard/myorders", driver.getCurrentUrl());
    	
    }
    
    @Test(dataProvider="getData")
    public void CartBtnValidation(HashMap<String, String> input) {
    	ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"),input.get("password"));
    	productCatalogue.goToCart();
    	assertEquals("https://rahulshettyacademy.com/client/#/dashboard/cart", driver.getCurrentUrl());
		
	}
    
    @Test(dataProvider="getData")
    public void AddToCartValidation (HashMap<String, String> input) throws InterruptedException {
    		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"),input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		List<String> products = cartPage.getCartProductNames();
		assertTrue(products.contains(input.get("product")));
		
    }
    
    
    
    @Test(dataProvider="getData")
    public void CartNumberValidation(HashMap<String, String> input) throws InterruptedException {
    	
    	ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"),input.get("password"));
    	int count = productCatalogue.addMultipleItemsToCart();   	
    	int number = productCatalogue.cartNmbrCheck();
    	assertEquals(count, number);
    	
    }
    
    @Test(dataProvider="getData")
    public void BrandNameValidation(HashMap<String, String> input) throws InterruptedException {
    	
    	ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"),input.get("password"));
    	String name = productCatalogue.getBrandName();
    	assertTrue(name.equalsIgnoreCase("Automation"));

    }
    
    @DataProvider
	 public Object[][] getData() throws IOException {
		 
		List<HashMap<String, String>> data = getJsonDataToMap("C:\\Users\\KIBZ\\eclipse-workspace\\ECommerceProject\\src\\test\\java\\testData\\TestData.json");
		return new Object[][] {{data.get(0)}}; 
		 
	 }
    
}
	
	
	
	


