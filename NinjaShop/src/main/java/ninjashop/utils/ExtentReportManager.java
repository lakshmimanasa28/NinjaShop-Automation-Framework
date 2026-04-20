package ninjashop.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static final String REPORT_PATH = "test-output/ExtentReport.html";

    public static void initReport() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportPath = "test-output/ExtentReport_" + timestamp + ".html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("NinjaShop Automation Report");
            sparkReporter.config().setReportName("NinjaShop Test Execution Report");
            sparkReporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            
            extent.setSystemInfo("Application", "Tutorial Ninja Demo Store");
            extent.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
    }

    public static ExtentTest createTest(String testName) {
        if (extent == null) {
            initReport();
        }
        return extent.createTest(testName);
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}