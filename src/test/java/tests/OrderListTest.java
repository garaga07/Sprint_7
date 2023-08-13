package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import models.GetOrdersListResponse;
import models.OrdersItem;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertNotEquals;

@Feature("Получение списка заказов")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("Тесты на получение списка заказов")
public class OrderListTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка заказов")
    public void getOrderListTest() {
        GetOrdersListResponse ordersResponse = given()
                .header("Content-type", "application/json")
                .queryParam("limit", 10)
                .queryParam("page", 1)
                .when()
                .get("/api/v1/orders")
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .body().as(GetOrdersListResponse.class);
        // Проходим по каждому ордеру в списке и проверяем, что значение ключа track не равно 0
        for (OrdersItem order : ordersResponse.getOrders()) {
            assertNotEquals(0, order.getTrack());
        }
    }
}
