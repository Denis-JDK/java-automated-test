package api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.assertj.core.api.SoftAssertions;
import org.iteco_QA_testing.api.models.Customer;
import org.iteco_QA_testing.api.models.request.SuccessfulRequests;
import org.iteco_QA_testing.api.models.request.Requests;
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
    protected List<Customer> createdCustomers;
    @BeforeAll
    public static void restAssured(){
        //добавили логирование запросов и ответов перед всеми тестами благодаря static и аннотации @BeforeAll
        RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
    }
    @BeforeEach
    public void setupTest(){
        requests = new Requests();
        successfulRequests = new SuccessfulRequests();
        softly = new SoftAssertions();
        createdCustomers = new ArrayList<>();
    }

    @AfterEach //чистим после каждого метода
    public void cleanData(){
        createdCustomers.forEach(customer ->successfulRequests.deleteCustomer(customer.getId()));
    }
}
