package SAILEELATESTCLASS;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.clickonblankspace;
import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class createcustomer extends SAILEELABaseclass {

	// locator
	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By Mastermanagement = AppiumBy.accessibilityId("Master Management");
	By customermastermenu = AppiumBy.accessibilityId("Customer Master");
	By Addcustomer = AppiumBy.accessibilityId("Add Customer");
	By customernamefield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
	By mobilenumberfield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
	By GSTnumberfield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(2)");
	By Addressfield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(3)");
	By Createcustomerbutton = AppiumBy.accessibilityId("Create Customer");
	By searchbox = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")");
	By editbutton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(2)");
	By editnamefield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
	By updatebutton = AppiumBy.accessibilityId("Update");
	By deletebutton = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(3)");
	By deleteconfirmaton = AppiumBy.accessibilityId("Delete");

	

	public String generateMobileNumber() {
		long number = ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L);

		return String.valueOf(number);
	}

	@DataProvider(name = "customerdetails")
	public Object[][] itemDetails() {

		String customername = "Sanket";
		String Mobilenumber = generateMobileNumber();
		String GSTnumber = "27ADHD1234BDBD";
		String Address = "KOlhapur";
		String updatedcustomername = "Shivam";

		return new Object[][] { { customername, Mobilenumber, GSTnumber, Address, updatedcustomername } };
	}

	@Test(dataProvider = "customerdetails", priority = 2)
	public void createcustomerTest(String customername, String Mobilenumber, String GSTnumber, String Address,
			String updatedcustomername) throws InterruptedException {
		test = extent.createTest("Create the customer with code: " + customername);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		// Create Item Method
		driver.findElement(menubar).click();
		driver.findElement(Mastermanagement).click();
		driver.findElement(customermastermenu).click();
		Thread.sleep(4000);
		driver.findElement(Addcustomer).click();
		Thread.sleep(2000);
		driver.findElement(customernamefield).click();
		driver.findElement(customernamefield).sendKeys(customername);
		driver.findElement(mobilenumberfield).click();
		driver.findElement(mobilenumberfield).sendKeys(Mobilenumber);
		driver.findElement(GSTnumberfield).click();
		driver.findElement(GSTnumberfield).sendKeys(GSTnumber);
		driver.findElement(Addressfield).click();
		driver.findElement(Addressfield).sendKeys(Address);
		driver.findElement(Createcustomerbutton).click();

		test.pass("customer created successfully");

	}

	@Test(dataProvider = "customerdetails", priority = 3)
	public void editcustomerTest(String customername, String Mobilenumber, String GSTnumber, String Address,
			String updatedcustomername) throws InterruptedException {
		test = extent.createTest("Edit the customer with code: " + customername);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.findElement(searchbox).click();
		driver.findElement(searchbox).sendKeys(customername);
		driver.findElement(editbutton).click();

		driver.findElement(editnamefield).click();
		driver.findElement(editnamefield).clear();
		driver.findElement(editnamefield).sendKeys(updatedcustomername);

		driver.findElement(updatebutton).click();

		test.pass("customer updated successfully");
		Thread.sleep(2000);
		driver.navigate().back();
		clickonblankspace.clickAtPosition(driver, 740, 1110);
		
	}

//	    @Test(dataProvider = "customerdetails", priority = 4)
//		public void deletecustomerTest(String customername, String Mobilenumber, String GSTnumber, String Address, String updatedcustomername) throws InterruptedException {
//		    test = extent.createTest("Delete the customer with code: " + updatedcustomername);
//		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//		    driver.findElement(searchbox).click();
//		    driver.findElement(searchbox).clear();
//		    Thread.sleep(2000);
//		    driver.findElement(searchbox).sendKeys(updatedcustomername);
//		    
//		    
//		    driver.findElement(deletebutton).click();
//		    
//		    driver.findElement(deleteconfirmaton).click();
//		    
//		    test.pass("Customer deleted successfully");
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
}
