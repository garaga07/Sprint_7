package ru.praktikum.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.models.CourierLoginRequest;

import static ru.praktikum.Constants.BASE_URL;
import static ru.praktikum.steps.CourierLoginApi.*;

@Feature("Логин курьера в системе")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("Тесты на логин курьера в системе")
public class CourierLoginTest {
    @Before
    public void setUp() {
            RestAssured.baseURI = BASE_URL;
        }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Логин курьера в систему с существующей парой логин/пароль")
    public void validCredentialsCourierLoginTest() {
        CourierLoginRequest courier = generateCourierLoginRequestWithValidCredentials();
        Response response = loginCourier(courier);
        verifySuccessfulCourierLoginResponse(response);
    }
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Попытка входа курьера в систему с неверным паролем")
    public void incorrectPasswordCourierLoginTest() {
        CourierLoginRequest courier = generateCourierLoginRequestWithIncorrectPassword();
        Response response = loginCourier(courier);
        verifyNotFoundCourierLoginResponse(response);
    }
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Попытка входа курьера в систему с неверным логином")
    public void incorrectLoginCourierLoginTest() {
        CourierLoginRequest courier = generateCourierLoginRequestWithIncorrectLogin();
        Response response = loginCourier(courier);
        verifyNotFoundCourierLoginResponse(response);
    }
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Попытка входа курьера в систему без указания логина")
    public void attemptCourierLoginWithoutLoginTest() {
        CourierLoginRequest courier = generateCourierLoginRequestWithoutLogin();
        Response response = loginCourier(courier);
        verifyIncompleteDataCourierLoginResponse(response);
    }
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Попытка входа курьера в систему без указания пароля")
    public void attemptCourierLoginWithoutPasswordTest() {
        CourierLoginRequest courier = generateCourierLoginRequestWithoutPassword();
        Response response = loginCourier(courier);
        verifyIncompleteDataCourierLoginResponse(response);
    }

}