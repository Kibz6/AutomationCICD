package TestComponents;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;

public class BaseTest {
	
	
	public LoginPage loginPage;
	protected String currentEmail;
	protected String currentPassword;
	protected String currentBrowser;

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public WebDriver getDriver() {
		return driver.get();		
	}
	

	public WebDriver initializeDriver (String browserName) throws IOException {
		
			
		String hubUrl = "http://localhost:4444/wd/hub";
			
		switch (browserName.toLowerCase()) {
		case "chrome":
			 ChromeOptions chromeOptions = new ChromeOptions();
	         driver.set(new RemoteWebDriver(new URL(hubUrl), chromeOptions));
		break;
		
		case "firefox":
			 FirefoxOptions firefoxOptions = new FirefoxOptions();
			 driver.set(new RemoteWebDriver(new URL(hubUrl), firefoxOptions));
	      			break;
			
		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			driver.set(new RemoteWebDriver(new URL(hubUrl), edgeOptions));
			break;
			
			default:
				throw new IllegalArgumentException("Browser not supported: " + browserName);
		}

		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return getDriver();
	
	}
	
	@BeforeMethod
	@Parameters({"browser","email","password"})
	public  LoginPage launchApplication(String browser,String email,String password) throws IOException {
		
		    this.currentBrowser = browser;
			this.currentEmail = email;
			this.currentPassword = password;
		
		
			
	    initializeDriver(browser);
	    loginPage = new LoginPage(getDriver());
	    loginPage.goTo();
		
	    
	    return loginPage;	    	
	}
	    public String getCurrentBrowser() {
	    	return currentBrowser;
	    }
	
	
	@AfterMethod
	public void tearDown() {
		if(getDriver() != null) {
	      getDriver().quit();
	      driver.remove();
		}
	}
	
	
	
	 public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
		
		{
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String, String>> data = mapper.readValue(
			    jsonContent,
			    new TypeReference<List<HashMap<String, String>>>() {});
			    return data;
		}
		
	public String getScreenshot(String testName) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) getDriver();
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testName + ".png";	
	}
}
	
	
	


