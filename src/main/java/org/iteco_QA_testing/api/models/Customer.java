package org.iteco_QA_testing.api.models;

public class Customer {
    private String id;
    private String firstName;
    private String sureName;
    private String email;

    public Customer(String id, String firstName, String sureName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.sureName = sureName;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSureName() {
        return sureName;
    }

    public String getEmail() {
        return email;
    }
}
