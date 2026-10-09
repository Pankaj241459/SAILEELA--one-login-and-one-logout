package SAILEELATESTCLASS;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class Clearreportdata extends SAILEELABaseclass {
	
	// Locators
		By profilemenu =AppiumBy.accessibilityId("Swapnil Thakur"); 
		By clearreportdata = AppiumBy.accessibilityId("Clear Report Data");
		By submitbutton = AppiumBy.accessibilityId("Submit");
		By yesbutton=AppiumBy.accessibilityId("Yes");
		
		
		
		@Test (priority=1)
		public void cleardata() {
			
			test = extent.createTest("Clear report data");
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			driver.findElement(profilemenu).click();
			
			driver.findElement(clearreportdata).click();
			
			driver.findElement(submitbutton).click();
			
			driver.findElement(yesbutton).click();
			
			test.pass("Report data cleared successfully.");
			
			
			
		}
	
	
	

}
