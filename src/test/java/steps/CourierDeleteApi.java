package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;

public class CourierDeleteApi {
    @Step("Отправить запрос на удаление курьера")
    public static Response deleteCourier(int courierId) {
        return given()
                .when()
                .delete("/api/v1/courier/{id}",courierId);
    }

    @Step("Проверить ответ на успешный запрос удаления курьера")
    public static void verifySuccessfulCourierDeleteResponse(Response response) {
        response.then()
                .assertThat()
                .statusCode(SC_OK)
                .body("ok", equalTo(true));
    }
}
