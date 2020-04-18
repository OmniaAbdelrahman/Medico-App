package com.example.medicalapp.API.DoctorsModel;

import androidx.room.Ignore;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Profile{

	@SerializedName("gender")
	private String gender;
@Ignore
	@SerializedName("languages")
	private List<LanguagesItem> languages;

	@SerializedName("image_url")
	private String imageUrl;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("bio")
	private String bio;

	@SerializedName("title")
	private String title;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("slug")
	private String slug;

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setLanguages(List<LanguagesItem> languages){
		this.languages = languages;
	}

	public List<LanguagesItem> getLanguages(){
		return languages;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setBio(String bio){
		this.bio = bio;
	}

	public String getBio(){
		return bio;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	@Override
 	public String toString(){
		return 
			"Profile{" + 
			"gender = '" + gender + '\'' + 
			",languages = '" + languages + '\'' + 
			",image_url = '" + imageUrl + '\'' + 
			",last_name = '" + lastName + '\'' + 
			",bio = '" + bio + '\'' + 
			",title = '" + title + '\'' + 
			",first_name = '" + firstName + '\'' + 
			",slug = '" + slug + '\'' + 
			"}";
		}
}