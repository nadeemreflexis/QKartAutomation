
package QKART_TESTNG;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass extends QKART_Tests implements ITestListener {
    
    public void onStart(ITestContext context) {
        System.out.println("onStart method started");
    }

    public void onFinish(ITestContext context) {
        System.out.println("onFinish method started");
    }

    public void onTestStart(ITestResult result) {
        System.out.println("Test Started " +result.getName());
        takeScreenshot("TestStart",result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("onTestSuccess Method" +result.getName());
        takeScreenshot("TestEnd",result.getName());
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("onTestFailure Method" +result.getName());
        takeScreenshot("TestFailure",result.getName());
        
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("onTestSkipped Method" +result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("onTestFailedButWithinSuccessPercentage" +result.getName());
    }

}
