package com.example.medicalapp.API.DoctorsModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PracticesItem{

	@SerializedName("uid")
	private String uid;

	public PracticesItem(List<LanguagesItem> languages, VisitAddress visitAddress, List<PhonesItem> phones, double lon, double lat) {
		this.languages = languages;
		this.visitAddress = visitAddress;
		this.phones = phones;
		this.lon = lon;
		this.lat = lat;
	}



	@SerializedName("office_hours")
	private List<Object> officeHours;

	@SerializedName("accepts_new_patients")
	private boolean acceptsNewPatients;

	@SerializedName("languages")
	private List<LanguagesItem> languages;

	@SerializedName("insurance_uids")
	private List<String> insuranceUids;

	@SerializedName("visit_address")
	private VisitAddress visitAddress;

	@SerializedName("name")
	private String name;

	@SerializedName("phones")
	private List<PhonesItem> phones;

	@SerializedName("lon")
	private double lon=0;

	@SerializedName("location_slug")
	private String locationSlug;

	@SerializedName("lat")
	private double lat=0;

	public void setUid(String uid){
		this.uid = uid;
	}

	public String getUid(){
		return uid;
	}

	public void setOfficeHours(List<Object> officeHours){
		this.officeHours = officeHours;
	}

	public List<Object> getOfficeHours(){
		return officeHours;
	}

	public void setAcceptsNewPatients(boolean acceptsNewPatients){
		this.acceptsNewPatients = acceptsNewPatients;
	}

	public boolean isAcceptsNewPatients(){
		return acceptsNewPatients;
	}

	public void setLanguages(List<LanguagesItem> languages){
		this.languages = languages;
	}

	public List<LanguagesItem> getLanguages(){
		return languages;
	}

	public void setInsuranceUids(List<String> insuranceUids){
		this.insuranceUids = insuranceUids;
	}

	public List<String> getInsuranceUids(){
		return insuranceUids;
	}

	public void setVisitAddress(VisitAddress visitAddress){
		this.visitAddress = visitAddress;
	}

	public VisitAddress getVisitAddress(){
		return visitAddress;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPhones(List<PhonesItem> phones){
		this.phones = phones;
	}

	public List<PhonesItem> getPhones(){
		return phones;
	}

	public void setLon(double lon){
		this.lon = lon;
	}

	public double getLon(){
		return lon;
	}

	public void setLocationSlug(String locationSlug){
		this.locationSlug = locationSlug;
	}

	public String getLocationSlug(){
		return locationSlug;
	}

	public void setLat(double lat){
		this.lat = lat;
	}

	public double getLat(){
		return lat;
	}

	@Override
 	public String toString(){
		return 
			"PracticesItem{" + 
			"uid = '" + uid + '\'' + 
			",office_hours = '" + officeHours + '\'' + 
			",accepts_new_patients = '" + acceptsNewPatients + '\'' + 
			",languages = '" + languages + '\'' + 
			",insurance_uids = '" + insuranceUids + '\'' + 
			",visit_address = '" + visitAddress + '\'' + 
			",name = '" + name + '\'' + 
			",phones = '" + phones + '\'' + 
			",lon = '" + lon + '\'' + 
			",location_slug = '" + locationSlug + '\'' + 
			",lat = '" + lat + '\'' + 
			"}";
		}
}