package org.iteco_QA_testing.api.data;

import org.iteco_QA_testing.api.models.Customer;

public class Generator {
    public final RandomData randomData;


    public Generator() {
        this.randomData =new RandomData();
    }

    public Customer getCustomer(){
        return new Customer(randomData.getId(),randomData.getName(),randomData.getName(),randomData.getEmail());
    }
}
