package com.evy.framework.drivers;

import org.openqa.selenium.WebDriver;

/**
 * Provides WebDriver instances.
 * <p>
 * This interface defines a method to retrieve a WebDriver instance.
 * </p>
 */
public interface DriverSupplier {

    /**
     * Retrieves a WebDriver instance.
     *
     * @return the WebDriver instance
     */
    WebDriver getDriver();
}
