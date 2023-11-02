package api;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.assertj.core.api.SoftAssertions;
import org.iteco_QA_testing.api.models.Specifications;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

//нужен если много тестов по созданию обновлению и тп и для каждой операции необходим свой класс
// BaseApiTest будет общий для них, чтоб не дублировать код
public class BaseApiTest {
    //одна спецификация на все тесты
    protected Specifications specifications;
    protected Gson gsonMapping;
    protected SoftAssertions softly;
    @BeforeAll
    public static void restAssured(){
        //добавили логирование запросов и ответов перед всеми тестами благодаря static и аннотации @BeforeAll
        RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
    }
    @BeforeEach
    public void setupTest(){
        //в ней указали URL, Content Type
        specifications = new Specifications();
        gsonMapping = new Gson();
        softly = new SoftAssertions();
    }
}
