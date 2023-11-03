package org.iteco_QA_testing.api.data;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {
    public static final int DEFAULT_STRING_LENGTH =10;
    public static final int DEFAULT_NAME_LENGTH =7;
    public static final int DEFAULT_NUMBER_LENGTH =3;

    public String getId(){
        return RandomStringUtils.randomNumeric(DEFAULT_NUMBER_LENGTH);
    }

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
