package ru.praktikum.steps;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikum.models.CourierCreationRequest;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static ru.praktikum.Constants.CREATE_COURIER_URL;

public class CourierCreationApi {
    private static String generateLogin() {
        return new Faker().name().username();
    }

    private static String generatePassword() {
        return new Faker().internet().password();
    }

    private static String generateFirstName() {
        return new Faker().name().firstName();
    }

    @Step("Сгенерировать достаточный набор данных для создания курьера")
    public static CourierCreationRequest generatedPositiveCourier() {
        return new CourierCreationRequest(generateLogin(), generatePassword(), generateFirstName());
    }

    @Step("Сгенерировать курьера с недостаточным набором данных - отсутствует login")
    public static CourierCreationRequest generatedCourierWithMissingLogin() {
        return new CourierCreationRequest(null, generatePassword(), generateFirstName());
    }

    @Step("Сгенерировать курьера с недостаточным набором данных - отсутствует password")
    public static CourierCreationRequest generatedCourierWithMissingPassword() {
        return new CourierCreationRequest(generateLogin(), null, generateFirstName());
    }

    @Step("Сгенерировать курьера с недостаточным набором данных - отсутствует firstName")
    public static CourierCreationRequest generatedCourierWithMissingFirstName() {
        return new CourierCreationRequest(generateLogin(), generatePassword(), null);
    }

    @Step("Отправить запрос на создание курьера")
    public static Response createCourier(CourierCreationRequest courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(CREATE_COURIER_URL);
    }

    @Step("Проверить успешный ответ на запрос создания курьера")
    public static void verifyPositiveCreationResponse(Response response) {
        response.then()
                .assertThat()
                .statusCode(SC_CREATED)
                .body("ok", equalTo(true));
    }

    @Step("Проверить ответ с недостаточным набором даннымх для создания курьера")
    public static void verifyMissingDataCreationResponse(Response response) {
        response.then()
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("code", equalTo(400))
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Step("Проверить ответ c повторяющимся логином для создания курьера")
    public static void verifyDuplicateLoginCreationResponse(Response response) {
        response.then()
                .assertThat()
                .statusCode(SC_CONFLICT)
                .body("message", equalTo("Этот логин уже используется"));
    }
}
