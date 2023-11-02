package org.iteco_QA_testing.api.models.request;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.iteco_QA_testing.api.models.Customer;
import org.iteco_QA_testing.api.models.Specifications;

import static io.restassured.RestAssured.given;

public class Requests {
    private Specifications specifications;
    private Gson gsonMapping;

    public Requests() {
        //в ней указали URL, Content Type
        this.specifications = new Specifications();
        this.gsonMapping = new Gson();
    }

    public io.restassured.response.Response createCustomer(Customer customer){
       return given()
                .spec(specifications.baseRequestSpec()) //получаем из класса Specification URL Content Type
                .body(gsonMapping.toJson(customer))
                .post("/customer/");

    }

    public io.restassured.response.Response getCustomer(String customerId){
        return given()
                .spec(specifications.baseRequestSpec()) //получаем из класса Specification URL Content Type
                .get("/customer/" + customerId);

    }
    public Response deleteCustomer(String customerId){
        return given()
                .spec(specifications.baseRequestSpec())
                .delete("/customer/" + customerId);
    }
}
