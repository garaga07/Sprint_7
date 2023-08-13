package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.CourierCreationRequest;
import models.CourierLoginRequest;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierLoginApi {

    @Step("Сгенерировать данные для успешного входа курьера в систему в систему")
    public static CourierLoginRequest generateCourierLoginRequestWithValidCredentials() {
        return new CourierLoginRequest ("testLogin12","1234");
    }
    @Step("Забрать данные для успешного входа курьера в систему после его создания")
    public static CourierLoginRequest getCourierLoginCredentials(CourierCreationRequest courierCreationRequest) {
        return new CourierLoginRequest (courierCreationRequest.getLogin(),courierCreationRequest.getPassword());
    }
    @Step("Сгенерировать данные для входа курьера в систему с неверным паролем")
    public static CourierLoginRequest generateCourierLoginRequestWithIncorrectPassword() {
        return new CourierLoginRequest ("testLogin12","123456");
    }

    @Step("Сгенерировать данные для входа курьера в систему с неверным логином")
    public static CourierLoginRequest generateCourierLoginRequestWithIncorrectLogin() {
        return new CourierLoginRequest ("testLogin126745987698","1234");
    }

    @Step("Сгенерировать данные для входа курьера в систему без указания логина")
    public static CourierLoginRequest generateCourierLoginRequestWithoutLogin() {
        return new CourierLoginRequest (null,"1234");
    }

    @Step("Сгенерировать данные для входа курьера в систему без указания пароля")
    public static CourierLoginRequest generateCourierLoginRequestWithoutPassword() {
        return new CourierLoginRequest ("testLogin12",null);
    }

    @Step("Отправить запрос на вход курьера в систему")
    public static Response loginCourier(CourierLoginRequest courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
    }

    @Step("Проверить ответ на запрос входа курьера в систему с существующей парой логин/пароль")
    public static void verifySuccessfulCourierLoginResponse(Response response) {
        response.then()
                .assertThat()
                .statusCode(SC_OK)
                .body("id", notNullValue());
    }

    @Step("Проверить ответ на запрос входа курьера в систему с несуществующей парой логин/пароль")
    public static void verifyNotFoundCourierLoginResponse(Response response) {
        response.then()
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Step("Проверить ответ на запрос входа курьера в систему без указания логина")
    public static void verifyIncompleteDataCourierLoginResponse (Response response) {
        response.then()
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("code", equalTo(400))
                .body("message", equalTo("Недостаточно данных для входа"));
    }
}
