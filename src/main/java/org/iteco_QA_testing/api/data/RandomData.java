package org.iteco_QA_testing.api.data;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {
    private static final int DEFAULT_STRING_LENGTH =10;
    private static final int DEFAULT_NAME_LENGTH =7;
    public String getString(){
        return RandomStringUtils.random(DEFAULT_STRING_LENGTH, true, false);
    }
    public String getName(){
        return RandomStringUtils.random(DEFAULT_NAME_LENGTH, true, false);
    }

    public String getEmail(){
        return getString() + "@gmail.com";
    }
}
