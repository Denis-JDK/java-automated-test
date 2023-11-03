package org.iteco_QA_testing.api.request;

import org.apache.http.HttpStatus;
import org.iteco_QA_testing.api.models.Customer;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class SuccessfulRequests {
    private Requests requests;

    public SuccessfulRequests() {
        requests = new Requests();
    }

    public void createCustomer(Customer customer){
        requests.createCustomer(customer)
                .then()
                .assertThat().statusCode(HttpStatus.SC_CREATED);
    }

    public Customer getCustomer(String customerId){
        return requests.getCustomer(customerId)
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .body("$", hasKey("firstName")) //проверяем существует ли поле
                .body("lastName", is("Swith")) //проверяем значение поля
                .body("email", is("jana.swith@company.com"))
                .extract().body().as(Customer.class);
    }

    public void deleteCustomer(String customerId){
         requests.deleteCustomer(customerId)
                .then()
                .assertThat().statusCode(HttpStatus.SC_ACCEPTED);

    }
    public void updateCustomer(Customer customer){
        requests.updateCustomer(customer)
                .then()
                .assertThat().statusCode(HttpStatus.SC_ACCEPTED);
    }
}
