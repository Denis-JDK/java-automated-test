import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class ApiTest {

    @BeforeAll
    public static void restAssured(){
        //добавили логирование запросов и ответов перед всеми тестами благодаря static и аннотации @BeforeAll
        RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
        // убрали базовый URL из каждого теста и сделали его по умолчанию
        RestAssured.baseURI="http://localhost:8081";
    }

    @Test
    public void googleTest(){
        given()
                .get("http://google.com/")
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK);
        //проверка на получение кода 200, если не 200 то тест падает
    }

    public void createCustomerTest(){
        String id = "100"; //вынесли id из JSON POST в переменную чтоб потом было проще подставить в GET проверку, а создали ли мы данный обьект.
        given()
                .body("{\n"+
                        " \"id\": \"100\",\n"+
                        " \"firstName\": \"Jane\",\n"+
                        " \"lastName\": \"Swith\",\n"+
                        " \"email\": \"jana.swith@company.com\"\n"+
                        "}")
                .contentType(ContentType.JSON)
                .post("/customer")
                .then()
                .assertThat().statusCode(HttpStatus.SC_CREATED);

        given()
                .get("/customer" + id)
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .body("$", hasKey("firstName")) //проверяем существует ли поле
                .body("lastName", is("Swith")) //проверяем значение поля
                .body("email", is("jana.swith@company.com"));
    }

}
