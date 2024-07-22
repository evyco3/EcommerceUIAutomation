package com.evy.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class for logging messages using Log4j2.
 * <p>
 * Provides static methods to log informational and error messages consistently.
 * </p>
 */
public final class LoggerUtils {

    /**
     * Logger instance for this class.
     */
    private static final Logger logger = LogManager.getLogger(LoggerUtils.class);

    /**
     * Private constructor to prevent instantiation.
     */
    private LoggerUtils() {}

    /**
     * Gets a logger for the specified class.
     *
     * @param cls the class for which to get the logger
     * @return a Logger for the specified class
     */
    private static Logger getLogger(Class<?> cls) {
        return LogManager.getLogger(cls);
    }

    /**
     * Logs an informational message for the specified class.
     *
     * @param cls the class for which to log the message
     * @param msg the message to log
     */
    public static void info(Class<?> cls, String msg) {
        getLogger(cls).info(msg);
    }

    /**
     * Logs an error message and exception for the specified class.
     *
     * @param cls the class for which to log the error
     * @param msg the error message to log
     * @param t   the exception to log
     */
    public static void error(Class<?> cls, String msg, Throwable t) {
        getLogger(cls).error(msg, t);
    }
}
