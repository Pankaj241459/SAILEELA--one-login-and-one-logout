package SAILEELATESTCLASS;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.clickonblankspace;
import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class createcategory extends SAILEELABaseclass {
	// Locators
	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By Mastermanagement = AppiumBy.accessibilityId("Master Management");
	By categorymenu = AppiumBy.accessibilityId("Category");
	By Addcategorybutton = AppiumBy.accessibilityId("Add Category");
	By categorynamefield = By.xpath("//android.widget.EditText");
	By sectionname = AppiumBy.accessibilityId("Electronics");
	By submitcategorybutton = AppiumBy.accessibilityId("Submit");
	By searchbox = By.xpath("//android.widget.EditText");
	By editbutton = By.xpath(
			"//android.view.View[@content-desc=\"Total Categories: 1\"]/android.view.View[2]/android.view.View[2]/android.view.View/android.widget.Button[1]");

	By updatebutton = AppiumBy.accessibilityId("Update");
	By deletebutton = By.xpath(
			"//android.view.View[@content-desc=\"Total Categories: 1\"]/android.view.View[2]/android.view.View[2]/android.view.View/android.widget.Button[2]");
	By deleteconfirmation = AppiumBy.accessibilityId("Yes");

	String categoryname = "oldMObile";
	String updatedcategoryname = "new Mobile";

	

	@DataProvider(name = "categorydata")
	public Object[][] testdata() {

		return new Object[][] { { categoryname, updatedcategoryname } };
	}

	@Test(dataProvider = "categorydata", priority = 2)
	public void cratecategoryTest(String categoryname, String updatedcategoryname) throws InterruptedException {

		test = extent.createTest("Create the category with name: " + categoryname);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		// Create Section Method
		driver.findElement(menubar).click();
		driver.findElement(Mastermanagement).click();
		driver.findElement(categorymenu).click();
		Thread.sleep(2000);
		driver.findElement(Addcategorybutton).click();
		driver.findElement(categorynamefield).click();

		driver.findElement(categorynamefield).sendKeys(categoryname);
		driver.findElement(sectionname).click();
		driver.findElement(submitcategorybutton).click();

		test.pass("Section created successfully with name: " + categoryname);
	}

	@Test(dataProvider = "categorydata", priority = 3, dependsOnMethods = "cratecategoryTest")
	public void editcategoryTest(String categoryname, String updatedcategoryname) throws InterruptedException {

		test = extent.createTest("Edit the category with name: " + updatedcategoryname);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		// Create Section Method
		Thread.sleep(2000);
		driver.findElement(searchbox).click();
		driver.findElement(searchbox).sendKeys(categoryname);
		driver.findElement(editbutton).click();
		driver.findElement(categorynamefield).click();
		driver.findElement(categorynamefield).clear();
		driver.findElement(categorynamefield).sendKeys(updatedcategoryname);
		driver.findElement(updatebutton).click();

		test.pass("Section created successfully with name: " + updatedcategoryname);
		
		driver.navigate().back();
		clickonblankspace.clickAtPosition(driver, 740, 1110);
	}

//		 @Test(dataProvider = "categorydata", priority = 4,dependsOnMethods ="editcategoryTest")
//		 public void deletecategoryTest(String categoryname, String updatedcategoryname) throws InterruptedException {
//
//			test = extent.createTest("Delete the category with name: " + updatedcategoryname);
//
//			// Create Section Method
//			Thread.sleep(2000);
//			driver.findElement(searchbox).click();
//			driver.findElement(searchbox).clear();
//			driver.findElement(searchbox).sendKeys(updatedcategoryname);
//			Thread.sleep(2000);
//			driver.findElement(deletebutton).click();
//			driver.findElement(deleteconfirmation).click();
//			
//			test.pass("Section created successfully with name: " + updatedcategoryname);
//			
//			
//		}
	
	
}
