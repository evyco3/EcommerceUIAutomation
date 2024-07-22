package com.evy.framework.config;

import org.aeonbits.owner.ConfigCache;

/**
 * Provides access to the {@link FrameworkConfig} configuration.
 * <p>
 * This class uses the {@link ConfigCache} to create or retrieve
 * the configuration instance for the framework.
 * </p>
 */
public final class ConfigManager {

    /**
     * Gets the framework configuration.
     *
     * @return the {@link FrameworkConfig} instance
     */
    public static FrameworkConfig get() {
        return ConfigCache.getOrCreate(FrameworkConfig.class);
    }
}
