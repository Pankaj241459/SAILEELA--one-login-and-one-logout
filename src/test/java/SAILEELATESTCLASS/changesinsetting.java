package SAILEELATESTCLASS;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.clickonblankspace;
import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class changesinsetting extends SAILEELABaseclass {

	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By Setting = AppiumBy.accessibilityId("Settings");
	By itemwithrate = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\").instance(1)");
	By addgst = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\").instance(8)");
	By whatsappbill = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\").instance(15)");
	By Holdbill = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\").instance(14)");
	By muttilanguage = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\").instance(17)");
	By selectCustomerDetails = AppiumBy.accessibilityId("Select Sections\nSelect customer details option");
	By posprint = AppiumBy.accessibilityId("POS Print");
	By confirm = AppiumBy.accessibilityId("Confirm");
	By lineonefootermessage = AppiumBy
			.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(3)");
	By printQRonbill= AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\").instance(3)");

	

	@DataProvider(name = "FSSAIdetails")
	public Object[][] itemDetails() {
		String FSSAIdetails = "FSSAI NO -12921014000123";

		return new Object[][] { { FSSAIdetails } };
	}

	@Test(dataProvider = "FSSAIdetails", priority = 1)
	public void setting(String FSSAIdetails) throws Exception {
		test = extent.createTest("changes in setting module");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(menubar).click();
		driver.findElement(Setting).click();
		driver.findElement(itemwithrate).click();
		driver.findElement(addgst).click();
		driver.findElement(whatsappbill).click();
		driver.findElement(Holdbill).click();
		driver.findElement(muttilanguage).click();
		driver.findElement(selectCustomerDetails).click();
		driver.findElement(posprint).click();
		driver.findElement(confirm).click();

		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
				+ ".scrollIntoView(new UiSelector().description(\"Header/Footer & Branding\"))"));

		driver.findElement(lineonefootermessage).click();
		driver.findElement(lineonefootermessage).sendKeys(FSSAIdetails);
		driver.hideKeyboard();
		driver.findElement(printQRonbill).click();

		Thread.sleep(3000);
		driver.navigate().back();
		clickonblankspace.clickAtPosition(driver, 740, 1110);
		

		test.pass("setting updated successfully");

	}

}
