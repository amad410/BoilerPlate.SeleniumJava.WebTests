package WebTests;

import Handlers.ReportHandler;
import PageObjects.BasePage;
import Utils.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

public class BaseTest{

    BrowserFactory _browserFactory;
    BasePage _basePage;
    ReportHandler _reportHandler;
    public WebDriver _driver;
    @BeforeClass
    public static void Setup(){
        System.out.println("Initial Setup");

    }
    @BeforeTest
    public void TestSetup(){
        System.out.println("Test Setup");
        _browserFactory = BrowserFactory.getInstance();
        _browserFactory.setDriver("chrome");
        _driver = _browserFactory.getDriver();
        _driver.manage().window().maximize();
        _driver.get("http://www.google.com");
        _basePage = new BasePage(_browserFactory.getDriver());
        _reportHandler = new ReportHandler();
        _reportHandler.setExtent();



    }
    @BeforeMethod
    public void CreateTest(ITestResult result) throws IOException{
        _reportHandler.createTestReport(result);

    }
    @AfterMethod
    public void ReportResults(ITestResult result) throws IOException{
        _reportHandler.reportResults(result,_driver);
        _reportHandler.endReport();

    }

   @AfterClass
    public static void TearDown(){

        System.out.println("Tearing down initial setup");


    }
    @AfterTest
    public void TestTearDown(){
        System.out.println("Tearing down test setup");
        _driver.quit();
        //_reportHandler.endReport();

    }
}
