package SAILEELATESTCLASS;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class Dailysalesreport extends SAILEELABaseclass {

	// Locators
	String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	By profilebutton = AppiumBy.accessibilityId("Swapnil Thakur");

	By dailysalesreport = AppiumBy.accessibilityId("Daily Sale Report");

	By submit = AppiumBy.accessibilityId("Submit");

	By ok = AppiumBy.accessibilityId("OK");

	By downloadreport = AppiumBy.accessibilityId("Download Report");

	By exportaspdf = AppiumBy.accessibilityId("Export as PDF");
	By openfile = By
			.xpath("(//android.widget.ScrollView/android.view.View/android.view.View[@text=\"" + todayDate + "\"])[1]");

	
	@Test(priority = 2)
	public void generatedailysalesreport() throws Exception {

		test = extent.createTest("Generate daily sales report");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(profilebutton).click();

		// Open Daily Sale Report
		driver.findElement(dailysalesreport).click();

		Thread.sleep(4000);
		driver.findElement(submit).click();

		Thread.sleep(1000);

		driver.findElement(downloadreport).click();

		Thread.sleep(1000);

		driver.findElement(exportaspdf).click();

		// Wait for PDF download
		Thread.sleep(3000);
		driver.findElement(openfile).click();
		Thread.sleep(20000);

		driver.navigate().back();

		test.pass("Daily sales report open successfully");

	}

}