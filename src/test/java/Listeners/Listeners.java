package Listeners;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listeners extends TestListenerAdapter {
    /*
   This is the listener class which gets automatically invoked whenever the test methods are called.
    */
    public void onTestStart(ITestResult result){
        System.out.println("Test Started");
    }
    public void onTestSuccess(ITestResult result){
        System.out.println("Test Passed");
    }
    public void onTestFailure(ITestResult result){
        System.out.println("Test Failed");
    }
    public void onTestSkipped(ITestResult result){
        System.out.println("Test Skipped");
    }



}
