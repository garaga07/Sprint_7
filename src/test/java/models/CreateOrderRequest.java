package models;

import java.util.List;

public class CreateOrderRequest{
	private String firstName;
	private String lastName;
	private String address;
	private int metroStation;
	private String phone;
	private int rentTime;
	private String deliveryDate;
	private String comment;
	private List<String> color;

	public CreateOrderRequest(String firstName, String lastName, String address, int metroStation, String phone, int rentTime,
							  String deliveryDate, String comment, List<String> color) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.metroStation = metroStation;
		this.phone = phone;
		this.rentTime = rentTime;
		this.deliveryDate = deliveryDate;
		this.comment = comment;
		this.color = color;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public String getFirstName(){
		return firstName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public String getLastName(){
		return lastName;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return address;
	}
	public void setColor(List<String> color){
		this.color = color;
	}
	public List<String> getColor(){
		return color;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return phone;
	}
	public void setComment(String comment){
		this.comment = comment;
	}
	public String getComment(){
		return comment;
	}
	public void setRentTime(int rentTime){
		this.rentTime = rentTime;
	}
	public int getRentTime(){
		return rentTime;
	}
	public void setDeliveryDate(String deliveryDate){
		this.deliveryDate = deliveryDate;
	}
	public String getDeliveryDate(){
		return deliveryDate;
	}
	public void setMetroStation(int metroStation){
		this.metroStation = metroStation;
	}
	public int getMetroStation(){
		return metroStation;
	}
}