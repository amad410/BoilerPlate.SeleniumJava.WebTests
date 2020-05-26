package Handlers;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;


import java.io.IOException;

public class ReportHandler {

    public ExtentHtmlReporter _htmlReporter;
    public ExtentReports _extent;
    public ExtentTest _test;

    public void setExtent(){
        _htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/myReport.html");
        _htmlReporter.config().setDocumentTitle("Automation Report");//title of the report
        _htmlReporter.config().setReportName("Functional Report");//name or the report
        _htmlReporter.config().setTheme(Theme.DARK);

        _extent = new ExtentReports();
        _extent.attachReporter(_htmlReporter);

        _extent.setSystemInfo("Hostname", "Localhost");
        _extent.setSystemInfo("OS", "Windows");
    }
    public void createTestReport(ITestResult result){
        _test = _extent.createTest(result.getMethod().getMethodName());
    }
    public void reportResults(ITestResult result, WebDriver driver) throws IOException{
        if (result.getStatus() == ITestResult.FAILURE) {
            _test.log(Status.FAIL, "Test Case FAILED: " + result.getName());
            String screenshotPath = ScreenCaptureHandler.getScreenshot(driver);
            _test.addScreenCaptureFromPath(screenshotPath);// adding screen shot
            _test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } else if (result.getStatus() == ITestResult.SKIP) {
            _test.log(Status.SKIP, "Test Case SKIPPED: " + result.getName());
        }
        else if (result.getStatus() == ITestResult.SUCCESS) {
            _test.log(Status.PASS, "Test Case PASSED: " + result.getName());
        }

    }
    public void endReport() {
        _extent.flush();
    }

}
