package com.example.medicalapp.API.DoctorsModel;

import com.google.gson.annotations.SerializedName;


public class LanguagesItem{

	@SerializedName("code")
	private String code;

	public LanguagesItem(String name) {
		this.name = name;
	}

	@SerializedName("name")
	private String name;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"LanguagesItem{" + 
			"code = '" + code + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}