import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.iteco_QA_testing.api.models.Customer;
import org.iteco_QA_testing.api.models.Specifications;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class ApiTest {
    //одна спецификация на все тесты
    private Specifications specifications;
    private Gson gsonMapping;
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
    }

    @Test
    public void googleTest(){
        given()
                .get("http://google.com/")
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK);
        //проверка на получение кода 200, если не 200 то тест падает
    }

    @Test
    public void createCustomerAssertByBodyParametersTest(){
        Customer customer= Customer.builder()
                .id("100")
                .firstName("Jana")
                .sureName("Swith")
                .email("jana.swith@company.com").build();



        given()
                .spec(specifications.baseRequestSpec()) //получаем из класса Specification URL Content Type
                .body(gsonMapping.toJson(customer))
                .post("/customer")
                .then()
                .assertThat().statusCode(HttpStatus.SC_CREATED);

        given()
                .spec(specifications.baseRequestSpec()) //получаем из класса Specification URL Content Type
                .get("/customer" + customer.getId())
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .body("$", hasKey("firstName")) //проверяем существует ли поле
                .body("lastName", is("Swith")) //проверяем значение поля
                .body("email", is("jana.swith@company.com"));

    }

    @Test
    public void createCustomerTest(){
       Customer expectCustomer= Customer.builder()
               .id("100")
               .firstName("Jana")
               .sureName("Swith")
               .email("jana.swith@company.com").build();



        given()
                .spec(specifications.baseRequestSpec()) //получаем из класса Specification URL Content Type
                .body(gsonMapping.toJson(expectCustomer))
                .post("/customer")
                .then()
                .assertThat().statusCode(HttpStatus.SC_CREATED);

        Customer actualCustomer = given()
                .spec(specifications.baseRequestSpec()) //получаем из класса Specification URL Content Type
                .get("/customer" + expectCustomer.getId())
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .body("$", hasKey("firstName")) //проверяем существует ли поле
                .body("lastName", is("Swith")) //проверяем значение поля
                .body("email", is("jana.swith@company.com"))
                .extract().body().as(Customer.class);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualCustomer).isEqualTo(expectCustomer);

    }

}
