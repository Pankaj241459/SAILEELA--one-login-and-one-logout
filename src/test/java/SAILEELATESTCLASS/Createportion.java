package SAILEELATESTCLASS;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;
import Utility.clickonblankspace;


public class Createportion extends SAILEELABaseclass {

	// Locators
	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By Mastermanagement = AppiumBy.accessibilityId("Master Management");
	By portionmenu = AppiumBy.accessibilityId("Portion");
	By Addportionbutton = AppiumBy.accessibilityId("Add Portion");
	By portionnamefield = By.xpath("//android.widget.EditText");
	By sectionname = AppiumBy.accessibilityId("Electronics");
	By submitportionbutton = AppiumBy.accessibilityId("Submit");
	By searchbox = By.xpath("//android.widget.EditText");
	By editbutton = By.xpath(
			"//android.view.View[@content-desc=\"Total Portions: 1\"]/android.view.View[2]/android.view.View[2]/android.view.View/android.widget.Button[1]");

	By updatebutton = AppiumBy.accessibilityId("Update");
	By deletebutton = By.xpath(
			"//android.view.View[@content-desc=\"Total Portions: 1\"]/android.view.View[2]/android.view.View[2]/android.view.View/android.widget.Button[2]");
	By deleteconfirmation = AppiumBy.accessibilityId("Yes");

	String portionname = "NOS";
	String updatedportionname = "Numbers";

	

	@DataProvider(name = "portiondata")
	public Object[][] testdata() {

		return new Object[][] { { portionname, updatedportionname } };
	}

	@Test(dataProvider = "portiondata", priority = 2)
	public void crateportionTest(String portionname, String updatedportionname) throws InterruptedException {

		test = extent.createTest("Create the category with name: " + portionname);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		// Create Section Method
		driver.findElement(menubar).click();
		driver.findElement(Mastermanagement).click();
		driver.findElement(portionmenu).click();
		Thread.sleep(2000);
		driver.findElement(Addportionbutton).click();
		driver.findElement(portionnamefield).click();

		driver.findElement(portionnamefield).sendKeys(portionname);
		driver.findElement(sectionname).click();
		driver.findElement(submitportionbutton).click();

		test.pass("Section created successfully with name: " + portionname);
	}

	@Test(dataProvider = "portiondata", priority = 3, dependsOnMethods = "crateportionTest")
	public void editportionTest(String portionname, String updatedportionname) throws InterruptedException {

		test = extent.createTest("Edit the category with name: " + updatedportionname);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		// Create Section Method
		Thread.sleep(2000);
		driver.findElement(searchbox).click();
		driver.findElement(searchbox).sendKeys(portionname);
		driver.findElement(editbutton).click();
		driver.findElement(portionnamefield).click();
		driver.findElement(portionnamefield).clear();
		driver.findElement(portionnamefield).sendKeys(updatedportionname);
		driver.findElement(updatebutton).click();

		test.pass("Section created successfully with name: " + updatedportionname);

	}

	@Test(dataProvider = "portiondata", priority = 4, dependsOnMethods = "editportionTest")
	public void deletecategoryTest(String portionname, String updatedportionname) throws InterruptedException {

		test = extent.createTest("Delete the category with name: " + updatedportionname);

		// Create Section Method
		Thread.sleep(2000);
		driver.findElement(searchbox).click();
		driver.findElement(searchbox).clear();
		driver.findElement(searchbox).sendKeys(updatedportionname);
		Thread.sleep(2000);
		driver.findElement(deletebutton).click();
		driver.findElement(deleteconfirmation).click();

		test.pass("Section created successfully with name: " + updatedportionname);
		driver.navigate().back();
		
		clickonblankspace.clickAtPosition(driver, 740, 1110);
	}

	
	

}
