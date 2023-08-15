package ru.praktikum.steps;

import io.qameta.allure.Step;
import ru.praktikum.models.GetOrdersListResponse;
import ru.praktikum.models.OrdersItem;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertNotEquals;
import static ru.praktikum.Constants.LIST_ORDER_URL;


public class OrderListsApi {
    @Step("Отправить запрос на получение списка заказов с ограничением {limit} и количеством страниц {page}")
    public static GetOrdersListResponse getOrdersList(int limit, int page) {
        return given()
                .header("Content-type", "application/json")
                .queryParam("limit", limit)
                .queryParam("page", page)
                .when()
                .get(LIST_ORDER_URL)
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .body().as(GetOrdersListResponse.class);
    }

    @Step("Проверить, что значение ключа track для каждого заказа не равно 0")
    public static void verifyTrackValues(GetOrdersListResponse ordersResponse) {
        for (OrdersItem order : ordersResponse.getOrders()) {
            assertNotEquals(0, order.getTrack());
        }
    }
}
