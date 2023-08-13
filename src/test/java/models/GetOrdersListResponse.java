package models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class GetOrdersListResponse{
	private List<OrdersItem> orders;
	public List<OrdersItem> getOrders(){
		return orders;
	}
}