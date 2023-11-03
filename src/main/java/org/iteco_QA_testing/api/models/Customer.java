package org.iteco_QA_testing.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Customer {
    private String id;
    private String firstName;
    private String sureName;
    private String email;

}
