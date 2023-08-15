package ru.praktikum.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static ru.praktikum.Constants.DELETE_COURIER_URL;

public class CourierDeleteApi {
    @Step("Отправить запрос на удаление курьера")
    public static Response deleteCourier(int courierId) {
        return given()
                .when()
                .delete(DELETE_COURIER_URL,courierId);
    }

    @Step("Проверить ответ на успешный запрос удаления курьера")
    public static void verifySuccessfulCourierDeleteResponse(Response response) {
        response.then()
                .assertThat()
                .statusCode(SC_OK)
                .body("ok", equalTo(true));
    }
}
