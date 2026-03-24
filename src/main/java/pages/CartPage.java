package pages;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.TimeoutException;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import abstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy (xpath="//li/div")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//h1[normalize-space()='No Products in Your Cart !']")
	WebElement emptyCartMsg;
	
	@FindBy(xpath="//div[@role='alert']")
	WebElement emptyCartToast;
	
	@FindBy(xpath="//button[normalize-space()='Continue Shopping']")
	WebElement continueShoppingBtn;
	
	@FindBy(xpath="//span[text()='Subtotal']/following-sibling::span")
	WebElement subTotal;
	
	@FindBy(xpath="//button[normalize-space()='Checkout']")
	WebElement checkout;
	
	By cartProductsBy = By.xpath("//li/div");
	
	By removeBtn = By.xpath(".//button");

	By emptyCartBy = By.xpath("//h1[normalize-space()='No Products in Your Cart !']");
	
	
	
	
	public List<WebElement> getCartProducts() {
		try {
	    waitForElementToAppear(cartProductsBy);
		return cartProducts;
	} catch (TimeoutException e) {
		return null;
	}
	}
		
	
	public List<String> getCartProductNames() {
	
		
		
		List<WebElement> products = getCartProducts();
		
		if(products==null)
		{
			return Collections.emptyList();
		}
		
		List<String> names = products.stream().map(s->s.findElement(By.xpath(".//h3")).getText()).toList();
	    return names;	
	
	}
	
	public List<String> getProductCode() {
		
		List<String> codes = getCartProducts().stream().map(s->s.findElement(By.xpath(".//p[@class='itemNumber']")).getText())
				.map(s->s.replace("#", "")).toList();
				
		return codes;
	}
	
	public String getEmptyCartMsg() {
		
		waitForElementToAppear(emptyCartBy);
		String msg = emptyCartMsg.getText();
		return msg;
	
	}
	
	public String getToastMsg() {
		
		String toast = emptyCartToast.getText();
		return toast;
			
	}
	
	public ProductCatalogue continueShopping() {
		
		continueShoppingBtn.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		
	}
	
	public void removeItem(String productName) {
		
		WebElement product = getCartProducts().stream().filter(s->s.findElement(By.xpath(".//h3")).getText().equals(productName))
		.findFirst().orElse(null);
		
		if(product !=null) {
		product.findElement(By.xpath(".//button[@class='btn btn-danger']")).click();
		waitForStaleness(product);
		
		
		}
		
	
	}
	
	public int getSubTotalValue() {
		
		
		int value = Integer.parseInt(subTotal.getText().replace("$", "").trim());
		return value;
		
	}
	
	public int getCartItemsTotal() {
		int total = getCartProducts().stream().map(s->s.findElement(By.xpath(".//div[@class='prodTotal cartSection']"))
				.getText().replace("$", "").trim()).mapToInt(Integer::parseInt).sum();
		return total;			
	}


	
	public CheckoutPage goToCheckout() {
		checkout.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
		
	}
	
	
	
}
