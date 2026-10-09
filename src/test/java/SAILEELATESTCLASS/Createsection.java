package SAILEELATESTCLASS;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;
import Utility.clickonblankspace;

public class Createsection extends SAILEELABaseclass {
// Locators
	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By Mastermanagement = AppiumBy.accessibilityId("Master Management");
	By Section = AppiumBy.accessibilityId("Section");
	By Addsection = AppiumBy.accessibilityId("Add Section");
	By Sectionnamefield = By.xpath("//android.widget.EditText");
	By savesection = AppiumBy.accessibilityId("Save");

	By editsectionbutton(String sectionName) {
		return By.xpath("//android.view.View[@content-desc=\"1\n" + sectionName + "\"]/android.widget.Button[1]");
	}

	By editsectionnamefield(String sectionName) {
		return By.xpath("//android.widget.EditText[@text=\"" + sectionName + "\"]");
	}

	By updatebutton = AppiumBy.accessibilityId("Update");

	By deletebutton(String sectionName) {
		return By
				.xpath("//android.view.View[contains(@content-desc,\"" + sectionName + "\")]/android.widget.Button[2]");
	}

	By deleteconfirmation = AppiumBy.accessibilityId("Yes");
	By Backbutton = AppiumBy.accessibilityId("Back");

	// Test Data
	String sectionname = "Mobile";
	String updatedsectionname = "Electronics";

	

	@DataProvider(name = "sectiondata")
	public Object[][] loginData() {

		return new Object[][] { { sectionname, updatedsectionname } };
	}

	@Test(dataProvider = "sectiondata", priority = 2)
	public void crateSectionTest(String sectionname, String updatedsectionname) throws InterruptedException {

		test = extent.createTest("Create the section with name: " + sectionname);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		// Create Section Method
		driver.findElement(menubar).click();
		driver.findElement(Mastermanagement).click();
		driver.findElement(Section).click();
		Thread.sleep(2000);
		driver.findElement(Addsection).click();
		driver.findElement(Sectionnamefield).click();
		driver.findElement(Sectionnamefield).sendKeys(sectionname);
		driver.findElement(savesection).click();

		test.pass("Section created successfully with name: " + sectionname);
	}

	@Test(dataProvider = "sectiondata", priority = 3, dependsOnMethods = "crateSectionTest")
	public void editSectionTest(String sectionname, String updatedsectionname) throws InterruptedException {

		test = extent.createTest("Edit the section with name: " + updatedsectionname);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		// Create Section Method
		Thread.sleep(2000);
		driver.findElement(editsectionbutton(sectionname)).click();
		driver.findElement(editsectionnamefield(sectionname)).click();
		driver.findElement(editsectionnamefield(sectionname)).clear();
		driver.findElement(Sectionnamefield).sendKeys(updatedsectionname);

		driver.findElement(updatebutton).click();

		test.pass("Section created successfully with name: " + updatedsectionname);
		
		Thread.sleep(2000);
		driver.navigate().back();
		clickonblankspace.clickAtPosition(driver, 740, 1110);
		test.pass("navigate to home page successfully");
	}

//	@Test(dataProvider = "sectiondata", priority=4,dependsOnMethods = "editSectionTest")
//	public void DeleteSectionTest(
//			String sectionname,String updatedsectionname) throws InterruptedException {
//
//		test = extent.createTest("Delete the section with name: " + updatedsectionname);
//
//		// Create Section Method
//		Thread.sleep(2000);
//		driver.findElement(deletebutton(updatedsectionname)).click();
//		driver.findElement(deleteconfirmation).click();
//		
//		
//		test.pass("Section Deleted successfully with name: " + updatedsectionname);
//	}

	
	

}
