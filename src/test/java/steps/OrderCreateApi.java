package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.CreateOrderRequest;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
public class OrderCreateApi {
    @Step("Отправить запрос на создание заказа")
    public static Response createOrder(CreateOrderRequest orderRequest) {
        return  given()
                .header("Content-type", "application/json")
                .body(orderRequest)
                .when()
                .post("/api/v1/orders");
    }
    @Step("Проверить успешный ответ на запрос создания заказа")
    public static void verifySuccessfulCreateOrderResponse(Response response) {
        response.then()
                .assertThat()
                .statusCode(SC_CREATED)
                .body("track", Matchers.notNullValue());
    }
}
