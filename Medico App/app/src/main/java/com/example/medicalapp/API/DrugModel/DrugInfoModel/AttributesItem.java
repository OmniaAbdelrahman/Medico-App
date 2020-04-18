package com.example.medicalapp.API.DrugModel.DrugInfoModel;

import com.google.gson.annotations.SerializedName;

public class AttributesItem{

	@SerializedName("name")
	private String name;

	@SerializedName("value")
	private String value;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"AttributesItem{" + 
			"name = '" + name + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}