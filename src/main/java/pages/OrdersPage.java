package pages;

import java.util.Collections;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {
	
	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//tbody//tr")
	List<WebElement> products;
	
	By productsBy = By.xpath("//tbody//tr");
	
	@FindBy(xpath="//button[@class='btn btn-primary col-md-2 offset-md-4']")
	WebElement backToShop;
	
	@FindBy(xpath="//button[@class='btn btn-primary col-md-2']")
	WebElement backToCart;
	
	
	
	
	
	
	public ProductCatalogue goBackToShop() {
		
		clickWhenNotBlocked(backToShop);
		return new ProductCatalogue(driver);
		
	}
	
	public CartPage goBackToCart() {
		
		clickWhenNotBlocked(backToCart);
		return new CartPage(driver);
		
	}
	
	
	
	public List<WebElement> getProducts() {
		try {
		waitForElementToAppear(productsBy);
		return products;
		} catch(TimeoutException e) {
			return null;
		}
		
	}
	
	public List<String> getOrderId() {
		
		List<WebElement> products = getProducts();
		
		if(products==null) {
			return Collections.emptyList();
		}
			
		
		List<String> idList = products.stream().map(s->s.findElement(By.xpath(".//th")).getText()).toList();
				
		return idList;
	}
	
	public OrderDetailsPage viewItem(String orderId) {
		
		getProducts().stream().filter(s->s.findElement(By.xpath(".//th")).getText().equals(orderId))
		         .findFirst().ifPresent(s->s.findElement(By.xpath(".//button[@class='btn btn-primary']")).click());
		return new OrderDetailsPage(driver);
		
	}
	
	public void deleteOrder(String orderId) {
		
		WebElement product = getProducts().stream().filter(s->s.findElement(By.xpath(".//th")).getText().equals(orderId))
        .findFirst().orElse(null);
		
		if(product !=null)
		{
			product.findElement(By.xpath(".//button[@class='btn btn-danger']")).click();
			waitForStaleness(product);		
	}
	
	
	}
}
