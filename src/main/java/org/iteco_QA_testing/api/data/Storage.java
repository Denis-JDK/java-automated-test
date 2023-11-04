package org.iteco_QA_testing.api.data;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static Storage instance;
    private List<String> customerIds;

    private Storage() {
        this.customerIds = new ArrayList<>();
    }

    public static Storage getInstance(){
        if (instance == null) return new Storage();
        return instance;
    }

    public void addCustomerIdList(String customerId){
        customerIds.add(customerId);
    }

    public List<String> getCustomerIds() {
        return customerIds;
    }
}
