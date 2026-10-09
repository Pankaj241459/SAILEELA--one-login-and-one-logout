package SAILEELATESTCLASS;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Utility.clickonblankspace;
import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class Exportcustomerdetailsreport extends SAILEELABaseclass {

	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By POSreport = AppiumBy.accessibilityId("POS Report");
	By CustomerDetailsReport = AppiumBy.accessibilityId("Customer Details Report");
	By exportbutton = AppiumBy.accessibilityId("Export");
	By exportaspdf = AppiumBy.accessibilityId("Export as PDF");
	By openpdf = AppiumBy.accessibilityId("Customer Details Report");
	By viewbutton = By.xpath("//android.widget.HorizontalScrollView/android.view.View/android.widget.Button[1]");

	

	@Test(priority = 1)
	public void exportreport() throws Exception {
		test = extent.createTest("Export Customer Details Report");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(menubar).click();
		driver.findElement(POSreport).click();
		driver.findElement(CustomerDetailsReport).click();
		driver.findElement(viewbutton).click();
		Thread.sleep(2000);
		driver.navigate().back();
		driver.findElement(exportbutton).click();
		driver.findElement(exportaspdf).click();
		driver.findElement(openpdf).click();

		Thread.sleep(10000);

		driver.navigate().back();
		Thread.sleep(2000);
		driver.navigate().back();
		clickonblankspace.clickAtPosition(driver, 740, 1110);

	}

}
