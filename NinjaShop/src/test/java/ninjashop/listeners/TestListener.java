package ninjashop.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import ninjashop.base.BaseTest;
import ninjashop.utils.ExtentReportManager;
import ninjashop.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = ExtentReportManager.createTest(result.getMethod().getMethodName());
        extentTest.log(Status.INFO, "Test Execution Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, "Test Passed Successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL, "Test Failed");
        extentTest.log(Status.FAIL, result.getThrowable());

        Object testInstance = result.getInstance();
        WebDriver driver = null;

        if (testInstance instanceof BaseTest) {
            driver = ((BaseTest) testInstance).driver;
        }

        if (driver != null) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
            try {
                extentTest.addScreenCaptureFromPath(screenshotPath);
                extentTest.log(Status.INFO, "Screenshot captured on failure");
            } catch (Exception e) {
                extentTest.log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flushReport();
    }
}