package base;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Utility.ExtentReportManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class SAILEELABaseclass {

	// ============================================================
	// DRIVER
	// ============================================================

	protected static AndroidDriver driver;

	// ============================================================
	// EXTENT REPORT
	// ============================================================

	protected static ExtentReports extent;

	protected ExtentTest test;

	// ============================================================
	// COMMON LOGIN LOCATORS
	// ============================================================

	protected By token = By.xpath("//android.widget.EditText");

	protected By submitButton = By.xpath("//android.widget.Button[@content-desc='Submit']");

	protected By username = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]"
			+ "/android.widget.FrameLayout/android.view.View" + "/android.view.View/android.view.View"
			+ "/android.view.View/android.view.View" + "/android.view.View[1]/android.widget.EditText[1]");

	protected By password = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]"
			+ "/android.widget.FrameLayout/android.view.View" + "/android.view.View/android.view.View"
			+ "/android.view.View/android.view.View" + "/android.view.View[1]/android.widget.EditText[2]");

	protected By loginButton = By.xpath("//android.view.View[@content-desc=\"Login\"]");

	protected By allowpermission = By.id("com.android.permissioncontroller:id/permission_allow_button");

	// ============================================================
	// LOGOUT LOCATORS
	// ============================================================

	protected By onlyclickonprofileicon = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]"
			+ "/android.widget.FrameLayout/android.view.View" + "/android.view.View/android.view.View"
			+ "/android.view.View");

	/*
	 * IMPORTANT:
	 *
	 * This locator is currently based on your existing code.
	 *
	 * If "Swapnil Thakur" changes according to logged-in user, replace this with a
	 * stable Profile locator.
	 */
	protected By profileicon = AppiumBy.accessibilityId("Swapnil Thakur");

	protected By logoutbutton = AppiumBy.accessibilityId("Log Out");

	protected By Yes = AppiumBy.accessibilityId("Yes");

	// ============================================================
	// TOKEN
	// ============================================================

	protected String validToken = "RETAILUAE726";

	// ============================================================
	// BEFORE SUITE
	// ============================================================

	@BeforeSuite
	public void beforeSuite() throws Exception {

		System.out.println("======================================");
		System.out.println("TEST SUITE STARTED");
		System.out.println("======================================");

		// --------------------------------------------------------
		// START EXTENT REPORT
		// --------------------------------------------------------

		extent = ExtentReportManager.getReportObject();

		System.out.println("Extent Report initialized");

		// --------------------------------------------------------
		// START APPIUM
		// --------------------------------------------------------

		UiAutomator2Options options = new UiAutomator2Options();

		options.setPlatformName("Android");

		options.setAutomationName("UiAutomator2");

		options.setDeviceName("0123456789ABCDEF");

		options.setUdid("0123456789ABCDEF");

		options.setAppPackage("com.adhik.retail_pos");

		options.setAppActivity("com.adhik.retail_pos.MainActivity");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

		System.out.println("Appium Driver started");

		// --------------------------------------------------------
		// LOGIN ONCE
		// --------------------------------------------------------

		loginAtSuiteStart("swap", "swap");
	}

	// ============================================================
	// LOGIN AT SUITE START
	// ============================================================

	private void loginAtSuiteStart(String usernameValue, String passwordValue) throws Exception {

		try {

			if (driver == null) {

				throw new IllegalStateException("Driver is null. Appium was not started.");
			}

			test = extent.createTest("Suite Login");

			test.info("Starting login for user: " + usernameValue);

			// ----------------------------------------------------
			// TOKEN
			// ----------------------------------------------------

			test.info("Entering Token: " + validToken);

			WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));

			wait.until(ExpectedConditions.elementToBeClickable(token)).click();

			driver.findElement(token).sendKeys(validToken);

			// ----------------------------------------------------
			// SUBMIT
			// ----------------------------------------------------

			test.info("Clicking Submit Button");

			wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();

			// ----------------------------------------------------
			// USERNAME
			// ----------------------------------------------------

			test.info("Entering Username: " + usernameValue);

			wait.until(ExpectedConditions.elementToBeClickable(username)).click();

			driver.findElement(username).sendKeys(usernameValue);

			// ----------------------------------------------------
			// PASSWORD
			// ----------------------------------------------------

			test.info("Entering Password");

			wait.until(ExpectedConditions.elementToBeClickable(password)).click();

			driver.findElement(password).sendKeys(passwordValue);

			// ----------------------------------------------------
			// LOGIN
			// ----------------------------------------------------

			test.info("Clicking Login Button");

			wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

			// ----------------------------------------------------
			// LOGIN SUCCESS
			// ----------------------------------------------------

			test.pass("User logged in successfully: " + usernameValue);

			System.out.println("======================================");

			System.out.println("LOGIN SUCCESSFUL");

			System.out.println("User: " + usernameValue);

			System.out.println("======================================");

		} catch (Exception e) {

			if (test != null) {

				test.fail("Suite login failed: " + e.getMessage());
			}

			throw e;
		}
	}

	// ============================================================
	// AFTER EVERY TEST
	// ============================================================

	@AfterMethod
	public void afterTest(ITestResult result) {

		try {

			// ----------------------------------------------------
			// SCREENSHOT
			// ----------------------------------------------------

			if (driver != null && test != null) {

				String screenshotPath = captureScreenshot();

				attachScreenshotToReport(screenshotPath);
			}

			// ----------------------------------------------------
			// PASS
			// ----------------------------------------------------

			if (result.getStatus() == ITestResult.SUCCESS) {

				if (test != null) {

					test.log(Status.PASS, "Test Passed");
				}
			}

			// ----------------------------------------------------
			// FAIL
			// ----------------------------------------------------

			else if (result.getStatus() == ITestResult.FAILURE) {

				if (test != null) {

					test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
				}
			}

			// ----------------------------------------------------
			// SKIP
			// ----------------------------------------------------

			else if (result.getStatus() == ITestResult.SKIP) {

				if (test != null) {

					test.log(Status.SKIP, "Test Skipped");
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// ============================================================
	// SCREENSHOT
	// ============================================================

	public String captureScreenshot() {

		String folderPath = System.getProperty("user.dir") + File.separator + "screenshots";

		File folder = new File(folderPath);

		if (!folder.exists()) {

			folder.mkdirs();
		}

		String screenshotPath = folderPath + File.separator + "Screenshot_" + System.currentTimeMillis() + ".png";

		try {

			if (driver == null) {

				System.out.println("Driver is null. Screenshot skipped.");

				return screenshotPath;
			}

			TakesScreenshot screenshot = (TakesScreenshot) driver;

			byte[] source = screenshot.getScreenshotAs(OutputType.BYTES);

			Path path = Paths.get(screenshotPath);

			Files.write(path, source);

			System.out.println("Screenshot saved: " + screenshotPath);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return screenshotPath;
	}

	// ============================================================
	// ATTACH SCREENSHOT
	// ============================================================

	public void attachScreenshotToReport(String screenshotPath) {

		try {

			if (test == null) {

				return;
			}

			byte[] screenshotBytes = Files.readAllBytes(Paths.get(screenshotPath));

			String base64Screenshot = Base64.getEncoder().encodeToString(screenshotBytes);

			test.info("Execution Screenshot",
					MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// ============================================================
	// AFTER SUITE
	// LOGOUT ONCE + CLOSE DRIVER + REPORT
	// ============================================================

	@AfterSuite
	public void afterSuite() {

		System.out.println("======================================");

		System.out.println("ALL TESTS COMPLETED");

		System.out.println("Starting Logout...");

		System.out.println("======================================");

		// --------------------------------------------------------
		// LOGOUT
		// --------------------------------------------------------

		if (driver != null) {

			try {

				test = extent.createTest("Suite Logout");

				test.info("Starting logout");

				WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));

				// ------------------------------------------------
				// PROFILE / MENU
				// ------------------------------------------------

				System.out.println("Clicking profile/menu...");

				wait.until(ExpectedConditions.elementToBeClickable(onlyclickonprofileicon)).click();

				// ------------------------------------------------
				// PROFILE
				// ------------------------------------------------

				System.out.println("Clicking profile...");

				wait.until(ExpectedConditions.elementToBeClickable(profileicon)).click();

				// ------------------------------------------------
				// LOGOUT
				// ------------------------------------------------

				System.out.println("Clicking Logout...");

				wait.until(ExpectedConditions.elementToBeClickable(logoutbutton)).click();

				// ------------------------------------------------
				// YES
				// ------------------------------------------------

				System.out.println("Confirming Logout...");

				wait.until(ExpectedConditions.elementToBeClickable(Yes)).click();

				System.out.println("Logout completed successfully");

				test.pass("User logged out successfully");

			} catch (Exception e) {

				System.out.println("Logout failed: " + e.getMessage());

				if (test != null) {

					test.fail("Logout failed: " + e.getMessage());
				}

				e.printStackTrace();

			} finally {

				// ----------------------------------------------
				// CLOSE DRIVER
				// ----------------------------------------------

				if (driver != null) {

					try {

						driver.quit();

						System.out.println("Appium Driver closed");

					} catch (Exception e) {

						System.out.println("Error while closing driver: " + e.getMessage());

					} finally {

						driver = null;
					}
				}
			}

		} else {

			System.out.println("Driver is null. Logout skipped.");
		}

		// --------------------------------------------------------
		// FLUSH EXTENT REPORT
		// --------------------------------------------------------

		if (extent != null) {

			extent.flush();

			System.out.println("Extent Report generated successfully");
		}

		System.out.println("======================================");

		System.out.println("TEST SUITE EXECUTION COMPLETED");

		System.out.println("======================================");
	}
}