package Utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

	private static ExtentReports extent;

    public static ExtentReports getReportObject() {

        if (extent == null) {

            String timestamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss")
                            .format(new Date());

            String reportPath =
                    System.getProperty("user.dir")
                    + "/test-output/ExtentReport_"
                    + timestamp
                    + ".html";

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(reportPath);

            spark.config().setReportName(
                    "Meatyns POS Mobile Automation Report");

            spark.config().setDocumentTitle(
                    "Appium Test Execution Report");

            extent = new ExtentReports();

            extent.attachReporter(spark);

            extent.setSystemInfo("Application", "Meatyns POS");
            extent.setSystemInfo("Platform", "Android");
            extent.setSystemInfo("Automation", "Appium");
            extent.setSystemInfo("Framework", "TestNG");
            extent.setSystemInfo("Language", "Java");
            extent.setSystemInfo("Tester", "Pankaj Patil.");
        }

        return extent;
    }
}