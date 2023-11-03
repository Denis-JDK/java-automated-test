package org.iteco_QA_testing.api.request;

import org.apache.http.HttpStatus;
import org.iteco_QA_testing.api.models.Customer;

import static org.hamcrest.Matchers.*;

public class SuccessfulRequests {
    private Requests requests;

    public SuccessfulRequests() {
        requests = new Requests();
    }

    public void createCustomer(Customer customer){
         requests.createCustomer(customer)
                .then()
                .assertThat().statusCode(HttpStatus.SC_CREATED)
                .body(equalTo("Customer stored correctly")); //проверяем не только по коду ответа SC_CREATED, но еще и по строке в теле ответа на операцию создания сущности


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
                .assertThat().statusCode(HttpStatus.SC_ACCEPTED)
                //добавляет проверку сообщений, возможно они нужны на фронтэнде, поэтому их проверяем
                .body(equalTo("Customer updated correctly")); //проверяем не только по коду ответа SC_CREATED, но еще и по строке в теле ответа на операцию создания сущности

    }
}
