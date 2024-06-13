package utilities;

import com.aventstack.extentreports.ExtentReports;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static ExtentReports extent = ExtentTestManager.getInstance();

    @Override
    public void onTestStart(ITestResult result) {
        // Log test start
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log test success
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log test failure
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log test skipped
    }

    // Implement other methods of ITestListener as needed

    @Override
    public void onStart(ITestContext context) {
        // Test suite start
    }

    @Override
    public void onFinish(ITestContext context) {
        // Test suite finish
        extent.flush();
    }
}

