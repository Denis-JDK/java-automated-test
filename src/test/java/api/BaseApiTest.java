package api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.assertj.core.api.SoftAssertions;
import org.iteco_QA_testing.api.data.Generator;
import org.iteco_QA_testing.api.data.RandomData;
import org.iteco_QA_testing.api.data.Storage;
import org.iteco_QA_testing.api.models.Customer;
import org.iteco_QA_testing.api.request.SuccessfulRequests;
import org.iteco_QA_testing.api.request.Requests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

//нужен если много тестов по созданию обновлению и тп и для каждой операции необходим свой класс
// BaseApiTest будет общий для них, чтоб не дублировать код
public class BaseApiTest {
    //одна спецификация на все тесты
    protected Requests requests;
    protected SuccessfulRequests successfulRequests;
    protected SoftAssertions softly;
    protected RandomData randomData;
    protected Generator generator;
    @BeforeAll
    public static void restAssured(){
        //добавили логирование запросов и ответов перед всеми тестами благодаря static и аннотации @BeforeAll
        RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
    }
    //метод прогоняется перед каждым тестом благодоря аннотации
    @BeforeEach
    public void setupTest(){
        requests = new Requests();
        successfulRequests = new SuccessfulRequests();
        softly = new SoftAssertions();
        randomData = new RandomData();
        generator= new Generator();
    }

    @AfterEach //чистим после каждого метода(благодаря аннотации), созданные сущности во время тестирования
    public void cleanData(){
        Storage.getInstance().getCustomerIds().forEach(customerId->requests.deleteCustomer(customerId));
    }
}
