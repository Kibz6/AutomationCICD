package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(xpath="//h1[@class='hero-primary']")
	WebElement confirmation;
	
	@FindBy(xpath="//div/tr")
	List<WebElement> products; 
	
	By productsBy = By.xpath("//div/tr");
	
	@FindBy(css=".em-spacer-1 .ng-star-inserted")
	List<WebElement> orderId; 
	
	By orderIdBy = By.cssSelector(".em-spacer-1 .ng-star-inserted");
	
	@FindBy(xpath="//label[@routerlink='/dashboard/myorders']")
	WebElement orderHistoryPage;
	
	
	public String getConfirmation() {
		String text = confirmation.getText();
		return text;
	}
	
		
	public List<WebElement> getProducts() {
		
		waitForElementToAppear(productsBy);
		return products;
		
	}
		
	public List<String> getProductNames() {
		
		List<String>names = getProducts().stream().map(s->s.findElement(By.xpath(".//td[@class='line-item product-info-column m-3']/*[@class='title']"))
				.getText()).toList();
		return names;

	}
	
	public List<String> getOrderId() {
		
	    waitForElementToAppear(orderIdBy);
        List<String> codes = orderId.stream().map(WebElement::getText).map(s->s.replace("|", "").trim()).toList();
        return codes;
		
	
	}
	
	public OrdersPage goToOrderHistoryPage() {
		
		orderHistoryPage.click();
        return new OrdersPage(driver);	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
