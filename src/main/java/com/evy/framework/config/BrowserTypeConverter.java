package com.evy.framework.config;

import com.evy.framework.constants.BrowserType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

/**
 * Converts a string to a {@link BrowserType} enum.
 * <p>
 * This class is used by the configuration library to convert
 * string values from the configuration file to the appropriate
 * {@link BrowserType} enum.
 * </p>
 */
public final class BrowserTypeConverter implements Converter<BrowserType> {
    @Override
    public BrowserType convert(Method method, String browserType) {
        return BrowserType.valueOf(browserType.toUpperCase());
    }
}
