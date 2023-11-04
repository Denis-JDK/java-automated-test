package org.iteco_QA_testing.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesLoader {
    private static final String CONFIG_PROPERTIES = "config.properties";
    private Properties properties;

    public String getProperty(String key){
        if (properties == null){
            properties = loadProperties();
        }
        return properties.getProperty(key);
    }
    private Properties loadProperties(){
        File file = new File(PropertiesLoader.class.getClassLoader().getResource(CONFIG_PROPERTIES).getFile());
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
