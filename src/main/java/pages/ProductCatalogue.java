package pages;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;
	LoginPage loginPage;
	OrdersPage ordersPage;
	CartPage cartPage;
	ProductCatalogue productCatalogue;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By succesfulLoginAlertBY = By.xpath("//div[@aria-label='Login Successfully']");
	
	@FindBy (xpath = "//div[@class='py-2 border-bottom ml-3']//input[@placeholder='search']")
	WebElement searchBar;
	
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	By productsBy = By.cssSelector(".mb-3");
	

	@FindBy (xpath ="//button[@routerlink='/dashboard/cart']")
	WebElement cartBtn;
		
	@FindBy (xpath ="//h3[normalize-space()='Automation']")
	WebElement brandName;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']/label")
	WebElement cartNmbr;
	
    By addToCart =By.xpath(".//button[@class='btn w-10 rounded']");
    
	
	public void searchProduct (String productName) {
		
		searchBar.sendKeys(productName + Keys.ENTER);		
	}
	
	public List<WebElement> getProducts () {
		
	waitForAllElementsToAppear(productsBy);	
	return products;
	
	}
	public WebElement getProductName(String productName) {
		WebElement prod = getProducts().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName))
		.findFirst().orElse(null);
		return prod;	
	}
	
	public void addProductToCart(String productName) {
		
		WebElement prod = getProductName(productName);
		WebElement button = prod.findElement(addToCart);
	    clickWhenNotBlocked(button);
		
		
	    
				
	}
	
	public  int cartNmbrCheck() {
		
		String text = cartNmbr.getText();
		if(text.isEmpty()) {
			return 0;
		}
			return Integer.parseInt(text);
			
		}
		
		
	
	
	public  int addMultipleItemsToCart() {
		List<WebElement> products = getProducts();
		int count = products.size();
		int addedCount = 0;
		for(int i=0;i<count;i++) {
					
			WebElement item = products.get(i);
			WebElement button = item.findElement(By.xpath(".//button[@class='btn w-10 rounded']"));	
			int cartCount = cartNmbrCheck();
			clickWhenNotBlocked(button);
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(driver -> (cartNmbrCheck()) > cartCount);
			
			addedCount++;
		}			
		return addedCount;
			
	}
	

    
    public String getBrandName() {
    	
    	
    	String name = brandName.getText();
    	return name;
    	
    	
    }
    
    public ProductPage ViewProduct(String productName) {
		
		getProducts().stream().filter(s->s.findElement(By.xpath(".//b")).getText().equals(productName))
		.findFirst().ifPresent(s->s.findElement(By.xpath(".//button[@class='btn w-40 rounded']")).click());	
		ProductPage productPage = new ProductPage(driver);
		return productPage;
		
	}
	
    
    
    
    
    
		
		
	}
	
		
	
		
		
	
	


