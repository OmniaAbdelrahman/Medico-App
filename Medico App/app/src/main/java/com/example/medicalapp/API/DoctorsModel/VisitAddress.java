package com.example.medicalapp.API.DoctorsModel;

import com.google.gson.annotations.SerializedName;

public class VisitAddress{

	@SerializedName("zip")
	private String zip;

	public VisitAddress(String stateLong, String city) {
		this.stateLong = stateLong;
		this.city = city;

	}

	@SerializedName("state_long")
	private String stateLong;

	@SerializedName("city")
	private String city;

	@SerializedName("street")
	private String street;

	@SerializedName("lon")
	private double lon;

	@SerializedName("state")
	private String state;

	@SerializedName("street2")
	private String street2;

	@SerializedName("lat")
	private double lat;

	public void setZip(String zip){
		this.zip = zip;
	}

	public String getZip(){
		return zip;
	}

	public void setStateLong(String stateLong){
		this.stateLong = stateLong;
	}

	public String getStateLong(){
		return stateLong;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public String getStreet(){
		return street;
	}

	public void setLon(double lon){
		this.lon = lon;
	}

	public double getLon(){
		return lon;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setStreet2(String street2){
		this.street2 = street2;
	}

	public String getStreet2(){
		return street2;
	}

	public void setLat(double lat){
		this.lat = lat;
	}

	public double getLat(){
		return lat;
	}

	@Override
 	public String toString(){
		return 
			"VisitAddress{" + 
			"zip = '" + zip + '\'' + 
			",state_long = '" + stateLong + '\'' + 
			",city = '" + city + '\'' + 
			",street = '" + street + '\'' + 
			",lon = '" + lon + '\'' + 
			",state = '" + state + '\'' + 
			",street2 = '" + street2 + '\'' + 
			",lat = '" + lat + '\'' + 
			"}";
		}
}