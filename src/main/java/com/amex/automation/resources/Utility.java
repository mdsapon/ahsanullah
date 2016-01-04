package com.amex.automation.resources;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility {
	
	public static String captureScreenShot(WebDriver driver, String imageName) {
		try{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File sources= ts.getScreenshotAs(OutputType.FILE);
	    String dest="./ScreenShots/"+imageName+".png";
	    File destination=new File(dest);
		FileUtils.copyFile(sources, destination);
		System.out.println("screenshot taken");
		return dest;
		}catch(Exception e){
			System.out.println("Exception while taking screenshot "+e.getMessage());
			return e.getMessage();
		}
		
	}

}
