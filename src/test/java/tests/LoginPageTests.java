package tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import pages.ProductCatalogue;



public class LoginPageTests extends BaseTest{
	@Test(dataProvider="getData")
	public void SuccessfulLoginValidation(HashMap<String, String> input)
	{
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(currentEmail,currentPassword);
		assertEquals("Login Successfully", productCatalogue.getToastMsg());	
	}
	
	@Test(dataProvider="getData")
	public void WrongPasswordValidation(HashMap<String, String> input) {
		
		loginPage.loginApplication(currentEmail,"Wrongpassword1234");
		assertEquals("Incorrect email or password.", loginPage.getToastMsg());
			}
		
		
	@Test
	public void BlankSpacesValidation() {
		
		loginPage.loginApplication("", "");
		assertEquals("*Email is required", loginPage.getEmailErrorMsg());
		assertEquals("*Password is required", loginPage.getPasswordErrorMsg());
			
	}
	
	@Test
	public void InvalidEmailValidation() {
		loginPage.loginApplication("12345", "");
		assertEquals("*Enter Valid Email", loginPage.getEmailErrorMsg());
	}
	
	@Test
	public void ForgotPasswordLinkValidation()
	{
		loginPage.goToNewPasswordPage();
		assertEquals("https://rahulshettyacademy.com/client/#/auth/password-new", getDriver().getCurrentUrl());
		
	}
	
	@Test
	public void RegisterLinkValidation() {
		
		loginPage.goToRegisterPage();
		assertEquals("https://rahulshettyacademy.com/client/#/auth/register", getDriver().getCurrentUrl());
		
	}
	
	@Test(dataProvider="getData")
	public void LogOutValidation(HashMap<String, String> input) {
		ProductCatalogue productCatalogue = loginPage.loginApplication(currentEmail,currentPassword);
		productCatalogue.signOut();
		assertEquals("Logout Successfully", loginPage.getToastMsg());
		
	}

	@DataProvider
	private Object getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap("C:\\Users\\KIBZ\\eclipse-workspace\\ECommerceProject\\src\\test\\java\\testData\\TestData.json");
		
		return new Object[][] {{data.get(0)}};
		
		
		
	}
	
	
	

}
