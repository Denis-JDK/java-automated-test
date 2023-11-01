import org.assertj.core.api.SoftAssertions;
import org.iteco_QA_testing.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CalculatorTest {
    private Calculator calculator;
    //мегко проходит по списку assert, и не прерывает тестирование пока все не проверит.
    private SoftAssertions softly;

    //метод выполняемый перед всеми тестами, благодаря аннотации
    @BeforeAll
    public static void setupBeforeAllTest(){

    }

    //метод выполняется перед всеми методами, благодаря аннотации
    @BeforeEach
    public void setup(){
        this.calculator = new Calculator();
        //мегко проходит по списку assert, и не прерывает тестирование пока все не проверит.
        this.softly = new SoftAssertions();
    }

    public void assertAll(){
        //в этом методе упадет программа если какой то из assert из списка не прошел. Его обязательно поэтому нужно писать.
        this.softly.assertAll();
    }
    @DisplayName("Base positive test for sum")//присваиваем более расширенное название для отчета, в build/reports/index.html видно его
   // @RepeatedTest(value = 2, name = "{displayName} {currentRepetition}/{totalRepetition}") //количество повторение теста
    @Tag("positive") // тэг, позволяет фильтровать при запуске из терминала, тесты по тэгам
    @ParameterizedTest
    //@ValueSource(ints = {1,-10}) указываем первому параметру несколько последовательных значений, тест запускается последовательно с каждым из них
    @CsvSource({
            "1,2,3",        //firstValue,secondValue,expectedResult
            "-10,-20, -30"

    })
    public void sumTest(int firstValue, int secondValue, int expectedResult ){

        //получаем фактический результат
        int actualResult = calculator.sum(firstValue,secondValue);

        //жесткий прерывает программу тестирования на первом же не пройденном assert, тем самым оставляет не проверенными нижние assert
       // assert expectedResult==actualResult: "We expected "+ expectedResult+ "but was "+ actualResult;

        //мегко проходит по списку assert, и не прерывает тестирование пока все не проверит.
       // SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualResult).isEqualTo(expectedResult).as("We expected "+ expectedResult+ "but was "+ actualResult);
        //в этом методе упадет программа если какой то из assert из списка не прошел. Его обязательно поэтому нужно писать.
      //  softly.assertAll();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test_data.csv") //параметаризируем из файла
    public void divideTest(int firstValue, int secondValue, int expectedResult){
        double actualResult= calculator.divide(firstValue,secondValue);

        //проверяем соответствует ли полученный результат ожидаемому
        softly.assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> testDataForSumCollectionValues(){
        return Stream.of(
                Arguments.of(Arrays.asList(1,2,4),7),
                Arguments.of(Arrays.asList(-1,0,1),0)
        );
    }

    @ParameterizedTest
    @MethodSource("testDataForSumCollectionValues")
    public void sumOfCollectionValuesTest(List<Integer> numbers, long expectedResult){
        long actualResult = calculator.sum(numbers);

        softly.assertThat(actualResult).isEqualTo(expectedResult);


    }





    @Test
    @Disabled //отмечаем при необходимости временно не действующий тест, чтоб остальные отработали.
    @Tag("broken")
    public void brokenTest(){

    }


    //тест ниже дублирует код, решение аннотация @ParameterizedTest+@CsvSource/@ValueSource в которых прописываем значения в параметры теста
    @DisplayName("Base negative test for sum")
    @Tag("negative")
    @Test
    public void sumNegativeValueTest(){
        int firstValue = -10;
        int secondValue = -20;
        int expectedResult = -30;

        int actualResult = calculator.sum(firstValue,secondValue);
        softly.assertThat(expectedResult).isEqualTo(actualResult).as("We expected "+ expectedResult+ "but was "+ actualResult);

    }
}
