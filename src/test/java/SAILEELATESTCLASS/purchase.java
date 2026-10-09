package SAILEELATESTCLASS;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.clickonblankspace;
import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class purchase extends SAILEELABaseclass {

	// Locator
	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By expenseandpurchase = AppiumBy.accessibilityId("Expenses & Purchase");
	By Purchasemenu = AppiumBy.accessibilityId("Purchase");
	By selectsupplier = AppiumBy.accessibilityId("Select Supplier");
	By Addsupplierbutton = AppiumBy.accessibilityId("Add Supplier");
	By Suppliernamefield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
	By GSTnumberfield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
	By Mobilenumberfield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(2)");
	By Addressnumberfield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(3)");
	By submitbutton = AppiumBy.accessibilityId("Submit");
	By searchbutton = By.xpath("//android.widget.EditText");
	By suppliername = By.xpath("//android.view.View[starts-with(@content-desc,'Pankaj')]");
	By invoiceNumberfield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
	By selectitemlist = AppiumBy.accessibilityId("Please select item");
	By searchboxofitemlist1 = By.xpath("//android.widget.EditText");

	private By item(String itemName) {
		return AppiumBy.androidUIAutomator("new UiSelector().descriptionContains(\"" + itemName + "\")");
	}

	By quantityfield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
	By addrowbutton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)");
	By searchboxitemlist2 = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")");

//	private By item2(String itemName) {
//		return AppiumBy.androidUIAutomator("new UiSelector().descriptionContains(\"" + itemName + "\")");
//	}

	By submitpurchasebutton = AppiumBy.accessibilityId("Submit");

	

	private static AtomicInteger invoiceNumber = new AtomicInteger(5);

	public String generateInvoiceNumber() {

		int nextNumber = invoiceNumber.incrementAndGet();

		String invoice = String.format("INV%03d", nextNumber);

		System.out.println("Generated Invoice = " + invoice);

		return invoice;
	}

	public String generateMobileNumber() {
		long number = ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L);

		return String.valueOf(number);
	}

	@DataProvider(name = "PurchaseDetails")
	public Object[][] itemDetails() {

		String Suppliername = "Pankaj";
		String Mobilenumber = generateMobileNumber();
		String GSTnumber = "29ABCDE1234F1Z5";
		String Address = "KOlhapur";
		String invoicenumber = generateInvoiceNumber();
		String quantity = "100";
		String motoitemName = "Motorola edge 60";
		String appleitemname = "Apple 16";

		return new Object[][] { { Suppliername, Mobilenumber, GSTnumber, Address, invoicenumber, quantity, motoitemName,
				appleitemname } };
	}

	@Test(dataProvider = "PurchaseDetails", priority = 2)
	public void createSupplier(String supplierName, String mobileNumber, String gstNumber, String address,
			String invoiceNumber, String quantity, String motoitemName, String appleitemname) throws Exception {

		test = extent.createTest("Create the Supplier with name: " + supplierName);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(menubar).click();
		driver.findElement(expenseandpurchase).click();
		driver.findElement(Purchasemenu).click();
		driver.findElement(selectsupplier).click();
		driver.findElement(Addsupplierbutton).click();
		driver.findElement(Suppliernamefield).click();
		driver.findElement(Suppliernamefield).sendKeys(supplierName);
		driver.findElement(GSTnumberfield).click();
		driver.findElement(GSTnumberfield).sendKeys(gstNumber);
		driver.findElement(Mobilenumberfield).click();
		driver.findElement(Mobilenumberfield).sendKeys(mobileNumber);
		driver.findElement(Addressnumberfield).click();
		driver.findElement(Addressnumberfield).sendKeys(address);
		driver.findElement(submitbutton).click();
		Thread.sleep(2000);
//	    driver.findElement(searchbutton).click();
//	    driver.findElement(searchbutton).sendKeys(supplierName);
//	    Thread.sleep(2000);
//	    driver.findElement(suppliername).click();
		Thread.sleep(2000);
		test.pass("Supplier created successfully");
	}

	@Test(dataProvider = "PurchaseDetails", priority = 3)
	public void CreatePurchase(String supplierName, String mobileNumber, String gstNumber, String address,
			String invoiceNumber, String quantity, String motoitemName, String appleitemname) throws Exception {

		test = extent.createTest("Create the purche with product name: " + motoitemName, appleitemname);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(invoiceNumberfield).click();
		driver.findElement(invoiceNumberfield).sendKeys(invoiceNumber);
		driver.findElement(selectitemlist).click();
		Thread.sleep(2000);
		driver.findElement(searchboxofitemlist1).click();
		Thread.sleep(4000);
		driver.findElement(searchboxofitemlist1).sendKeys(motoitemName);
		Thread.sleep(2000);
		driver.findElement(item(motoitemName)).click();
		driver.findElement(quantityfield).sendKeys(quantity);
//	    driver.findElement(addrowbutton).click();
//	    driver.findElement(selectitemlist).click();
//	    Thread.sleep(2000);
//	    driver.findElement(searchboxitemlist2).click();
//	    driver.findElement(searchboxitemlist2).sendKeys(appleitemname);
//	    driver.findElement(item2(appleitemname)).click();
//	    driver.findElement(quantityfield).sendKeys(quantity);

		Thread.sleep(2000);
		driver.hideKeyboard();
		driver.findElement(submitpurchasebutton).click();

		test.pass("Purchase order created successfully");
		
		driver.navigate().back();
		Thread.sleep(2000);
		clickonblankspace.clickAtPosition(driver, 740, 1110);

	}
}
