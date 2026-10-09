package SAILEELATESTCLASS;

import java.time.Duration;

import org.openqa.selenium.By;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.clickonblankspace;
import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class checkinventorystockaftersale extends SAILEELABaseclass {

	// locator
	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By POSreport = AppiumBy.accessibilityId("POS Report");
	By inventoryreport = AppiumBy.accessibilityId("Inventory Report");
	By searchitemfield = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")");
	By availablestock = AppiumBy.accessibilityId("98.0");

	

	@DataProvider(name = "ItemDetails")
	public Object[][] itemDetails() {
		String itemname = "Motorola edge 60";
		String expectedstock = "98";
		return new Object[][] { { itemname, expectedstock } };
	}

	@Test(dataProvider = "ItemDetails", priority = 2)
	public void checkstock(String itemname, String expectedstock) throws InterruptedException {
		test = extent.createTest("check inventory stock of " + itemname);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(menubar).click();

		driver.findElement(POSreport).click();
		driver.findElement(inventoryreport).click();

		driver.findElement(searchitemfield).click();
		driver.findElement(searchitemfield).sendKeys(itemname);
		String actualstock = driver.findElement(availablestock).getAttribute("contentDescription");

		System.out.println("Available stock is : " + actualstock);
		System.out.println("Expected stock is  : " + expectedstock);

		// Convert String values to numbers
		double actual = Double.parseDouble(actualstock);
		double expected = Double.parseDouble(expectedstock);

		// Compare
		if (actual == expected) {

			System.out.println("Stock is correct");

			test.pass("Stock validation passed. Available Stock: " + actual + ", Expected Stock: " + expected);

		} else {

			System.out.println("Stock is not correct");

			test.fail("Stock validation failed. Available Stock: " + actual + ", Expected Stock: " + expected);

		}
		
		driver.hideKeyboard();
		Thread.sleep(2000);
		driver.navigate().back();
		clickonblankspace.clickAtPosition(driver, 740, 1110);
	}
}
