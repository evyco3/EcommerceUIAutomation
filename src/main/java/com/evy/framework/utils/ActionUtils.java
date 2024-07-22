package com.evy.framework.utils;

import io.qameta.allure.Allure;

import java.util.function.Supplier;

/**
 * Utility class for executing actions with retry logic.
 * <p>
 * This class provides methods to run functions with retry attempts. It logs the outcome and reports
 * results to Allure. It is used in {@link com.evy.framework.pages.BasePage} for executing actions
 * that may need retries.
 * </p>
 */
public final class ActionUtils {


    private ActionUtils() {}

    /**
     * Executes a void function with retry logic.
     *
     * @param cls        the class for logging
     * @param exec       the function to execute
     * @param successMsg the message to log and report on success
     * @param errorMsg   the message to log and report on failure
     */
    public static void execVoidFunction(Class<?> cls, Runnable exec, String successMsg, String errorMsg) {
        executeWithRetry(cls, () -> {
            exec.run();
            return null;
        }, successMsg, errorMsg, true);
    }

    /**
     * Executes a function that returns a String with retry logic.
     *
     * @param cls        the class for logging
     * @param exec       the function to execute
     * @param successMsg the message to log and report on success
     * @param errorMsg   the message to log and report on failure
     * @return the result of the function execution
     */
    public static String execStringFunction(Class<?> cls, Supplier<String> exec, String successMsg, String errorMsg) {
        return executeWithRetry(cls, exec, successMsg, errorMsg, false);
    }

    /**
     * Executes a function that returns a boolean with retry logic.
     *
     * @param cls        the class for logging
     * @param exec       the function to execute
     * @param successMsg the message to log and report on success
     * @param errorMsg   the message to log and report on failure
     * @return the result of the function execution
     */
    public static boolean execBooleanFunction(Class<?> cls, Supplier<Boolean> exec, String successMsg, String errorMsg) {
        return Boolean.TRUE.equals(executeWithRetry(cls, exec, successMsg, errorMsg, false));
    }

    /**
     * Executes a function with retry logic and handles logging and Allure reporting.
     *
     * @param cls        the class for logging
     * @param exec       the function to execute
     * @param successMsg the message to log and report on success
     * @param errorMsg   the message to log and report on failure
     * @param <T>        the return type of the function
     * @param isVoid     true if the function has no return value
     * @return the result of the function execution, or null if it's a void function
     */
    private static <T> T executeWithRetry(Class<?> cls, Supplier<T> exec, String successMsg, String errorMsg, boolean isVoid) {
        int attempts = 0;
        int maxAttempts=3;

        while (true) {
            try {
                T result = exec.get();
                LoggerUtils.info(cls, successMsg);
                Allure.step(successMsg);
                return result;
            } catch (Exception e) {
                attempts++;
                if (attempts >=maxAttempts) {
                    LoggerUtils.error(cls, errorMsg, e);
                    Allure.step(errorMsg);
                    throw new RuntimeException(errorMsg, e);
                }
            }
        }

    }

}
