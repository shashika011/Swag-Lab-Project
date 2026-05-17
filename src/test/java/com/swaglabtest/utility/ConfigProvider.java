package com.swaglabtest.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProvider {

    private Properties prop;

    // Constructor loads the properties file from classpath
    public ConfigProvider() {
        try {
            InputStream instream = getClass()
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (instream == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }

            prop = new Properties();
            prop.load(instream);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    // Generic method to get any property by key
    public String getDataFromConfig(String key) {
        String value = prop.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config.properties");
        }
        return value;
    }

    // Get browser from config
    public String getBrowser() {
        return getDataFromConfig("Browser");
    }

    // Get URL from config (can be QA, Dev, or Prod depending on key)
    public String getURL() {
        return getDataFromConfig("QaURL");  // change key to DevURL or ProdURL for other env
    }
}