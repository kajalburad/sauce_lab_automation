package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

    public class ExtentTestManager {
        private static ExtentReports extent;

        public static ExtentReports getInstance() {
            if (extent == null) {
                extent = new ExtentReports();
                ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/ExtentReport.html");
                extent.attachReporter(htmlReporter);
                extent.setSystemInfo("Environment", "Test");
            }
            return extent;
        }
    }


