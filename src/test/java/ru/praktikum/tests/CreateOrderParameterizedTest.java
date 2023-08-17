package ru.praktikum.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.models.CreateOrderRequest;
import java.util.List;
import static ru.praktikum.Constants.BASE_URL;
import static ru.praktikum.steps.OrderCreateApi.createOrder;
import static ru.praktikum.steps.OrderCreateApi.verifySuccessfulCreateOrderResponse;

@Feature("Создание заказа")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("Параметризованный тест на создание заказа")
@RunWith(Parameterized.class)
public class CreateOrderParameterizedTest {
    private final List<String> color;
    // Статические значения для других параметров
    private static final String DEFAULT_FIRST_NAME = "Василий";
    private static final String DEFAULT_LAST_NAME = "Иванов";
    private static final String DEFAULT_ADDRESS = "Konoha, 142 apt.";
    private static final int DEFAULT_METRO_STATION = 4;
    private static final String DEFAULT_PHONE = "+7 777 355 35 35";
    private static final int DEFAULT_RENT_TIME = 1;
    private static final String DEFAULT_DELIVERY_DATE = "2023-08-13";
    private static final String DEFAULT_COMMENT = "Saske, come back to Konoha";

    public CreateOrderParameterizedTest(List<String> color) {
        this.color = color;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }
        @Parameterized.Parameters(name = "Тест на создание заказа с цветом: {0}")
        public static Object[][] data() {
            return new Object[][]{
                    {List.of("BLACK")},
                    {List.of("GREY")},
                    {List.of("BLACK", "GREY")},
                    {null},
            };
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        public void createOrderTest() {
            CreateOrderRequest orderRequest = new CreateOrderRequest(
                    DEFAULT_FIRST_NAME,
                    DEFAULT_LAST_NAME,
                    DEFAULT_ADDRESS,
                    DEFAULT_METRO_STATION,
                    DEFAULT_PHONE,
                    DEFAULT_RENT_TIME,
                    DEFAULT_DELIVERY_DATE,
                    DEFAULT_COMMENT,
                    color
            );
            Response response = createOrder(orderRequest);
            verifySuccessfulCreateOrderResponse(response);
        }
}
