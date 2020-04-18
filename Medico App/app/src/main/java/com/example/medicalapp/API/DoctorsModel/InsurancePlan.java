package com.example.medicalapp.API.DoctorsModel;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class InsurancePlan{

	@SerializedName("uid")
	private String uid;

	@SerializedName("name")
	private String name;

	@SerializedName("category")
	private List<String> category;

	public void setUid(String uid){
		this.uid = uid;
	}

	public String getUid(){
		return uid;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCategory(List<String> category){
		this.category = category;
	}

	public List<String> getCategory(){
		return category;
	}

	@Override
 	public String toString(){
		return 
			"InsurancePlan{" + 
			"uid = '" + uid + '\'' + 
			",name = '" + name + '\'' + 
			",category = '" + category + '\'' + 
			"}";
		}
}