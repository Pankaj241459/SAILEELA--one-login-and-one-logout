package SAILEELATESTCLASS;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class POSsell extends SAILEELABaseclass {

	// locator
	By POSbutton = AppiumBy.accessibilityId("POS");
	By itemsearchfield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
	By quantityfield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
	By addproductbutton = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)");
	By chargesfield = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(24)");
	By discountfield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
	By packagingchargesfield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
	By savebutton = AppiumBy.accessibilityId("Save");
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

	By selectcustomer = AppiumBy.accessibilityId("Select Customer");
	By searchcustomerfield = By.xpath("//android.widget.EditText");
	By shivam = By.xpath("(//*[contains(@content-desc,'Shivam')])[1]");
	By submit = AppiumBy.accessibilityId("Submit");
	By profilebutton = AppiumBy.accessibilityId("Swapnil Thakur");
	By syncbill = AppiumBy.accessibilityId("Sync Bills");
	By submitallbills = AppiumBy.accessibilityId("Submit All Bills");

	

	@DataProvider(name = "ItemDetails")
	public Object[][] itemDetails() {
		String itemname = "Motorola edge 60";
		String quantity = "2";
		String Discount = "200";
		String packagingcharges = "200";
		String cash = "30000";
		String upi = "30000";
		String customername = "shivam";
		return new Object[][] { { itemname, quantity, Discount, packagingcharges, cash, upi, customername } };
	}

	@Test(dataProvider = "ItemDetails", priority = 1)
	public void posell(String itemname, String quantity, String Discount, String packagingcharges, String cash,
			String upi, String customername) throws InterruptedException {
		test = extent.createTest("Point Of Sell of " + itemname);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(POSbutton).click();
		driver.findElement(itemsearchfield).click();
		driver.findElement(itemsearchfield).sendKeys(itemname);
		driver.findElement(quantityfield).click();
		driver.findElement(quantityfield).sendKeys(quantity);
		driver.findElement(addproductbutton).click();
		Thread.sleep(2000);
		driver.findElement(chargesfield).click();
		driver.findElement(discountfield).click();
		driver.findElement(discountfield).sendKeys(Discount);
		driver.findElement(packagingchargesfield).click();
		driver.findElement(packagingchargesfield).sendKeys(packagingcharges);
		driver.findElement(savebutton).click();

		driver.findElement(printbutton).click();
		driver.findElement(cashUPI).click();
		driver.findElement(cashfield).click();
		driver.findElement(cashfield).sendKeys(cash);
		driver.findElement(UPIfield).click();
		driver.findElement(UPIfield).sendKeys(upi);
		Thread.sleep(2000);
		driver.findElement(selectupidropdown).click();
		driver.findElement(upiselection("Swapnil (skthakur123@ybl)")).click();
		driver.findElement(submitpayment).click();
		Thread.sleep(2000);
		driver.findElement(submitandclose).click();
		Thread.sleep(2000);
//		driver.findElement(selectcustomer).click();
//		driver.findElement(searchcustomerfield).click();
//		driver.findElement(searchcustomerfield).sendKeys(customername);
//		driver.findElement(shivam).click();
//		driver.findElement(submit).click();

		driver.navigate().back();

		test.pass("product sale successfully");

	}

	@Test(priority = 2)
	public void syncbill() throws InterruptedException {
		test = extent.createTest("sync pending bills");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(profilebutton).click();
		driver.findElement(syncbill).click();
		Thread.sleep(2000);
		driver.findElement(submitallbills).click();
		

	}
}
