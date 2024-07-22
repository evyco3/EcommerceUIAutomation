package com.evy.framework.drivers;

import com.evy.framework.constants.BrowserType;
import com.evy.framework.utils.LoggerUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.EnumMap;
import java.util.Map;

/**
 * Factory for creating WebDriver instances for different browsers.
 * <p>
 * Provides WebDriver instances based on the specified browser type.
 * </p>
 */
public final class DriverFactory {

    private static final Map<BrowserType, DriverSupplier> DRIVERS_MAP = new EnumMap<>(BrowserType.class);

    private DriverFactory() {
        // Prevent instantiation
    }

    static {
        DRIVERS_MAP.put(BrowserType.CHROME, new ChromeDriverSupplier());
        DRIVERS_MAP.put(BrowserType.FIREFOX, new FirefoxDriverSupplier());
        DRIVERS_MAP.put(BrowserType.EDGE, new EdgeDriverSupplier());
        DRIVERS_MAP.put(BrowserType.SAFARI, new SafariDriverSupplier());
        DRIVERS_MAP.put(BrowserType.OPERA, new OperaDriverSupplier());
    }

    /**
     * Gets a WebDriver instance based on the specified browser type.
     *
     * @param browserType the type of browser to get the WebDriver for
     * @return a WebDriver instance for the given browser
     * @throws RuntimeException if there is an error creating the WebDriver
     */
     static WebDriver getDriver(BrowserType browserType) {
        try {
            DriverSupplier supplier = DRIVERS_MAP.get(browserType);
            if (supplier != null) {
                LoggerUtils.info(DriverFactory.class, browserType + " driver initialized successfully");
                return supplier.getDriver();
            } else {
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
            }
        } catch (Exception e) {
            LoggerUtils.error(DriverFactory.class, "Failed to create WebDriver for: " + browserType, e);
            throw new RuntimeException("Error creating WebDriver", e);
        }
    }

    private static final class ChromeDriverSupplier implements DriverSupplier {
        @Override
        public WebDriver getDriver() {
            return WebDriverManager.chromedriver().capabilities(new ChromeOptions().addArguments("--headless")).create();
        }
    }

    private static final class FirefoxDriverSupplier implements DriverSupplier {
        @Override
        public WebDriver getDriver() {
            return WebDriverManager.firefoxdriver().capabilities(new FirefoxOptions().addArguments("--headless")).create();
        }
    }

    private static final class EdgeDriverSupplier implements DriverSupplier {
        @Override
        public WebDriver getDriver() {
            return WebDriverManager.edgedriver().capabilities(new EdgeOptions().addArguments("--headless")).create();
        }
    }

    private static final class OperaDriverSupplier implements DriverSupplier {
        @Override
        public WebDriver getDriver() {
            return WebDriverManager.operadriver().create();
        }
    }

    private static final class SafariDriverSupplier implements DriverSupplier {
        @Override
        public WebDriver getDriver() {
            return WebDriverManager.safaridriver().create();
        }
    }
}
