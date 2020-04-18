package com.example.medicalapp.API.DrugModel.DrugInfoModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DrugClaimsItem{

	@SerializedName("aliases")
	private List<String> aliases;

	@SerializedName("name")
	private String name;

	@SerializedName("attributes")
	private List<Object> attributes;

	@SerializedName("source")
	private String source;

	@SerializedName("primary_name")
	private String primaryName;

	@SerializedName("publications")
	private List<Object> publications;

	public void setAliases(List<String> aliases){
		this.aliases = aliases;
	}

	public List<String> getAliases(){
		return aliases;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setAttributes(List<Object> attributes){
		this.attributes = attributes;
	}

	public List<Object> getAttributes(){
		return attributes;
	}

	public void setSource(String source){
		this.source = source;
	}

	public String getSource(){
		return source;
	}

	public void setPrimaryName(String primaryName){
		this.primaryName = primaryName;
	}

	public String getPrimaryName(){
		return primaryName;
	}

	public void setPublications(List<Object> publications){
		this.publications = publications;
	}

	public List<Object> getPublications(){
		return publications;
	}

	@Override
 	public String toString(){
		return 
			"DrugClaimsItem{" + 
			"aliases = '" + aliases + '\'' + 
			",name = '" + name + '\'' + 
			",attributes = '" + attributes + '\'' + 
			",source = '" + source + '\'' + 
			",primary_name = '" + primaryName + '\'' + 
			",publications = '" + publications + '\'' + 
			"}";
		}
}