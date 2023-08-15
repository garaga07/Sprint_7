package ru.praktikum.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.models.GetOrdersListResponse;

import static ru.praktikum.Constants.BASE_URL;
import static ru.praktikum.steps.OrderListsApi.getOrdersList;
import static ru.praktikum.steps.OrderListsApi.verifyTrackValues;

@Feature("Получение списка заказов")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("Тесты на получение списка заказов")
public class OrderListTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка заказов")
    public void getOrderListTest() {
        GetOrdersListResponse ordersResponse = getOrdersList(10,1);
        verifyTrackValues(ordersResponse);
    }
}
