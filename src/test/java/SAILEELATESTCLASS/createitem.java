package SAILEELATESTCLASS;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.clickonblankspace;

import java.time.Duration;

import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class createitem extends SAILEELABaseclass {

	// locator
	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By Mastermanagement = AppiumBy.accessibilityId("Master Management");
	By Itemmenu = AppiumBy.accessibilityId("Items");
	By AddItem = AppiumBy.accessibilityId("Add Item");
	By itemcodefield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
	By sectionnamedropdown = AppiumBy.accessibilityId("Select Section");
	By Sectionname = AppiumBy.accessibilityId("Electronics");
	By categorydropdown = AppiumBy.accessibilityId("Select Category");
	By categoryname = AppiumBy.accessibilityId("new Mobile");
	By Itemnamefield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
	By itemshortname = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(2)");
	By selectUOMdropdown = AppiumBy.accessibilityId("selectUOMType");
	By selectUOM = AppiumBy.accessibilityId("Nos");
	By pricingfield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(3)");
	By GSTfield = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(4)");;
	By stockitemcheckbox = AppiumBy.accessibilityId("Stock Item");
	By MenuItemcheckbox = AppiumBy.accessibilityId("Menu Item");
	By Selectfranchisecodedropdown = AppiumBy
			.androidUIAutomator("new UiSelector().description(\"Select Franchise Code\")");

	By Uploadimagefield = AppiumBy.androidUIAutomator("new UiSelector().description(\"Tap to upload\")");
	By image = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.android.documentsui:id/icon_mime_lg\")");
	By createitem = AppiumBy.accessibilityId("Create Item");
	By searchbox = By.xpath("//android.widget.EditText");
	By edititemnamebutton = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(3)");
	By shortNameField = AppiumBy.androidUIAutomator("new UiSelector().text(\"MO\")");
	By updatedshortnamefield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
	By savebutton = AppiumBy.accessibilityId("Save");
	By Deleteitemnamebutton = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(4)");
	By deleteconfirmation = AppiumBy.accessibilityId("Yes");
	By exportbutton=AppiumBy.accessibilityId("Export");
	By exportwithMRP=AppiumBy.accessibilityId("Export with MRP");
	By stockitem=AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\").instance(0)");

	

	@DataProvider(name = "itemDetails")
	public Object[][] itemDetails() {

		String itemCode = "ITEM201";
		String itemName = "Motorola edge 60";
		String itemShortName = "MO";
		String price = "30000";
		String gst = "5";
		String franchiseCode = "1-राजा सरकी(kg)";
		String updatedshortname = "MOTO";

		return new Object[][] { { itemCode, itemName, itemShortName, price, gst, franchiseCode, updatedshortname } };
	}

	@Test(dataProvider = "itemDetails", priority = 2)
	public void createItemTest(String itemCode, String itemName, String itemShortName, String price, String gst,
			String franchiseCode, String updatedshortname) throws InterruptedException {
		test = extent.createTest("Create the item with code: " + itemCode);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		// Create Item Method
		driver.findElement(menubar).click();
		driver.findElement(Mastermanagement).click();
		driver.findElement(Itemmenu).click();
		Thread.sleep(4000);
		driver.findElement(AddItem).click();
		Thread.sleep(2000);
		driver.findElement(itemcodefield).click();
		Thread.sleep(2000);
		driver.findElement(itemcodefield).clear();
		driver.findElement(itemcodefield).sendKeys(itemCode);
		driver.findElement(sectionnamedropdown).click();
		driver.findElement(Sectionname).click();
		driver.findElement(categorydropdown).click();
		driver.findElement(categoryname).click();
		driver.findElement(Itemnamefield).click();
		driver.findElement(Itemnamefield).sendKeys(itemName);
		driver.findElement(itemshortname).click();
		driver.findElement(itemshortname).sendKeys(itemShortName);
		driver.findElement(selectUOMdropdown).click();
		driver.findElement(selectUOM).click();
		Thread.sleep(2000);
		driver.findElement(pricingfield).click();
		driver.findElement(pricingfield).sendKeys(price);
		Thread.sleep(2000);
		driver.findElement(GSTfield).click();
		driver.findElement(GSTfield).sendKeys(gst);
		Thread.sleep(2000);
		driver.findElement(stockitemcheckbox).click();
		driver.findElement(MenuItemcheckbox).click();
		Thread.sleep(2000);
//	    driver.findElement(Selectfranchisecodedropdown).click();
//	    Thread.sleep(2000);
//	    By franchiseCodeValue =
//	            AppiumBy.androidUIAutomator(
//	                "new UiSelector().description(\""
//	                + franchiseCode
//	                + "\")"
//	            );
		// driver.findElement(franchiseCodeValue).click();
//		Thread.sleep(4000);
//		driver.findElement(Uploadimagefield).click();
//		Thread.sleep(3000);
//		driver.manage().wait(10);
//		driver.findElement(image).click();
		driver.hideKeyboard();
		Thread.sleep(3000);
		driver.findElement(createitem).click();
	}

	@Test(dataProvider = "itemDetails", priority = 3, dependsOnMethods = "createItemTest")
	public void edititem(String itemCode, String itemName, String itemShortName, String price, String gst,
			String franchiseCode, String updatedshortname) throws InterruptedException {

		test = extent.createTest("Edit the item with name: " + itemName);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Search item
		driver.findElement(searchbox).click();

		driver.findElement(searchbox).clear();

		driver.findElement(searchbox).sendKeys(itemName);

		// Click item
		driver.findElement(edititemnamebutton).click();

		// Click
		driver.findElement(shortNameField).click();

		// Clear
		driver.findElement(shortNameField).clear();

		// Enter new Short Name
		driver.findElement(updatedshortnamefield).sendKeys(updatedshortname);
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
				+ ".scrollIntoView(new UiSelector().description(\"Save\"))"));
		// Save
		driver.findElement(savebutton).click();
	}

//	@Test(dataProvider = "itemDetails", priority = 4,dependsOnMethods = "edititem")
//	public void deleteitem(String itemCode, String itemName, String itemShortName, String price, String gst, String franchiseCode,String Updateditemname) {
//		
//	    test = extent.createTest("Delete the item with name: " + itemName);
//	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//	    // Delete Item Method
//	    
//	    driver.findElement(searchbox).click();
//	    driver.findElement(searchbox).clear();
//	    driver.findElement(searchbox).sendKeys(itemName);
//	   
//	    driver.findElement(Deleteitemnamebutton).click();
//	 
//	    driver.findElement(deleteconfirmation).click();
//	}
	
	@Test(dataProvider = "itemDetails", priority = 5,dependsOnMethods = "edititem")
	public void deleteitem(String itemCode, String itemName, String itemShortName, String price, String gst, String franchiseCode,String Updateditemname) throws InterruptedException {
		
	    test = extent.createTest("Export item list with the MRP");
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	    driver.findElement(searchbox).clear();
	    driver.findElement(stockitem).click();
	    
	    driver.findElement(exportbutton).click();
	    driver.findElement(exportwithMRP).click();
	    
	    
	    test.pass("Items exported with MRP successfully");
	    Thread.sleep(2000);
	    driver.hideKeyboard();
	    driver.navigate().back();
	    clickonblankspace.clickAtPosition(driver, 740, 1110);
	    
	    
	    

}}
