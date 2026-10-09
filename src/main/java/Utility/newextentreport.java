package Utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class newextentreport {

    private static ExtentReports extent;


    public static ExtentReports getReportObject() {

        if (extent == null) {

            // ====================================================
            // CREATE TIMESTAMP
            // ====================================================

            String timestamp =
                    new SimpleDateFormat(
                            "yyyyMMdd_HHmmss")
                    .format(new Date());


            // ====================================================
            // REPORT FOLDER
            // ====================================================

            String reportFolder =
                    System.getProperty("user.dir")
                    + File.separator
                    + "report";


            // Create report folder
            File folder =
                    new File(reportFolder);


            if (!folder.exists()) {

                folder.mkdirs();
            }


            // ====================================================
            // REPORT FILE
            // ====================================================

            String reportPath =
                    reportFolder
                    + File.separator
                    + "ExtentReport_"
                    + timestamp
                    + ".html";


            // ====================================================
            // EXTENT SPARK REPORTER
            // ====================================================

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(
                            reportPath);


            spark.config().setReportName(
                    "Meatyns POS Mobile Automation Report");

            spark.config().setDocumentTitle(
                    "Appium Test Execution Report");


            // ====================================================
            // EXTENT REPORT
            // ====================================================

            extent =
                    new ExtentReports();


            extent.attachReporter(
                    spark);


            // ====================================================
            // SYSTEM INFORMATION
            // ====================================================

            extent.setSystemInfo(
                    "Application",
                    "Meatyns POS");

            extent.setSystemInfo(
                    "Platform",
                    "Android");

            extent.setSystemInfo(
                    "Automation",
                    "Appium");

            extent.setSystemInfo(
                    "Framework",
                    "TestNG");

            extent.setSystemInfo(
                    "Language",
                    "Java");

            extent.setSystemInfo(
                    "Tester",
                    "Pankaj");
        }


        return extent;
    }
}