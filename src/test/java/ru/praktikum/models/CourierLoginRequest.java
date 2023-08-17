package ru.praktikum.models;

public class CourierLoginRequest{

	private String password;
	private String login;

	public CourierLoginRequest( String login,String password) {
		this.login = login;
		this.password = password;
	}
	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setLogin(String login){
		this.login = login;
	}

	public String getLogin(){
		return login;
	}
}
