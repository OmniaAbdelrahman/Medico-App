package com.example.medicalapp.API.DoctorsModel;

import com.google.gson.annotations.SerializedName;

public class LicensesItem{

	@SerializedName("number")
	private String number;

	@SerializedName("state")
	private String state;

	public void setNumber(String number){
		this.number = number;
	}

	public String getNumber(){
		return number;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	@Override
 	public String toString(){
		return 
			"LicensesItem{" + 
			"number = '" + number + '\'' + 
			",state = '" + state + '\'' + 
			"}";
		}
}