package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class ProductPage extends AbstractComponents {

	
	WebDriver driver;
	
	
	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath="//a[@class='continue']")
	WebElement continueShopping;
	
	@FindBy(xpath="//div/h2")
	WebElement productName;
	
	By productNameBy  = By.xpath("//div/h2");
	
	@FindBy(xpath="//div/button")
	WebElement addToCart;
	
	By addToCartBy = By.xpath("//div/button");
	
	public void continueShopping() {
		
		clickWhenNotBlocked(continueShopping);;
	
	}
	
	public String getProductName() {
		
		waitForElementToAppear(productNameBy);
		String name = productName.getText();
		return name;
	}
	
	public void  addToCart() {
		
		waitForElementToAppear(addToCartBy);
		clickWhenNotBlocked(addToCart);
	}
	
	
	
	
	
	
	
	
	
	

}
