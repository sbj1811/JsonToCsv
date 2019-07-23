package com.sjani.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Gets Properties from the config file
 */
public class PropertiesValues {

    private static final String PROPERTIES_FILE_NAME = "config.properties";

    /**
     * Extracts properties from the config file and returns properties object
     *
     * @return Properties object
     * @throws IOException
     */
    public Properties getProperties() throws IOException {
        Properties properties = new Properties();
        InputStream stream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
        if (stream != null) {
            properties.load(stream);
        } else {
            throw new FileNotFoundException("property file '" + PROPERTIES_FILE_NAME + "' not found in the classpath");
        }
        return properties;
    }
}
