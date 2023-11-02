package org.iteco_QA_testing.api.models;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public class Specifications {

    //настраиваем базовую спецификацию запроса
     public io.restassured.specification.RequestSpecification baseRequestSpec(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        //если мы знаем что в запросах будет JSON то можно его указать в спецификации, а не в каждом методе
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.setBaseUri("http://localhost:8081");
        return requestSpecBuilder.build();
    }
}
