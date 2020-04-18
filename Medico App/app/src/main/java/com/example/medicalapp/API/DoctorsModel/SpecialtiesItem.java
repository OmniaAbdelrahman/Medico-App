package com.example.medicalapp.API.DoctorsModel;

import com.google.gson.annotations.SerializedName;

public class SpecialtiesItem{

	@SerializedName("actor")
	private String actor;

	public SpecialtiesItem(String actor, String description, String category,String name) {
		this.actor = actor;
		this.description = description;
		this.category = category;
		this.name = name;

	}

	@SerializedName("uid")
	private String uid;

	@SerializedName("actors")
	private String actors;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("category")
	private String category;

	public void setActor(String actor){
		this.actor = actor;
	}

	public String getActor(){
		return actor;
	}

	public void setUid(String uid){
		this.uid = uid;
	}

	public String getUid(){
		return uid;
	}

	public void setActors(String actors){
		this.actors = actors;
	}

	public String getActors(){
		return actors;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	@Override
 	public String toString(){
		return 
			"SpecialtiesItem{" + 
			"actor = '" + actor + '\'' + 
			",uid = '" + uid + '\'' + 
			",actors = '" + actors + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",category = '" + category + '\'' + 
			"}";
		}
}