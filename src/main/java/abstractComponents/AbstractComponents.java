package abstractComponents;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.CartPage;
import pages.LoginPage;
import pages.OrdersPage;
import pages.ProductCatalogue;

public class AbstractComponents {
	
	WebDriver driver;
	WebDriverWait wait;
	public AbstractComponents (WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
	}
	
	@FindBy (xpath ="//button[normalize-space()='Sign Out']")
	WebElement signOutBtn;
	
	@FindBy (xpath ="//button[@routerlink='/dashboard/myorders']")
	WebElement myOrdersBtn;
	
	@FindBy (xpath ="//button[@routerlink='/dashboard/cart']")
	WebElement cartBtn;
	
	By cartBtnBy = By.xpath("//button[@routerlink='/dashboard/cart']");
	
	@FindBy (xpath ="//button[normalize-space()='HOME']")
	WebElement homeBtn;
	
	@FindBy(xpath="//div[@aria-label]")
	WebElement toastMsg;
	
	By toastBy = By.xpath("//div[@aria-label]");

	By spinnerBy = By.xpath("//div[@class='ngx-spinner-overlay ng-tns-c31-12 ng-trigger ng-trigger-fadeIn ng-star-inserted ng-animating']");
	
	public void waitForElementToAppear(By findBy) {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDissapear (By findBy) {
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));		
	}
	
	public void waitForStaleness(WebElement element) {
		
		wait.until(ExpectedConditions.stalenessOf(element));
	}
	
	  public void clickWhenNotBlocked(WebElement element) {

  	    wait.until(driver -> {
  	        try {
  	            element.click();
  	            return true;
  	        } catch (Exception e) {
  	            return false;
  	        }
  	    });
  	}
   
	
	public LoginPage signOut() {
		clickWhenNotBlocked(signOutBtn);
		LoginPage loginPage = new LoginPage(driver);
		return loginPage;
	}
	

	public OrdersPage goToMyOrders() {
		
		clickWhenNotBlocked(myOrdersBtn);
	    OrdersPage ordersPage = new OrdersPage(driver);
	    return ordersPage;		
		}
	
  
     public CartPage goToCart() {
 		
     	
     	clickWhenNotBlocked(cartBtn);
 		CartPage cartPage = new CartPage(driver);
 		return cartPage;		
 			}
     
     
     
     
    public ProductCatalogue goToHome() {
	
    	clickWhenNotBlocked(homeBtn);
	    ProductCatalogue productCatalogue = new ProductCatalogue(driver);
	    return productCatalogue;
	
}
	public void refreshPage() {
		
		driver.navigate().refresh();
	
	}
	
    public String getToastMsg() {

		waitForElementToAppear(toastBy);
		WebElement toast = toastMsg;			
	    String msg = toast.getText();
    return msg;
	}}

	
	


