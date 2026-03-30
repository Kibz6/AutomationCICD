package TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.TestInstance;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;

public class Listeners implements ITestListener {

	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReporterObject();
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	
	@Override
	public void onTestStart(ITestResult result) {
		
	
		BaseTest testInstance = (BaseTest) result.getInstance();		
		String browser = testInstance.getCurrentBrowser();
		Retry retry = (Retry) result.getMethod().getRetryAnalyzer(result);
		if(retry != null && retry.count < retry.maxCount) {
			test = extent.createTest(result.getMethod().getMethodName()+"- Retry" + (retry.count+1));
			extentTest.set(test);
			test.log(Status.INFO, "Retrying test...");
		}
		else {
			
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		}
		test.info("Running on browser: "+ browser);
		
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().pass("Test Passed");
		extentTest.remove();
		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		extentTest.get().fail(result.getThrowable());
		
		String filePath = null;
		try {
			BaseTest testInstance = (BaseTest) result.getInstance();
			filePath = testInstance.getScreenshot(result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
		extentTest.remove();
		ITestListener.super.onTestFailure(result);
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		Retry retry = (Retry) result.getMethod().getRetryAnalyzer(result);
		if (retry !=null && retry.count > 0) {
			return;
		}
		
		extentTest.get().skip("Test Skipped: " + result.getMethod().getMethodName());
		extentTest.remove();
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extent.flush();
		
		
		ITestListener.super.onFinish(context);
	}

	
	
	
	
}
