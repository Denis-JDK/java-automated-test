package org.iteco_QA_testing.core;

public class Config {
    private String apiUrl;

    public Config() {
        this.apiUrl = System.getProperty("apiUrl")!= null ? System.getProperty("apiUrl") : new PropertiesLoader().getProperty("apiUrl");
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
