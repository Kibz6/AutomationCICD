package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	
        WebDriver driver;
        
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
			}

	
	@FindBy(xpath="//div[@class='ng-star-inserted']")
	List<WebElement> products;
	
	By productsBy = By.xpath("//div[@class='ng-star-inserted']");
	
	@FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement placeOrder;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement applyCoupon;
		
	@FindBy(xpath="//input[@value='4542 9931 9292 2293']")
	WebElement creditCard;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryBar;
	
	@FindBy(xpath="//section[@class='ta-results list-group ng-star-inserted']")
	List<WebElement> countryDropdown;
	
	By countryDropdownBy = By.xpath("//section[@class='ta-results list-group ng-star-inserted']");
	
	
	
	public List<WebElement> getProducts() {
		
	waitForAllElementsToAppear(productsBy);
	return products;
				
	}
	
	public List<String> getProductNames() {
		
		waitForElementToAppear(productsBy);
		List<String> productList = getProducts().stream().map(s->s.findElement(By.xpath(".//div[@class='item__title']")).getText()).toList();
		return productList;
		
	}
	
	public void applyCoupon() {
		
		clickWhenNotBlocked(applyCoupon);
		
		
		
	}
	
	public ConfirmationPage placeOrder(String country,String creditCardInfo) {
		
		countryBar.sendKeys(country);
		if (country !=null && !country.isEmpty()) {
		waitForElementToAppear(countryDropdownBy); 
	    countryDropdown.stream().filter(s->s.getText().equals(country)).findFirst().ifPresent(WebElement::click);
		}
		creditCard.clear();
		creditCard.sendKeys(creditCardInfo);
		placeOrder.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
			
	}
	
	
}
