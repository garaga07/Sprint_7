package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.CreateOrderRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;
import static steps.OrderCreateApi.createOrder;
import static steps.OrderCreateApi.verifySuccessfulCreateOrderResponse;

@Feature("Создание заказа")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("Параметризованный тест на создание заказа")
@RunWith(Parameterized.class)
public class CreateOrderParameterizedTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final List<String> color;

    public CreateOrderParameterizedTest(String firstName, String lastName, String address, int metroStation, String phone,
                                        int rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }
        @Parameterized.Parameters
        public static Object[][] data() {
            return new Object[][]{
                    {"Василий", "Иванов", "Konoha, 142 apt.", 4, "+7 777 355 35 35", 1, "2023-08-13", "Saske, come back to Konoha", List.of("BLACK")},
                    {"Сергей", "Чернов", "Konoha, 142 apt.", 4, "+7 779 355 35 35", 2, "2023-08-13", "Saske, come back to Konoha", List.of("GREY")},
                    {"Виктор", "Петров", "Konoha, 142 apt.", 4, "+7 778 355 35 35", 3, "2023-08-13", "Saske, come back to Konoha", List.of("BLACK", "GREY")},
                    {"Антон", "Долгов", "Konoha, 142 apt.", 4, "+7 770 355 35 35", 5, "2023-08-13", "Saske, come back to Konoha", null},
            };
        }

        @Test
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Тест на создание заказа")
        public void CreateOrderTest() {
            CreateOrderRequest orderRequest = new CreateOrderRequest(firstName, lastName, address, metroStation, phone,
                    rentTime, deliveryDate, comment, color);
            Response response = createOrder(orderRequest);
            verifySuccessfulCreateOrderResponse(response);
        }
}
