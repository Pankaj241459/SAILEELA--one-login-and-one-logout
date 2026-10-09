package SAILEELATESTCLASS;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Utility.clickonblankspace;
import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class ExportGSTreport extends SAILEELABaseclass {

	// Current month - Dynamic
	private final String currentMonth = LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

	// Locators
	private final By menubar = By.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']"
			+ "/android.widget.FrameLayout/android.view.View" + "/android.view.View/android.view.View"
			+ "/android.view.View/android.view.View[1]" + "/android.widget.Button");

	private final By POSreport = AppiumBy.accessibilityId("POS Report");

	private final By GSTReport = AppiumBy.accessibilityId("GST Reports");

	private final By selectMonth = AppiumBy.accessibilityId("Select Month");

	private final By getreportbutton = AppiumBy.accessibilityId("Get Reports");

	private final By exportbutton = AppiumBy.accessibilityId("Export");

	private final By exportaspdf = AppiumBy.accessibilityId("Export as PDF");

	// private final By openpdf = By.xpath("//android.view.View[@content-desc=\"GST
	// Reports\"]");

	// Dynamic month locator
	private By monthOption(String month) {

		return By.xpath("//*[contains(@text,'" + month + "') " + "or contains(@content-desc,'" + month + "')]");
	}

	

	@Test(priority = 1)
	public void checkgstdeatils() throws Exception {

		test = extent.createTest("Generate GST Report of current month: " + currentMonth);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Open Menu
		wait.until(ExpectedConditions.elementToBeClickable(menubar)).click();

		// Click POS Report
		wait.until(ExpectedConditions.elementToBeClickable(POSreport)).click();

		// Click GST Reports
		wait.until(ExpectedConditions.elementToBeClickable(GSTReport)).click();

		// Open Month dropdown
		wait.until(ExpectedConditions.elementToBeClickable(selectMonth)).click();

		// Select current month dynamically
		By currentMonthLocator = monthOption(currentMonth);

		wait.until(ExpectedConditions.elementToBeClickable(currentMonthLocator)).click();

		// Generate report
		wait.until(ExpectedConditions.elementToBeClickable(getreportbutton)).click();

		test.pass("GST Report of Month: " + currentMonth + " is generated successfully");
	}

	@Test(priority = 2, dependsOnMethods = "checkgstdeatils")
	public void exportreport() throws Exception {

		test = extent.createTest("Export GST Report of current Month: " + currentMonth);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Click Export
		wait.until(ExpectedConditions.elementToBeClickable(exportbutton)).click();

		// Click Export as PDF
		wait.until(ExpectedConditions.elementToBeClickable(exportaspdf)).click();

		// Wait for PDF option/page
		// wait.until(ExpectedConditions.elementToBeClickable(openpdf)).click();

		test.pass("GST Report is exported successfully");

		// Wait before navigating back
		Thread.sleep(3000);
		
		driver.navigate().back();
		clickonblankspace.clickAtPosition(driver, 740, 1110);

	}
}
