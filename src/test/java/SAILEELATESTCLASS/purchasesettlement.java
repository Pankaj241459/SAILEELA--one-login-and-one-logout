package SAILEELATESTCLASS;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.clickonblankspace;
import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class purchasesettlement extends SAILEELABaseclass {

	// locator
	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By settlementmenu = AppiumBy.accessibilityId("Settlement");
	By PurchaseSettlementmenu = AppiumBy.accessibilityId("Purchase Settlement");
	By searchfield = By.xpath("//android.widget.EditText");
	// By inwardbutton=AppiumBy.androidUIAutomator("new
	// UiSelector().className(\"android.widget.Button\").instance(2)");
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

	

	@DataProvider(name = "stockinwardDetails")
	public Object[][] itemDetails() {
		String invoicenumber = "INV007";
		String settlementamount = "3150000";
		return new Object[][] { { invoicenumber, settlementamount } };
	}

	@Test(dataProvider = "stockinwardDetails", priority = 2)
	public void inwardstock(String invoiceNumber, String settlementamount) throws Exception {

		test = extent.createTest("Inward the stock of invoice number: " + invoiceNumber);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(menubar).click();
		driver.findElement(settlementmenu).click();
		driver.findElement(PurchaseSettlementmenu).click();
		driver.findElement(searchfield).click();
		driver.findElement(searchfield).sendKeys(invoiceNumber);
		driver.hideKeyboard();

		scrollRightUntilVisible(inwardbutton);
		driver.findElement(inwardbutton).click();
		Thread.sleep(2000);
		driver.findElement(settlementamountfield).click();
		driver.findElement(settlementamountfield).sendKeys(settlementamount);
		driver.findElement(UPI).click();
		driver.findElement(submit).click();
		test.pass("purchase settlement done against invoice number" +invoiceNumber);
		
		driver.navigate().back();
		clickonblankspace.clickAtPosition(driver, 740, 1110);

	}
}
