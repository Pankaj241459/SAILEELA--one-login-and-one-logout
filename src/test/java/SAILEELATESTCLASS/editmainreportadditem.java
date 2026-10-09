package SAILEELATESTCLASS;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.clickonblankspace;
import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class editmainreportadditem extends SAILEELABaseclass {

	// locator
	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By POSreport = AppiumBy.accessibilityId("POS Report");
	By mainreport = AppiumBy.accessibilityId("Main Report");
	By searchbutton = By.xpath("//android.widget.EditText");
	By editbutton = By.xpath("//android.widget.HorizontalScrollView/android.view.View/android.widget.Button[2]");
	By openbill = AppiumBy.accessibilityId("Open Bill");
	By itemsearchfield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
	By quantityfield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
	By addproductbutton = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)");
	By chargesfield = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(32)");
	By discountfield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
	By packagingchargesfield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
	By savebutton = AppiumBy.accessibilityId("Save");
	By subTotal = By.xpath("//*[contains(@content-desc,'Sub Total:')]");
	By printbutton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(2)");
	By cashUPI = AppiumBy.androidUIAutomator("new UiSelector().description(\"Cash+UPI\")");
	By cashfield = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")");
	By UPIfield = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(10)");
	By submitpayment = AppiumBy.androidUIAutomator("new UiSelector().description(\"Submit\")");
	By submitandclose = AppiumBy.accessibilityId("Submit & Close");
	By selectupidropdown = AppiumBy.accessibilityId("Select UPI");

	private By upiselection(String upiName) {
		return AppiumBy.androidUIAutomator("new UiSelector().descriptionContains(\"" + upiName + "\")");
	}

	By submit = AppiumBy.accessibilityId("Submit");
	By profilebutton = AppiumBy.accessibilityId("Swapnil Thakur");
	By syncbill = AppiumBy.accessibilityId("Sync Bills");
	By submitallbills = AppiumBy.accessibilityId("Submit All Bills");
	By exportbutton = AppiumBy.accessibilityId("Export");
	By exportaspdf = AppiumBy.accessibilityId("Export as PDF");
	By pdfSaved = AppiumBy.accessibilityId("Main Reports");

	

	@DataProvider(name = "editbillItemDetails")
	public Object[][] itemDetails() {
		String Billnumber = "0110640001";
		String itemname = "Malai";
		String quantity = "2";
		String Discount = "200";
		String packagingcharges = "100";
		String cash = "30000";
		String upi = "30260";
		String customername = "shivam";
		return new Object[][] {
				{ Billnumber, itemname, quantity, Discount, packagingcharges, cash, upi, customername } };
	}

	@Test(dataProvider = "editbillItemDetails", priority = 1)
	public void posell(String Billnumber, String itemname, String quantity, String Discount, String packagingcharges,
			String cash, String upi, String customername) throws InterruptedException {
		test = extent.createTest("Edit bill with add product  " + Billnumber);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(menubar).click();

		driver.findElement(POSreport).click();
		driver.findElement(mainreport).click();

		driver.findElement(searchbutton).click();
		driver.findElement(searchbutton).sendKeys(Billnumber);
		driver.findElement(editbutton).click();
		driver.findElement(openbill).click();

		driver.findElement(itemsearchfield).click();
		driver.findElement(itemsearchfield).sendKeys(itemname);
		driver.findElement(quantityfield).click();
		driver.findElement(quantityfield).sendKeys(quantity);
		driver.findElement(addproductbutton).click();
		Thread.sleep(2000);
		driver.findElement(chargesfield).click();
		driver.findElement(discountfield).click();
		driver.findElement(discountfield).clear();
		driver.findElement(discountfield).sendKeys(Discount);
		driver.findElement(packagingchargesfield).click();
		driver.findElement(packagingchargesfield).clear();
		driver.findElement(packagingchargesfield).sendKeys(packagingcharges);
		driver.findElement(savebutton).click();

		String actualSubTotalText = driver.findElement(subTotal).getAttribute("contentDescription");
		String actualSubTotal = actualSubTotalText
				.substring(actualSubTotalText.indexOf("Sub Total:") + "Sub Total:".length()).trim();
		String expectedSubTotal = "₹ 60,160.00";

		System.out.println("Expected Sub Total: " + expectedSubTotal);
		System.out.println("Actual Sub Total: " + actualSubTotal);

		if (actualSubTotal.equals(expectedSubTotal)) {
			System.out.println("Sub Total matched successfully");
			test.info("Sub Total matched successfully");
		} else {
			System.out.println("Sub Total is not matching");
			Assert.fail("Sub Total mismatch. Expected: " + expectedSubTotal + ", Actual: " + actualSubTotal);
		}

		Thread.sleep(2000);

		driver.findElement(printbutton).click();
		driver.findElement(cashUPI).click();
		driver.findElement(cashfield).click();
		driver.findElement(cashfield).sendKeys(cash);
		driver.findElement(UPIfield).click();
		driver.findElement(UPIfield).sendKeys(upi);
		Thread.sleep(2000);
		
		driver.findElement(submitpayment).click();
		Thread.sleep(2000);
		driver.findElement(submitandclose).click();
		Thread.sleep(2000);

		driver.navigate().back();

//		driver.findElement(profilebutton).click();
//		driver.findElement(syncbill).click();
//		Thread.sleep(2000);
//		driver.findElement(submitallbills).click();

	}

	@Test(priority = 2)
	public void exportreport() throws Exception {
		test = extent.createTest("Export main report");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(exportbutton).click();
		driver.findElement(exportaspdf).click();
		Thread.sleep(3000);

		driver.findElement(pdfSaved).click();

		Thread.sleep(15000);
		driver.navigate().back();
		Thread.sleep(2000);
		driver.navigate().back();
		clickonblankspace.clickAtPosition(driver, 740, 1110);

	}

}
