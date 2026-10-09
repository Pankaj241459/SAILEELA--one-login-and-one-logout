package SAILEELATESTCLASS;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.clickonblankspace;
import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class Createinvoice extends SAILEELABaseclass {

	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By INVOICEemenu = AppiumBy.accessibilityId("INVOICE");
	By Guestname = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
	By BillTo = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
	By BilltoGST = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(2)");
	By Billtoaddress = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(3)");
	By itemname1 = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(4)");
	By quantity1 = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(5)");
	By firstrowrate = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(6)");
	By additembutton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)");
	By itemname2 = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(7)");
	By quantity2 = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(8)");
	By secondrowrate = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(9)");

	By submitbutton = AppiumBy.accessibilityId("Submit");
	By openpdf = AppiumBy.accessibilityId("INVOICE");

	

	@DataProvider(name = "invoicedetails")
	public Object[][] itemDetails() {
		String guestName = "Rahul";
		String billTo = "Rahul Patil";
		String billToGST = "27ABCDE1234F1Z5";
		String billToAddress = "Kolhapur";
		String itemName1 = "Motorola edge 60";
		String qty1 = "50";
		String rate1 = "200";
		String itemName2 = "Malai";
		String qty2 = "20";
		String rate2 = "250";

		return new Object[][] {
				{ guestName, billTo, billToGST, billToAddress, itemName1, qty1, rate1, itemName2, qty2, rate2, } };
	}

	@Test(dataProvider = "invoicedetails", priority = 1)
	public void createInvoice(String guestName, String billTo, String billToGST, String billToAddress, String itemName1,
			String qty1, String rate1, String itemName2, String qty2, String rate2) throws Exception {

		test = extent.createTest("Create Invoice");

		driver.findElement(menubar).click();
		driver.findElement(INVOICEemenu).click();
		driver.findElement(Guestname).click();
		driver.findElement(Guestname).sendKeys(guestName);
		driver.findElement(BillTo).click();
		driver.findElement(BillTo).sendKeys(billTo);
		driver.findElement(BilltoGST).click();
		driver.findElement(BilltoGST).sendKeys(billToGST);
		driver.findElement(Billtoaddress).click();
		driver.findElement(Billtoaddress).sendKeys(billToAddress);

		driver.findElement(itemname1).click();
		driver.findElement(itemname1).sendKeys(itemName1);
		driver.findElement(quantity1).click();
		driver.findElement(quantity1).sendKeys(qty1);
		driver.findElement(firstrowrate).click();
		driver.findElement(firstrowrate).sendKeys(rate1);

		driver.findElement(additembutton).click();
		driver.findElement(itemname2).click();
		driver.findElement(itemname2).sendKeys(itemName2);
		driver.findElement(quantity2).click();
		driver.findElement(quantity2).sendKeys(qty2);
		driver.findElement(secondrowrate).click();
		driver.findElement(secondrowrate).sendKeys(rate2);

		driver.hideKeyboard();
		driver.findElement(submitbutton).click();

		driver.findElement(openpdf).click();
		Thread.sleep(20000);

		driver.navigate().back();
		Thread.sleep(2000);
		driver.navigate().back();
		clickonblankspace.clickAtPosition(driver, 740, 1110);

	}

}
