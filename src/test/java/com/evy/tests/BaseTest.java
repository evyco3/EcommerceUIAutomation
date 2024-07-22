package com.evy.tests;

import com.evy.framework.config.ConfigManager;
import com.evy.framework.drivers.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    void setup(){
        Driver.getInstance().init(ConfigManager.get().browserType());
    }

    @AfterMethod
    void tearDown(){
        Driver.getInstance().quitDriver();
    }
}
