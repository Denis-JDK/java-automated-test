package org.iteco_QA_testing.api.models;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.iteco_QA_testing.core.Config;

public class Specifications {

    //настраиваем базовую спецификацию запроса
     public io.restassured.specification.RequestSpecification baseRequestSpec(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        //если мы знаем что в запросах будет JSON то можно его указать в спецификации, а не в каждом методе
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.setBaseUri(new Config().getApiUrl());
        return requestSpecBuilder.build();
    }
}
