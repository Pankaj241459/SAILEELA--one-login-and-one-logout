package base;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Utility.ExtentReportManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class sepratetestbase {

    protected AndroidDriver driver;

    protected static ExtentReports extent;

    protected ExtentTest test;


    // ============================================================
    // START REPORT ONCE
    // ============================================================

    @BeforeSuite
    public void startReport() {

        extent =
                ExtentReportManager.getReportObject();

        System.out.println(
                "Extent Report initialized");
    }


    // ============================================================
    // START APPIUM ONCE BEFORE CLASS
    // ============================================================

    @BeforeClass
    public void setup() throws Exception {

        


        UiAutomator2Options options =
                new UiAutomator2Options();

        options.setPlatformName(
                "Android");

        options.setAutomationName(
                "UiAutomator2");

        options.setDeviceName(
                "17030ec8");


        options.setAppPackage(
                "com.example.adhik_pos");

        options.setAppActivity(
                "com.example.adhik_pos.MainActivity");


        driver =
                new AndroidDriver(
                        new URL(
                                "http://127.0.0.1:4723"),
                        options);


        
    }


    // ============================================================
    // CREATE EXTENT TEST BEFORE EVERY TEST METHOD
    // ============================================================

    @BeforeMethod
    public void createExtentTest(Method method) {

        String methodName =
                method.getName();


        test =
                extent.createTest(
                        methodName,
                        "Appium Mobile Automation Test");


        test.info(
                "Test Method: "
                + methodName);

        
    }


    // ============================================================
    // AFTER EVERY TEST METHOD
    // ============================================================

    @AfterMethod
    public void afterTest(
            ITestResult result) {

        try {

            // Screenshot
            if (driver != null) {

                String screenshotPath =
                        captureScreenshot();


                attachScreenshotToReport(
                        screenshotPath);
            }


            // PASS
            if (result.getStatus()
                    == ITestResult.SUCCESS) {

                test.log(
                        Status.PASS,
                        "Test Passed");
            }


            // FAIL
            else if (result.getStatus()
                    == ITestResult.FAILURE) {

                test.log(
                        Status.FAIL,
                        "Test Failed: "
                        + result.getThrowable());
            }


            // SKIP
            else if (result.getStatus()
                    == ITestResult.SKIP) {

                test.log(
                        Status.SKIP,
                        "Test Skipped");
            }


        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ============================================================
    // SCREENSHOT
    // ============================================================

    public String captureScreenshot() {

        String folderPath =
                System.getProperty("user.dir")
                + File.separator
                + "screenshots";


        File folder =
                new File(folderPath);


        if (!folder.exists()) {

            folder.mkdirs();
        }


        String screenshotPath =
                folderPath
                + File.separator
                + "Screenshot_"
                + System.currentTimeMillis()
                + ".png";


        try {

            TakesScreenshot screenshot =
                    (TakesScreenshot) driver;


            byte[] source =
                    screenshot.getScreenshotAs(
                            OutputType.BYTES);


            Path path =
                    Paths.get(
                            screenshotPath);


            Files.write(
                    path,
                    source);


            System.out.println(
                    "Screenshot saved: "
                    + screenshotPath);


        } catch (Exception e) {

            e.printStackTrace();
        }


        return screenshotPath;
    }


    // ============================================================
    // ATTACH SCREENSHOT
    // ============================================================

    public void attachScreenshotToReport(
            String screenshotPath) {

        try {

            byte[] screenshotBytes =
                    Files.readAllBytes(
                            Paths.get(
                                    screenshotPath));


            String base64Screenshot =
                    Base64.getEncoder()
                            .encodeToString(
                                    screenshotBytes);


            test.info(
                    "Execution Screenshot",

                    MediaEntityBuilder
                            .createScreenCaptureFromBase64String(
                                    base64Screenshot)
                            .build());


        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ============================================================
    // CLOSE APPIUM AFTER CLASS
    // ============================================================

    @AfterClass
    public void closeDriver() {

        if (driver != null) {

            try {

                driver.quit();

                System.out.println(
                        "Appium Driver closed");

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }


    // ============================================================
    // FLUSH REPORT
    // ============================================================

    @AfterSuite
    public void finishReport() {

        if (extent != null) {

            extent.flush();

            System.out.println(
                    "Extent Report generated successfully");
        }
    }
}

