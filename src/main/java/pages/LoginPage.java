package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents{
	
	WebDriver driver;
	ProductCatalogue productCatalogue;
	NewPasswordPage newPassword;
	RegisterPage registerPage;
		
	public LoginPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy (id = "userEmail")
	WebElement email;
	
	@FindBy (id = "userPassword")
	WebElement password;
	
	@FindBy (id = "login")
	WebElement loginBtn;
		
	@FindBy (xpath = "//div[@class='form-group']/div")
	WebElement emailErrorMsg;
	
	By emailErrorMsgBy = By.xpath("//div[@class='form-group']/div");
	
	@FindBy(xpath="//div[@class='form-group mb-4']/div")
	WebElement passwordErrorMsg;
	
	By passwordErrorMsgBy = By.xpath("//div[@class='form-group mb-4']/div");
	
	@FindBy (xpath="//a[@class='forgot-password-link']")
	WebElement forgotPasswordBtn;
	
	@FindBy (xpath="//a[@class='btn1']")
	WebElement registerBtn;
		
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
	
	public ProductCatalogue loginApplication(String userEmail, String userPassword) {
		email.sendKeys(userEmail);
		password.sendKeys(userPassword);
		loginBtn.click();
		return new ProductCatalogue(driver);
		
	}
	


	public String getEmailErrorMsg() {
		
		waitForElementToAppear(emailErrorMsgBy);
		return emailErrorMsg.getText();
	}
	
	public String getPasswordErrorMsg() {
		
		waitForElementToAppear(passwordErrorMsgBy);
		return passwordErrorMsg.getText();
	}
	
    public NewPasswordPage goToNewPasswordPage()
    {
    	forgotPasswordBtn.click();
    	return new NewPasswordPage(driver);
    }
	
    public RegisterPage goToRegisterPage() {
    	
    	registerBtn.click();
    	return new RegisterPage(driver);
    }
     

	
	
}
