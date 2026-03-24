package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class OrderDetailsPage extends AbstractComponents{

	
	WebDriver driver;
	public OrderDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	
	}
	
	@FindBy(xpath="//div[@class='col-text -main']")
	WebElement orderId;
	
	@FindBy(xpath="//p[@class='tagline']")
	WebElement greetMsg;
	
	@FindBy(xpath="//div[@class='email-wrapper ng-star-inserted']//div[1]//div[1]//p[1]")
	WebElement billingAddress;
	
	@FindBy(xpath="/html[1]/body[1]/app-root[1]/app-order-details[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/p[2]")
	WebElement billingCountry;
	
	@FindBy(xpath="/html[1]/body[1]/app-root[1]/app-order-details[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/div[1]/p[1]")
	WebElement deliveryAddress;
	
	@FindBy(xpath="//div[@class='email-container']//div[2]//div[1]//p[2]")
	WebElement deliveryCountry;
	
	@FindBy(xpath = "//div[@class='title']")
	WebElement productName;
	
	@FindBy(xpath="//div[@class='price']")
	WebElement productPrice;
	
	@FindBy(xpath="//div[@class='btn -teal']")
	WebElement viewOrders;
	
	
	public String getOrderId() {
		
		String Id = orderId.getText();
		return Id;
		
	}
	
    public String getGreetMsg() {
		
		return greetMsg.getText();		

     }


    public String getBillingAddress() {
	
	return billingAddress.getText();	
       }
    
    public String getBillingCountry() {
    	
    	return billingCountry.getText().split("-")[1].trim();	
           }

    public String getDeliveryAddress() {
    	
    	return deliveryAddress.getText();	
           }
    
    public String getDeliveryCountry() {
    	
    	return deliveryCountry.getText().split("-")[1].trim();
           }
    
    public String getProductName() {
    	
    	return productName.getText();	
           }
    
    public int getProductPrice() {
    	
    	String price = productPrice.getText().replace("$", "").trim();
    	return Integer.parseInt(price);
           }

    public OrdersPage goToViewOrders() {
    	
    	viewOrders.click();
    	return new OrdersPage(driver);
    	
    	
    }









}

