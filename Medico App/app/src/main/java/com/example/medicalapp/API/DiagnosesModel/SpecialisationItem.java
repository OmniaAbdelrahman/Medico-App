package com.example.medicalapp.API.DiagnosesModel;

import com.google.gson.annotations.SerializedName;



public class SpecialisationItem{

	@SerializedName("SpecialistID")
	private int specialistID;

	@SerializedName("ID")
	private int iD;

	@SerializedName("Name")
	private String name;

	public void setSpecialistID(int specialistID){
		this.specialistID = specialistID;
	}

	public int getSpecialistID(){
		return specialistID;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
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
			"SpecialisationItem{" + 
			"specialistID = '" + specialistID + '\'' + 
			",iD = '" + iD + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}