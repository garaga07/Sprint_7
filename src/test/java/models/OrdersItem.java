package models;

public class OrdersItem{
	private Object lastName;
	private Object address;
	private Object color;
	private Object firstName;
	private String createdAt;
	private Object phone;
	private Object courierId;
	private Object comment;
	private int id;
	private int rentTime;
	private Object deliveryDate;
	private int track;
	private Object metroStation;
	private String updatedAt;
	private int status;

	public Object getLastName(){
		return lastName;
	}

	public Object getAddress(){
		return address;
	}

	public Object getColor(){
		return color;
	}

	public Object getFirstName(){
		return firstName;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public Object getPhone(){
		return phone;
	}

	public Object getCourierId(){
		return courierId;
	}

	public Object getComment(){
		return comment;
	}

	public int getId(){
		return id;
	}

	public int getRentTime(){
		return rentTime;
	}

	public Object getDeliveryDate(){
		return deliveryDate;
	}

	public int getTrack(){
		return track;
	}

	public Object getMetroStation(){
		return metroStation;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getStatus(){
		return status;
	}
}
