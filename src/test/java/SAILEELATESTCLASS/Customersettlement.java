package SAILEELATESTCLASS;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.clickonblankspace;
import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class Customersettlement extends SAILEELABaseclass {

	// locator
	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By settlementmenu = AppiumBy.accessibilityId("Settlement");
	By settlement = By.xpath("//android.view.View[@content-desc=\"Settlement\"]");
	By searchfield = By.xpath("//android.widget.EditText");
	// By inwardbutton=AppiumBy.androidUIAutomator("new
	// UiSelector().className(\"android.widget.Button\").instance(2)");
	By viewbuttonn = By.xpath("(//android.widget.HorizontalScrollView//android.widget.Button)[2]");
	By closebutton = AppiumBy.accessibilityId("Close");
	By inwardbutton = By.xpath("(//android.widget.HorizontalScrollView//android.widget.Button)[1]");
	By settlementamountfield = By.xpath("//android.widget.EditText");
	By UPI = AppiumBy.accessibilityId("UPI");
	By submit = AppiumBy.accessibilityId("Submit");

	// scrolling method
	public void scrollRightUntilVisible(By elementLocator) throws InterruptedException {

		int maxScrolls = 6;

		for (int i = 0; i < maxScrolls; i++) {

			try {

				if (driver.findElement(elementLocator).isDisplayed()) {
					System.out.println("Element is visible.");
					return;
				}

			} catch (org.openqa.selenium.NoSuchElementException e) {

				System.out.println("Element not visible. Scrolling...");
			}

			// Scroll the table horizontally to the RIGHT
			driver.executeScript("mobile: scrollGesture",
					Map.of("left", 20, "top", 300, "width", 760, "height", 100, "direction", "right", "percent", 0.80));

			Thread.sleep(1000);
		}

		throw new org.openqa.selenium.NoSuchElementException("Element not visible after horizontal scrolling.");
	}

	

	@DataProvider(name = "settlementdetails")
	public Object[][] itemDetails() {
		String customername = "shrikant";
		String settlementamount = "100";
		return new Object[][] { { customername, settlementamount } };
	}

	@Test(dataProvider = "settlementdetails", priority = 2)
	public void settlementofcustomer(String customername, String settlementamount) throws Exception {

		test = extent.createTest("settlement of customer name " + customername);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(menubar).click();
		driver.findElement(settlementmenu).click();
		driver.findElement(settlement).click();
		driver.findElement(searchfield).click();
		driver.findElement(searchfield).sendKeys(customername);
		driver.hideKeyboard();
		driver.findElement(viewbuttonn).click();
		Thread.sleep(2000);
		driver.findElement(closebutton).click();

		driver.findElement(inwardbutton).click();
		Thread.sleep(2000);
		driver.findElement(settlementamountfield).click();
		driver.findElement(settlementamountfield).sendKeys(settlementamount);
		driver.findElement(UPI).click();
		driver.findElement(submit).click();
		test.pass(" Customer settlement done successfully");
		
		driver.navigate().back();
		clickonblankspace.clickAtPosition(driver, 740, 1110);

	}
}
