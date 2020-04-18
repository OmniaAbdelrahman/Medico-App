package com.example.medicalapp.API.DoctorsModel;

import com.google.gson.annotations.SerializedName;

public class PhonesItem{

	@SerializedName("number")
	private String number;

	public PhonesItem(String number) {
		this.number = number;
	}

	@SerializedName("type")
	private String type;

	public void setNumber(String number){
		this.number = number;
	}

	public String getNumber(){
		return number;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"PhonesItem{" + 
			"number = '" + number + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}