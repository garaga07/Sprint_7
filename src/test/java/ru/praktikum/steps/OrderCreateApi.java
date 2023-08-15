package ru.praktikum.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import ru.praktikum.models.CreateOrderRequest;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static ru.praktikum.Constants.CREATE_ORDER_URL;

public class OrderCreateApi {
    @Step("Отправить запрос на создание заказа с цветом: {0}")
    public static Response createOrder(CreateOrderRequest orderRequest) {
        String colorDescription = orderRequest.getColor() == null ? "null" : orderRequest.getColor().toString();
        return  given()
                .header("Content-type", "application/json")
                .body(orderRequest)
                .when()
                .post(CREATE_ORDER_URL);
    }
    @Step("Проверить успешный ответ на запрос создания заказа")
    public static void verifySuccessfulCreateOrderResponse(Response response) {
        response.then()
                .assertThat()
                .statusCode(SC_CREATED)
                .body("track", Matchers.notNullValue());
    }
}
