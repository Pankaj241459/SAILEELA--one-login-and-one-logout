package SAILEELATESTCLASS;

import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.clickonblankspace;
import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class Deletebill extends SAILEELABaseclass {

	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By POSreport = AppiumBy.accessibilityId("POS Report");
	By mainreport = AppiumBy.accessibilityId("Main Report");
	By searchbutton = By.xpath("//android.widget.EditText");
	By deletebutton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(5)");
	By enterreason = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")");
	By yesbutton = AppiumBy.accessibilityId("Yes");
	By exportbutton = AppiumBy.accessibilityId("Export");
	By exportaspdf = AppiumBy.accessibilityId("Export as PDF");
	By pdfSaved = AppiumBy.accessibilityId("Main Reports");

	

	@DataProvider(name = "invoicedetails")
	public Object[][] itemDetails() {
		String invoicenumber = "0110640001";
		String reason = "incorrect entry";
		return new Object[][] { { invoicenumber, reason } };
	}

	@Test(dataProvider = "invoicedetails", priority = 1)
	public void deleteinvoice(String invoicenumber, String reason) throws Exception {

		test = extent.createTest("Inward the stock of invoice number: " + invoicenumber);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(menubar).click();
		driver.findElement(POSreport).click();
		driver.findElement(mainreport).click();
		driver.findElement(searchbutton).click();
		driver.findElement(searchbutton).sendKeys(invoicenumber);
		driver.hideKeyboard();

		driver.findElement(deletebutton).click();
		driver.findElement(enterreason).click();
		driver.findElement(enterreason).sendKeys(reason);

		driver.findElement(yesbutton).click();

		test.info("Deleted the bill with invoice number: " + invoicenumber + " with reason: " + reason);

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
