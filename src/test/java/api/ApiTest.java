package api;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.iteco_QA_testing.api.data.Generator;
import org.iteco_QA_testing.api.models.Customer;
import org.iteco_QA_testing.api.models.Specifications;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.Arrays;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class ApiTest extends BaseApiTest {


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

        createdCustomers.add(customer); //добавляем созданного customer в коллекцию, для последующей очистки созданных сущностей, после тестирования

//        given()
//                .spec(specifications.baseRequestSpec()) //получаем из класса Specification URL Content Type
//                .body(gsonMapping.toJson(customer))
//                .post("/customer")
        requests.createCustomer(customer)
                .then()
                .assertThat().statusCode(HttpStatus.SC_CREATED);

//        given()
//                .spec(specifications.baseRequestSpec()) //получаем из класса Specification URL Content Type
//                .get("/customer" + customer.getId())
        requests.getCustomer(customer.getId())
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .body("$", hasKey("firstName")) //проверяем существует ли поле
                .body("lastName", is("Swith")) //проверяем значение поля
                .body("email", is("jana.swith@company.com"));

    }

    private static Stream<Arguments> testDataForSumCollectionValues(){
        return Stream.of(
                Arguments.of( Customer.builder()
                        .id("100")
                        .firstName("Jana")
                        .sureName("Swith")
                        .email("jana.swith@company.com").build()),
                Arguments.of(Customer.builder()
                        .id("200")
                        .firstName("Fana")
                        .sureName("Kwith")
                        .email("djanas.swith@company.com").build())
        );
    }

    @ParameterizedTest
    @MethodSource("testDataForSumCollectionValues")
    public void createCustomerTest(Customer expectCustomer){

        successfulRequests.createCustomer(expectCustomer);
        createdCustomers.add(expectCustomer); //добавляем созданного customer в коллекцию, для последующей очистки созданных сущностей, после тестирования
        Customer actualCustomer=successfulRequests.getCustomer(expectCustomer.getId());

        softly.assertThat(actualCustomer).isEqualTo(expectCustomer);

    }
    public void updateCustomerTest(){
        //Generation new customer
        Customer customer = generator.getCustomer();

        //Creation of generated customer
        successfulRequests.createCustomer(customer);

        //Creation updated customer entity
        Customer updatedCustomer = generator.getCustomer();
        updatedCustomer.setId(customer.getId()); //задаем updateCustomer id уже созданного customer
        //Updating of existing customer
        successfulRequests.updateCustomer(updatedCustomer);

        Customer actualCustomer = successfulRequests.getCustomer(customer.getId());

        // Asser customer is updated
        softly.assertThat(actualCustomer).isEqualTo(updatedCustomer);

    }

}
