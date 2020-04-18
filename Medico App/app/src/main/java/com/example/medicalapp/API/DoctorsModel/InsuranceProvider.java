package com.example.medicalapp.API.DoctorsModel;


import com.google.gson.annotations.SerializedName;


public class InsuranceProvider{

	@SerializedName("uid")
	private String uid;

	@SerializedName("name")
	private String name;

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

	@Override
 	public String toString(){
		return 
			"InsuranceProvider{" + 
			"uid = '" + uid + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}