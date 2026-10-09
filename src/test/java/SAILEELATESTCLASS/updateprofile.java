package SAILEELATESTCLASS;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.clickonblankspace;
import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class updateprofile extends SAILEELABaseclass {

	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By profilemenu = AppiumBy.accessibilityId("Profile");
	By checkbox1 = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\").instance(0)");
	By checkbox2 = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\").instance(1)");
	By checkbox3 = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\").instance(2)");
	By checkbox4 = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\").instance(3)");
	By editbutton = AppiumBy.accessibilityId("Edit");
	By phonenumberfield = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(7)");
	By submitbutton = AppiumBy.accessibilityId("Submit");

	
	@DataProvider(name = "profiledetails")
	public Object[][] itemDetails() {
		String phonenumber = "9226339090";
		return new Object[][] { { phonenumber } };
	}

	@Test(dataProvider = "profiledetails", priority = 1)
	public void updateprofilephoneno(String phonenumber) throws InterruptedException {

		test = extent.createTest("Update profile phone number" + phonenumber);
		driver.findElement(menubar).click();
		driver.findElement(profilemenu).click();
		Thread.sleep(2000);
		driver.findElement(checkbox1).click();
		driver.findElement(checkbox2).click();
		driver.findElement(checkbox3).click();
		driver.findElement(checkbox4).click();
		driver.hideKeyboard();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
				+ ".scrollIntoView(new UiSelector().description(\"Edit\"))"));
		
		driver.findElement(editbutton).click();
		driver.findElement(phonenumberfield).click();
		driver.findElement(phonenumberfield).clear();
		driver.findElement(phonenumberfield).sendKeys(phonenumber);

		driver.hideKeyboard();
		driver.findElement(submitbutton).click();
		Thread.sleep(2000);
		driver.navigate().back();
		clickonblankspace.clickAtPosition(driver, 740, 1110);
	}

}
