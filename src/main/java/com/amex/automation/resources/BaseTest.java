package com.amex.automation.resources;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/*
 * This is a base Test class. All testng tests needs to inherited from
 * this class. It contains common reporting infra
 */
public class BaseTest {

	public static WebDriver driver;
	ExtentTest logger;
	
	@BeforeMethod
	public void beforeMethod(Method caller) {
	    logger=ComplexReportFactory.getTest(caller.getName(), "This is a simple test from complex factory");
	    
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		
	}

	@AfterMethod
	public void afterMethod(ITestResult result,Method caller) {

		if(ITestResult.FAILURE==result.getStatus()){
			String screenshotPath= Utility.captureScreenShot(driver, result.getName());
			String image=logger.addScreenCapture(screenshotPath);
			logger.log(LogStatus.FAIL, "Title verification", image);
		}
		
	     //reports.endTest(logger);
	     //reports.flush();
	     driver.quit();
		ComplexReportFactory.closeTest(caller.getName());

	}

	/*
	 * After suite will be responsible to close the report properly at the end
	 * You an have another afterSuite as well in the derived class and this one
	 * will be called in the end making it the last method to be called in test exe
	 */
	@AfterSuite
	public void afterSuite() {
		ComplexReportFactory.closeReport();
	}

}
