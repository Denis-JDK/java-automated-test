package org.iteco_QA_testing.core;

public class Config {
    private String apiUrl;

    public Config() {
        this.apiUrl = new PropertiesLoader().getProperty("apiUrl");
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
