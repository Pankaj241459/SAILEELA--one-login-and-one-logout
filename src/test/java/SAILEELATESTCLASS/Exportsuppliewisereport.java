package SAILEELATESTCLASS;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Utility.clickonblankspace;
import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class Exportsuppliewisereport extends SAILEELABaseclass {
	String today = LocalDate.now().format(DateTimeFormatter.ofPattern("d/M/yyyy"));
	
	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By POSreport = AppiumBy.accessibilityId("POS Report");
	By supplierwisereport = AppiumBy.accessibilityId("Supplier-Wise Report");
	By filter=AppiumBy.accessibilityId("Filter");
	By fromDate = By.xpath(
		    "(//android.view.View[@content-desc='" + today + "'])[1]"
		);
	By backdate=AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(2)");
	By fristsep=AppiumBy.accessibilityId("1, Tuesday, September 1, 2026");
	By okbutton=AppiumBy.accessibilityId("OK");
	By submitbutton=AppiumBy.accessibilityId("Submit");
	By exportbutton = AppiumBy.accessibilityId("Export");
	By exportaspdf = AppiumBy.accessibilityId("Export as PDF");
	By openpdf = AppiumBy.accessibilityId("Customer Invoice Report");

	

	@Test(priority = 1)
	public void exportreport() throws Exception {
		test = extent.createTest("Export customer invoice Reportt");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(menubar).click();
		driver.findElement(POSreport).click();
		driver.findElement(supplierwisereport).click();
		driver.findElement(filter).click();
		driver.findElement(fromDate).click();
		driver.findElement(backdate).click();
		driver.findElement(fristsep).click();
		driver.findElement(okbutton).click();
		driver.findElement(submitButton).click();
		driver.findElement(exportbutton).click();
		driver.findElement(exportaspdf).click();
		
		driver.findElement(openpdf).click();
		Thread.sleep(25000);
		driver.navigate().back();
		
		
		test.pass("customer invoice report downloaded successfully");
		Thread.sleep(2000);
		driver.navigate().back();
		clickonblankspace.clickAtPosition(driver, 740, 1110);

	}


}
