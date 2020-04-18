package com.example.medicalapp.API.DiagnosesModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class DiagnosesResponse{

	@SerializedName("Issue")
	private Issue issue;

	@SerializedName("Specialisation")
	private List<SpecialisationItem> specialisation;

	public void setIssue(Issue issue){
		this.issue = issue;
	}

	public Issue getIssue(){
		return issue;
	}

	public void setSpecialisation(List<SpecialisationItem> specialisation){
		this.specialisation = specialisation;
	}

	public List<SpecialisationItem> getSpecialisation(){
		return specialisation;
	}

	@Override
 	public String toString(){
		return 
			"DiagnosesResponse{" + 
			"issue = '" + issue + '\'' + 
			",specialisation = '" + specialisation + '\'' + 
			"}";
		}
}