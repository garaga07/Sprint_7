package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.CourierCreationRequest;
import models.CourierLoginRequest;
import org.junit.Before;
import org.junit.Test;
import static steps.CourierCreationApi.*;
import static steps.CourierDeleteApi.deleteCourier;
import static steps.CourierDeleteApi.verifySuccessfulCourierDeleteResponse;
import static steps.CourierLoginApi.*;

@Feature("Создание курьера")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("Тесты на создание курьера")
public class CourierCreationTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание курьера - позитивный сценарий")
    public void positiveCourierCreationTest() {
        //Создание курьера
        CourierCreationRequest courier = generatedPositiveCourier();
        Response responseCreateCourier = createCourier(courier);
        verifyPositiveCreationResponse(responseCreateCourier);
        //Вход курьера в систему
        CourierLoginRequest courierLoginRequest = getCourierLoginCredentials(courier);
        Response responseLoginCourier = loginCourier(courierLoginRequest);
        verifySuccessfulCourierLoginResponse(responseLoginCourier);
        int courierId = responseLoginCourier.getBody().jsonPath().getInt("id");
        //Удаление созданного курьера
        Response responseDeleteCourier = deleteCourier(courierId);
        verifySuccessfulCourierDeleteResponse(responseDeleteCourier);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание курьера с повторяющимся логином")
    public void duplicateLoginCourierCreationTest() {
        CourierCreationRequest courier = generatedPositiveCourier();
        createCourier(courier);
        Response response = createCourier(courier);
        verifyDuplicateLoginCreationResponse(response);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка валидации при создании курьера - отсутствует login")
    public void missingLoginCourierCreationTest() {
        CourierCreationRequest courier = generatedCourierWithMissingLogin();
        Response response = createCourier(courier);
        verifyMissingDataCreationResponse(response);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка валидации при создании курьера - отсутствует password")
    public void missingPasswordCourierCreationTest() {
        CourierCreationRequest courier = generatedCourierWithMissingPassword();
        Response response = createCourier(courier);
        verifyMissingDataCreationResponse(response);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка валидации при создании курьера - отсутствует firstName")
    public void missingFirstNameCourierCreationTest() {
        CourierCreationRequest courier = generatedCourierWithMissingFirstName();
        Response response = createCourier(courier);
        verifyMissingDataCreationResponse(response);
    }
}
