package com.evy.framework.listeners;

import com.evy.framework.drivers.Driver;
import com.evy.framework.utils.LoggerUtils;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.ByteArrayInputStream;

/**
 * TestNG listener for managing test execution events and integrating with Allure reporting.
 * Handles test start, success, and failure events, including capturing and attaching screenshots on failure.
 */
public final class ListenerManager extends TestListenerAdapter {

    /**
     * Sets Allure description and logs test start information.
     *
     * @param result TestNG test result object containing method details
     */
    @Override
    public void onTestStart(ITestResult result) {
        Allure.description(result.getMethod().getDescription());
        LoggerUtils.info(getClass(),"Test started: " + result.getMethod().getMethodName()+ "Test Start");
    }

    /**
     * Adds Allure step and logs test success information.
     *
     * @param result TestNG test result object containing method details
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        Allure.step("Test passed: " + result.getMethod().getMethodName());
        LoggerUtils.info(getClass(),"Test passed: " + result.getMethod().getMethodName()+ "Test Success");
    }

    /**
     * Adds Allure step, logs test failure information, and attaches screenshot on failure.
     *
     * @param result TestNG test result object containing method details
     */
    @Override
    public void onTestFailure(ITestResult result) {
        Allure.step("Test failed: " + result.getMethod().getMethodName());
        LoggerUtils.error(getClass(),"Test failed: " + result.getMethod().getMethodName()+ "Test Failure",null);
        attachScreenshotToAllure();
    }

    /**
     * Captures a screenshot using WebDriver if supported, and attaches it to Allure report.
     * Logs error if screenshot capture fails.
     */
    private void attachScreenshotToAllure() {
        try {
            WebDriver driver = Driver.getInstance().getDriver();
            if (driver instanceof TakesScreenshot) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
            } else {
                LoggerUtils.error(getClass(),"Driver does not support taking screenshots Screenshot Error",null);
            }
        } catch (Exception e) {
            LoggerUtils.error(getClass(), "Failed to capture screenshot: " + e.getMessage()+ "Screenshot Error",e);
        }
    }
}