package com.evy.framework.config;

import com.evy.framework.constants.BrowserType;
import org.aeonbits.owner.Config;

/**
 * Configuration interface for the framework.
 * <p>
 * This interface defines the configuration properties and
 * their default values used by the framework. It loads properties
 * from the `config.properties` file.
 * </p>
 */
@Config.Sources("file:${user.dir}/src/main/resources/config.properties")
public interface FrameworkConfig extends Config {

    /**
     * Gets the browser type to use.
     *
     * @return the {@link BrowserType} value
     */

    @ConverterClass(BrowserTypeConverter.class)
    @Key("browserType")
    @DefaultValue("CHROME")
    BrowserType browserType();

    /**
     * Gets the URL for the application.
     *
     * @return the application URL
     */
    @Key("url")
    String url();

    /**
     * Gets the implicit wait time in seconds.
     *
     * @return the implicit wait time
     */
    @Key("implicitTime")
    int implicitTime();

    /**
     * Gets the page load timeout in seconds.
     *
     * @return the page load timeout
     */
    @Key("pageLoadTime")
    int pageLoadTime();
}
