package com.evy.framework.utils;

import io.qameta.allure.Allure;

/**
 * Utility class for performing assertions with logging capabilities.
 * This class provides methods to assert conditions, equality, and string containment,
 * with logging of success or failure messages.
 * Used exclusively within test classes for assertion operations.
 */
public class AssertionUtils {

    /**
     * Asserts a condition and logs success or failure.
     *
     * @param condition   The condition to assert.
     * @param description The description of the assertion.
     */
    public static void assertCondition(boolean condition, String description) {
        try {
            assert condition;
            logSuccess(description);
        } catch (AssertionError e) {
            logFailure(description);
            throw e;
        }
    }

    /**
     * Asserts equality between actual and expected values and logs success or failure.
     *
     * @param actual      The actual value.
     * @param expected    The expected value.
     * @param description The description of the assertion.
     */
    public static void assertEquality(Object actual, Object expected, String description) {
        try {
            assert actual.equals(expected);
            logSuccess(description);
        } catch (AssertionError e) {
            logFailure(description);
            throw e;
        }
    }

    /**
     * Asserts that a string contains the expected substring and logs success or failure.
     *
     * @param actual      The actual string.
     * @param expected    The expected substring.
     * @param description The description of the assertion.
     */
    public static void assertContains(String actual, String expected, String description) {
        try {
            assert actual.contains(expected);
            logSuccess(description);
        } catch (AssertionError e) {
            logFailure(description);
            throw e;
        }
    }

    /**
     * Verifies a condition is true and logs success or failure.
     *
     * @param condition   The condition to verify.
     * @param description The description of the verification.
     */
    public static void verifyTrue(boolean condition, String description) {
        if (condition) {
            logSuccess(description);
        } else {
            logFailure(description);
            throw new AssertionError(description + ": Failed");
        }
    }

    /**
     * Logs a successful assertion with Allure and LoggerUtils.
     *
     * @param description The description of the assertion.
     */
    private static void logSuccess(String description) {
        Allure.step(description + ": Passed");
        LoggerUtils.info(AssertionUtils.class, description + ": passed");
    }

    /**
     * Logs a failed assertion with Allure and LoggerUtils.
     *
     * @param description The description of the assertion.
     */
    private static void logFailure(String description) {
        Allure.step(description + ": Failed");
        LoggerUtils.error(AssertionUtils.class, description + ": Failed",null);
    }
}